package net.seabears.stats.football;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ParseDateTest
    extends TestCase
{
  /**
   * Create the test case
   * 
   * @param testName
   *          name of the test case
   */
  public ParseDateTest(String testName)
  {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(ParseDateTest.class);
  }

  public void testParseDate() throws ParseException
  {
    Date date = parseDate("MMMM d, yyyy");
    System.err.println(date);
    Date time = parseTime("h:mm a");
    System.err.println(time);
    date.setTime(date.getTime() + time.getTime());
    System.err.println(date);
  }

  public void testParseTime() throws ParseException
  {

  }

  private final static String DATE_TEXT = "8:30 PM ET, September 23, 2013";

  private Date parseTime(String format) throws ParseException
  {
    System.err.println(format);
    SimpleDateFormat timeFormat = new SimpleDateFormat(format);
    timeFormat.setLenient(true);
    timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return timeFormat.parse(DATE_TEXT);
  }

  private Date parseDate(String format) throws ParseException
  {
    System.err.println(format);
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    dateFormat.setLenient(true);
    return dateFormat.parse(DATE_TEXT.replaceFirst("^.*?,\\s*", ""));
  }
}
