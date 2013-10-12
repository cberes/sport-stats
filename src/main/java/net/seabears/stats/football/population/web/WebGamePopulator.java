package net.seabears.stats.football.population.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.seabears.stats.StatsSiteUtils;
import net.seabears.stats.IntegerUtils;
import net.seabears.stats.TeamRole;
import net.seabears.stats.football.FootballUtils;
import net.seabears.stats.football.objects.Game;
import net.seabears.stats.football.objects.Team;
import net.seabears.stats.football.objects.TeamGame;
import net.seabears.stats.football.objects.TeamGameStats;
import net.seabears.stats.football.population.IGamePopulator;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebGamePopulator implements IGamePopulator
{
  private final static Logger LOG = Logger.getLogger(WebGamePopulator.class.getName());
  final static String LEAGUE = System.getProperty("net.seabears.stats.football.league");
  final static String SCOREBOARD_URL = StatsSiteUtils.BASE_URL + "/" + LEAGUE + "/scoreboard";
  final static String KEY_HREF = "href";
  final static String KEY_VALUE = "value";

  private final Map<String, Team> teamsByAbbrev;

  public WebGamePopulator(List<Team> teams)
  {
    this.teamsByAbbrev = mapTeamsByAbbrev(teams);
  }

  private Map<String, Team> mapTeamsByAbbrev(List<Team> teams)
  {
    HashMap<String, Team> map = new HashMap<String, Team>(teams.size());
    for (Team t : teams)
    {
      map.put(t.getAbbrev().toUpperCase(), t);
    }
    return map;
  }

  public synchronized List<Game> populate(int season, int firstWeek, int finalWeek) throws IOException
  {
    String scoreBoardUrl = getSeasonUrl(season);
    List<String> weekUrls = getWeekUrls(scoreBoardUrl);
    return getGames(weekUrls, firstWeek, finalWeek);
  }

  String getSeasonUrl(int season) throws IOException
  {
    Document doc = Jsoup.connect(SCOREBOARD_URL).get();
    Elements options = doc.select("div.selectdates select option");
    for (Element option : options)
    {
      String value = option.attr(KEY_VALUE);
      if (value.contains(season + StringUtils.EMPTY))
      {
        return SCOREBOARD_URL + value;
      }
    }
    return SCOREBOARD_URL;
  }

  List<String> getWeekUrls(String url) throws IOException
  {
    Document doc = Jsoup.connect(url).get();
    Elements weekLinks = doc.select("div.selectdates div.week a");
    ArrayList<String> weekUrls = new ArrayList<String>(weekLinks.size());
    LOG.info("Found " + weekLinks.size() + " week links in page");
    for (Element link : weekLinks)
    {
      String weekUrl = SCOREBOARD_URL + link.attr(KEY_HREF);
      weekUrls.add(weekUrl);
      LOG.info("Found new week URL: " + weekUrl);
    }
    return weekUrls;
  }

  List<Game> getGames(List<String> weekUrls, int firstWeek, int finalWeek) throws IOException
  {
    if (weekUrls == null)
    {
      LOG.warning("No week URLs");
      return null;
    }

    ArrayList<Game> games = new ArrayList<Game>();
    int week = 0;
    for (String url : weekUrls)
    {
      int thisWeek = ++week;
      if (thisWeek >= firstWeek && (finalWeek < 0 || thisWeek <= finalWeek))
      {
        List<Game> gamesForWeek = getGamesForWeek(url, week);
        if (gamesForWeek.isEmpty())
        {
          LOG.info("No games for week " + week + " at URL " + url);
          break;
        }
        games.addAll(gamesForWeek);
      }
    }
    return games;
  }

  List<Game> getGamesForWeek(String weekUrl, int week) throws IOException
  {
    List<String> gameUrls = getBoxScoreUrls(weekUrl);
    ArrayList<Game> games = new ArrayList<Game>(gameUrls.size());
    LOG.info("Week " + week + ": " + gameUrls.size() + " URLs");
    for (String url : gameUrls)
    {
      Document doc = Jsoup.connect(url).get();
      Game game = getGameAtUrl(doc);
      if (game != null)
      {
        Date played = getGameDate(doc);
        if (played != null)
        {
          game.setSeason(FootballUtils.getSeason(played));
        }
        game.setPlayed(played);
        game.setWeek(week);
        games.add(game);
        LOG.info("Adding new game");
      }
    }
    return games;
  }

  List<String> getBoxScoreUrls(String url) throws IOException
  {
    Document doc = Jsoup.connect(url).get();
    Elements gameLinks = doc.select("div.game-links div.more-links ul a");
    ArrayList<String> boxscoreUrls = new ArrayList<String>(gameLinks.size());
    LOG.info("Found " + gameLinks.size() + " game links in page");
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
    return boxscoreUrls;
  }

  Game getGameAtUrl(Document doc) throws IOException
  {
    if (isGameFinal(doc))
    {
      final int gameStatsIndex = 1;
      Elements tables = doc.select("div.story-container div.mod-content table.mod-data");
      LOG.info("Found " + tables.size() + " tables in page");
      if (tables.size() > gameStatsIndex)
      {
        return getGameFromTableHtml(doc, tables.get(gameStatsIndex));
      }
    }
    return null;
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

  boolean isGameFinal(Document doc)
  {
    Elements spans = doc.select("p#gameStatusBarText");
    return !spans.isEmpty() && spans.get(0).text().toLowerCase().contains("final");
  }

  Game getGameFromTableHtml(Document doc, Element table)
  {
    TeamGame visitingTeamGame = getTeamGame(doc, table, 1, TeamRole.VISITING);
    TeamGame homeTeamGame = getTeamGame(doc, table, 2, TeamRole.HOME);
    if (homeTeamGame != null && visitingTeamGame != null)
    {
      LOG.info("Creating game with teams " + visitingTeamGame + " at " + homeTeamGame);
      Game game = new Game();
      homeTeamGame.setGame(game);
      visitingTeamGame.setGame(game);
      game.setTeamGames(Arrays.asList(homeTeamGame, visitingTeamGame));
      return game;
    }
    return null;
  }

  TeamGame getTeamGame(Document doc, Element table, int column, TeamRole role)
  {
    String abbrev = getTeamAbbrev(table, column);
    Team team = this.teamsByAbbrev.get(abbrev.trim().toUpperCase());
    if (team != null)
    {
      LOG.info("Processing game for team " + team);
      List<String> rawData = getTeamRawData(table, column);
      TeamGameStats stats = WebGameStatsParser.parse(rawData);
      if (stats != null)
      {
        switch (role)
        {
        case HOME:
          stats.setFinalScore(getFinalScoreHome(doc));
          break;
        case VISITING:
          stats.setFinalScore(getFinalScoreVisiting(doc));
          break;
        }

        TeamGame teamGame = new TeamGame();
        stats.setTeamGame(teamGame);
        teamGame.setRole(role);
        teamGame.setTeam(team);
        teamGame.setStats(stats);
        return teamGame;
      }
    }
    return null;
  }

  String getTeamAbbrev(Element table, int column)
  {
    Elements headers = table.select("thead th");
    LOG.info("Found " + headers.size() + " headers in table");
    return headers.size() >= column ? headers.get(column).text() : null;
  }

  List<String> getTeamRawData(Element table, int column)
  {
    ArrayList<Element> dataElements = new ArrayList<Element>();
    Elements rowElements = table.select("tbody tr");
    for (Element row : rowElements)
    {
      Elements cellElements = row.select("td");
      if (cellElements.size() >= column)
      {
        dataElements.add(cellElements.get(column));
      }
    }

    ArrayList<String> data = new ArrayList<String>(dataElements.size());
    LOG.info("Found " + dataElements.size() + " data elements in table");
    for (Element element : dataElements)
    {
      data.add(element.text());
    }
    return data;
  }

  Integer getFinalScoreHome(Document doc)
  {
    Elements spans = doc.select("div.home div.team-info span");
    return !spans.isEmpty() ? IntegerUtils.parse(spans.get(0).text()) : null;
  }

  Integer getFinalScoreVisiting(Document doc)
  {
    Elements spans = doc.select("div.away div.team-info span");
    return !spans.isEmpty() ? IntegerUtils.parse(spans.get(0).text()) : null;
  }
}
