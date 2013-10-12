package net.seabears.stats.hockey;

import java.io.IOException;
import java.text.ParseException;

import net.seabears.stats.hockey.App;

import org.junit.Test;

import com.almworks.sqlite4java.SQLiteException;

public class HockeyTest
{
  @Test
  public void test() throws SQLiteException, IOException, ParseException
  {
    App.main(null);
  }
}
