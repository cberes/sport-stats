package net.seabears.stats;

import java.util.logging.Logger;

public class IntegerUtils
{
  private final static Logger LOG = Logger.getLogger(IntegerUtils.class.getName());

  private IntegerUtils()
  {
    throw new UnsupportedOperationException();
  }

  public static Integer parse(String s)
  {
    try
    {
      return Integer.parseInt(s);
    } catch (NumberFormatException e)
    {
      LOG.warning(e.getMessage());
      return null;
    }
  }
}
