package net.seabears.stats;

import org.apache.commons.lang3.StringUtils;

public enum TeamRole
{
  HOME,
  VISITING;

  public String toString()
  {
    return this.name().charAt(0) + StringUtils.EMPTY;
  }
}
