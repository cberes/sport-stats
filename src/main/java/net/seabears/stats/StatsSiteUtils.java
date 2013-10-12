package net.seabears.stats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

public class StatsSiteUtils
{
  private static final Logger LOG = Logger.getLogger(StatsSiteUtils.class.getName());

  public static final String BASE_URL = System.getProperty("net.seabears.stats.url");

  private StatsSiteUtils()
  {
    throw new UnsupportedOperationException();
  }

  public static SimpleDateFormat getDateFormat()
  {
    SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy");
    format.setLenient(true);
    return format;
  }

  public static SimpleDateFormat getTimeFormat()
  {
    SimpleDateFormat format = new SimpleDateFormat("h:mm a");
    format.setLenient(true);
    format.setTimeZone(TimeZone.getTimeZone("UTC"));
    return format;
  }

  public static Date getGameDate(String gameDateText) throws ParseException
  {
    // remove time from date-time string
    Date date = getDateFormat().parse(gameDateText.replaceFirst("^.*?,\\s*", ""));

    try
    {
      // parse time and add to the date
      Date time = getTimeFormat().parse(gameDateText);
      date.setTime(date.getTime() + time.getTime());
    } catch (ParseException e)
    {
      LOG.warning(e.getMessage());
    }

    return date;
  }
}
