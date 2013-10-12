package net.seabears.stats.hockey.population.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.seabears.stats.StatsSiteUtils;
import net.seabears.stats.IntegerUtils;
import net.seabears.stats.TeamRole;
import net.seabears.stats.hockey.HockeyUtils;
import net.seabears.stats.hockey.objects.Game;
import net.seabears.stats.hockey.objects.Team;
import net.seabears.stats.hockey.objects.TeamGame;
import net.seabears.stats.hockey.objects.TeamGameStats;
import net.seabears.stats.hockey.population.IGamePopulator;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebGamePopulator implements IGamePopulator
{
  private final static Logger LOG = Logger.getLogger(WebGamePopulator.class.getName());
  final static String LEAGUE = System.getProperty("net.seabears.stats.hockey.league");
  final static String SCOREBOARD_URL = StatsSiteUtils.BASE_URL + "/" + LEAGUE + "/scoreboard";
  final static String KEY_HREF = "href";
  final static String KEY_VALUE = "value";

  private final Map<String, Team> teamsByName;

  public WebGamePopulator(List<Team> teams)
  {
    this.teamsByName = mapTeamsByName(teams);
  }

  private Map<String, Team> mapTeamsByName(List<Team> teams)
  {
    HashMap<String, Team> map = new HashMap<String, Team>(teams.size());
    for (Team t : teams)
    {
      map.put(t.getCity() + ' ' + t.getMascot(), t);
    }
    return map;
  }

  public List<Game> populate(int season, Date minimumDate, Date maximumDateExclusive) throws IOException
  {
    List<String> weekUrls = getDateUrls(SCOREBOARD_URL, minimumDate, maximumDateExclusive);
    return getGames(weekUrls);
  }

  List<String> getDateUrls(String url, Date minimumDate, Date maximumDateExclusive) throws IOException
  {
    ArrayList<String> dateUrls = new ArrayList<String>();
    Calendar cal = Calendar.getInstance();
    cal.setTime(minimumDate);
    SimpleDateFormat dateFormat = getDateFormat();
    while (cal.getTime().before(maximumDateExclusive))
    {
      dateUrls.add(SCOREBOARD_URL + "?date=" + dateFormat.format(cal.getTime()));
      cal.add(Calendar.DATE, 1);
    }
    return dateUrls;
  }

  static SimpleDateFormat getDateFormat()
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    format.setLenient(true);
    return format;
  }

  List<Game> getGames(List<String> dateUrls) throws IOException
  {
    if (dateUrls.isEmpty())
    {
      LOG.warning("No day URLs");
      return null;
    }

    ArrayList<Game> games = new ArrayList<Game>();
    for (String url : dateUrls)
    {
      List<Game> gamesForDay = getGamesForDay(url);
      if (gamesForDay.isEmpty())
      {
        LOG.info("No games found at URL " + url);
        break;
      }
      games.addAll(gamesForDay);
    }
    return games;
  }

  List<Game> getGamesForDay(String dayUrl) throws IOException
  {
    List<String> gameUrls = getBoxScoreUrls(dayUrl);
    ArrayList<Game> games = new ArrayList<Game>(gameUrls.size());
    LOG.info(gameUrls.size() + " URLs found at " + dayUrl);
    for (String url : gameUrls)
    {
      Document doc = Jsoup.connect(url).get();
      Game game = getGameFromDoc(doc);
      if (game != null)
      {
        Date played = getGameDate(doc);
        if (played != null)
        {
          game.setSeason(HockeyUtils.getSeason(played));
        }
        game.setPlayed(played);
        game.setNum(getNumberFromGameUrl(url));
        game.setPlayoffs(false);
        games.add(game);
        LOG.info("Adding new game");
      }
    }
    return games;
  }

  static Integer getNumberFromGameUrl(String url)
  {
    return IntegerUtils.parse(url.replaceFirst("^.+=(?=\\d+$)", StringUtils.EMPTY));
  }

  Date getGameDate(Document doc)
  {
    Elements ps = doc.select("div.game-time-location p");
    if (!ps.isEmpty())
    {
      try
      {
        return StatsSiteUtils.getGameDate(ps.get(0).text());
      } catch (ParseException e)
      {
        LOG.warning(e.getMessage());
        return null;
      }
    }
    return null;
  }

  List<String> getBoxScoreUrls(String url) throws IOException
  {
    Document doc = Jsoup.connect(url).get();
    Elements gameBlocks = doc.select("div.mod-content");
    LOG.info("Found " + gameBlocks.size() + " game blocks in page");
    ArrayList<String> boxscoreUrls = new ArrayList<String>(gameBlocks.size());
    for (Element gameBlock : gameBlocks)
    {
      if (isGameFinal(gameBlock))
      {
        Elements gameLinks = gameBlock.select("div.expand-gameLinks a");
        for (Element link : gameLinks)
        {
          String href = link.attr(KEY_HREF);
          if (href.contains("boxscore"))
          {
            String boxscoreUrl = StatsSiteUtils.BASE_URL + href;
            boxscoreUrls.add(boxscoreUrl);
            LOG.info("Found new box-score URL: " + boxscoreUrl);
          }
        }
      }
    }
    return boxscoreUrls;
  }

  boolean isGameFinal(Element element)
  {
    Elements lis = element.select("ul.game-info li");
    return !lis.isEmpty() && lis.get(0).text().toLowerCase().contains("final");
  }

  Game getGameFromDoc(Document doc) throws IOException
  {
    Elements tables = doc.select("table.mod-data");
    LOG.info("Found " + tables.size() + " tables in page");
    return getGameFromHtmlTables(tables);
  }

  Game getGameFromHtmlTables(Elements tables)
  {
    TeamGame visitingTeamGame = new TeamGame();
    TeamGame homeTeamGame = new TeamGame();
    int offset = getTeamGame(visitingTeamGame, tables, 0, TeamRole.VISITING);
    getTeamGame(homeTeamGame, tables, offset, TeamRole.HOME);
    if (homeTeamGame.getStats() != null && visitingTeamGame.getStats() != null)
    {
      // swap some stats between the two stats objects
      homeTeamGame.getStats().setBlockedShots(visitingTeamGame.getStats().getShotsBlocked());
      visitingTeamGame.getStats().setBlockedShots(homeTeamGame.getStats().getShotsBlocked());

      // create the game
      LOG.info("Creating game with teams " + visitingTeamGame + " at " + homeTeamGame);
      Game game = new Game();
      homeTeamGame.setGame(game);
      visitingTeamGame.setGame(game);
      game.setTeamGames(Arrays.asList(homeTeamGame, visitingTeamGame));
      return game;
    }
    return null;
  }

  int getTeamGame(TeamGame teamGame, Elements tables, int offset, TeamRole role)
  {
    int index = offset;
    for (Element table : tables.subList(offset, tables.size()))
    {
      ++index;
      Team team = this.teamsByName.get(getTeamName(table));
      List<String> headers = getTableHeaders(table);
      List<List<String>> data = getTableData(table, headers.size());
      LOG.info("team=" + team + " headers=" + headers.size() + " data=" + data.size());
      if (team != null && !headers.isEmpty() && !data.isEmpty())
      {
        LOG.info("Processing game for team " + team);
        TeamGameStats stats = WebGameStatsParser.parse(headers, data);
        if (stats != null)
        {
          stats.setTeamGame(teamGame);
          teamGame.setRole(role);
          teamGame.setTeam(team);
          teamGame.setStats(stats);
          break;
        }
      }
    }
    return index;
  }

  String getTeamName(Element table)
  {
    Elements headers = table.select("thead tr th");
    LOG.info("Found " + headers.size() + " name headers in table");
    return !headers.isEmpty() ? headers.get(0).text() : null;
  }

  List<String> getTableHeaders(Element table)
  {
    final int headerRowIndex = 1;
    Elements headerRows = table.select("thead tr");
    LOG.info("Found " + headerRows.size() + " data headers in table");
    if (headerRows.size() > headerRowIndex)
    {
      Elements headerCells = headerRows.get(headerRowIndex).select("th");
      ArrayList<String> headers = new ArrayList<String>(headerCells.size());
      for (Element headerCell : headerCells)
      {
        String text = headerCell.text();
        if (!StringUtils.isEmpty(text))
        {
          headers.add(text);
        }
      }
      return headers;
    }
    return Collections.<String> emptyList();
  }

  List<List<String>> getTableData(Element table, int expectedColumns)
  {
    Elements dataRows = table.select("tbody tr");
    LOG.info("Found " + dataRows.size() + " data rows in table");
    ArrayList<List<String>> rows = new ArrayList<List<String>>(dataRows.size());
    for (Element dataRow : dataRows)
    {
      // get the data cells for this row
      Elements dataCells = dataRow.select("td");
      ArrayList<String> cells = new ArrayList<String>(dataCells.size());
      for (Element dataCell : dataCells)
      {
        String text = dataCell.text();
        if (!StringUtils.isEmpty(text))
        {
          cells.add(text);
        }
      }

      // add the list of cells to the rows list if it has the expected # of
      // columns
      if (cells.size() == expectedColumns)
      {
        rows.add(cells);
      }
    }
    return rows;
  }
}
