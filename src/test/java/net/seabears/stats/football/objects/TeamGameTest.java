package net.seabears.stats.football.objects;

import net.seabears.stats.TeamRole;
import net.seabears.stats.football.objects.TeamGame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TeamGameTest
{
  @Test
  public void test()
  {
    TeamGame tg = new TeamGame();
    tg.setGameId(1);
    tg.setId(2);
    tg.setRole(TeamRole.HOME);
    tg.setTeamId(3);

    System.err.println(tg.getDeleteString());
    System.err.println(tg.getInsertString());
    System.err.println(tg.getUpdateString());
  }
}
