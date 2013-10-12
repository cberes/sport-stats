package net.seabears.stats.football;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.seabears.db.Database;
import net.seabears.stats.football.objects.Game;
import net.seabears.stats.football.objects.Team;
import net.seabears.stats.football.objects.TeamGame;
import net.seabears.stats.football.objects.TeamGameStats;
import net.seabears.stats.football.population.web.WebGamePopulator;

import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/** Football */
public class App
{
  public static final String DATABASE_PATH = System.getProperty("net.seabears.stats.football.db");

  public static void main(String[] args) throws SQLiteException, IOException
  {
    Database db = new Database(DATABASE_PATH);
    List<Team> teams = getTeams(db);
    if (!teams.isEmpty())
    {
      WebGamePopulator populator = new WebGamePopulator(teams);
      List<Game> games = populator.populate(2013, 5, 5);
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
