package net.seabears.stats.hockey;

import java.util.Calendar;
import java.util.Date;

public class HockeyUtils
{
  private HockeyUtils()
  {
    throw new UnsupportedOperationException();
  }

  public static int getSeason(Date date)
  {
    if (date == null)
    {
      throw new IllegalArgumentException("date must not be null");
    }

    // return the date's year, unless it's July (get the previous year)
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.YEAR) - (cal.get(Calendar.MONTH) < 6 ? 1 : 0);
  }
}
