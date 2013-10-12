package net.seabears.stats.football;

import java.util.Calendar;
import java.util.Date;

public class FootballUtils
{
  private FootballUtils()
  {
    throw new UnsupportedOperationException();
  }

  public static int getSeason(Date date)
  {
    if (date == null)
    {
      throw new IllegalArgumentException("date must not be null");
    }

    // return the date's year, unless it's January (get the previous year)
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.YEAR) - (cal.get(Calendar.MONTH) < 2 ? 1 : 0);
  }
}
