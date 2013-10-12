package net.seabears.stats.hockey;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.seabears.db.Database;
import net.seabears.stats.hockey.objects.Game;
import net.seabears.stats.hockey.objects.Team;
import net.seabears.stats.hockey.objects.TeamGame;
import net.seabears.stats.hockey.objects.TeamGameStats;
import net.seabears.stats.hockey.population.web.WebGamePopulator;

import org.apache.commons.lang3.time.DateUtils;

import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/** Hockey */
public class App
{
  public static final String DATABASE_PATH = System.getProperty("net.seabears.stats.hockey.db");

  public static void main(String[] args) throws SQLiteException, IOException, ParseException
  {
    Database db = new Database(DATABASE_PATH);
    List<Team> teams = getTeams(db);
    if (!teams.isEmpty())
    {
      WebGamePopulator populator = new WebGamePopulator(teams);
      List<Game> games = populator.populate(2013,
          new SimpleDateFormat("yyyy-MM-dd").parse("2013-10-11"),
          DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
      for (Game game : games)
      {
        db.execute(game.getInsertString());
        Integer gameId = db.getLastId(Integer.class);
        if (gameId != null && gameId > 0)
        {
          game.setId(gameId);
          for (TeamGame teamGame : game.getTeamGames())
          {
            teamGame.setGameId(gameId);
            db.execute(teamGame.getInsertString());
            Integer teamGameId = db.getLastId(Integer.class);
            if (teamGameId != null && teamGameId > 0)
            {
              teamGame.setId(teamGameId);
              TeamGameStats stats = teamGame.getStats();
              stats.setTeamGameId(teamGameId);
              db.execute(stats.getInsertString());
              Integer statsId = db.getLastId(Integer.class);
              if (statsId != null && statsId > 0)
              {
                stats.setId(statsId);
              }
            }
          }
          System.out.println(game);
        }
      }
    }
  }

  public static List<Team> getTeams(Database db) throws SQLiteException
  {
    ArrayList<Team> teams = new ArrayList<Team>();
    SQLiteStatement st = db.statement("SELECT * FROM team");
    try
    {
      while (st.step())
      {
        Team team = new Team();
        teams.add(team);
        team.setId(st.columnInt(0));
        team.setCity(st.columnString(1));
        team.setMascot(st.columnString(2));
        team.setAbbrev(st.columnString(3));
      }
    } finally
    {
      st.dispose();
    }
    return teams;
  }
}
