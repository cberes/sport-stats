package net.seabears.stats.football.objects;

import net.seabears.stats.football.objects.Team;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TeamTest
{
  @Test
  public void test()
  {
    Team t = new Team();
    t.setAbbrev("BUF");
    t.setCity("Buffalo");
    t.setMascot("Bills");

    System.err.println(t.getDeleteString());
    System.err.println(t.getInsertString());
    System.err.println(t.getUpdateString());
  }
}
