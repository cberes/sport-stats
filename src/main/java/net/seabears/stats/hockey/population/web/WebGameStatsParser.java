package net.seabears.stats.hockey.population.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.seabears.stats.IntegerUtils;
import net.seabears.stats.hockey.objects.TeamGameStats;

public final class WebGameStatsParser
{
  enum Stat
  {
    GOALS("G"),
    SHOTS("SOG"),
    MISSED_SHOTS("MS"),
    SHOTS_BLOCKED("BS"),
    PENALTIES("PN"),
    PENALTIES_IN_MINUTES("PIM"),
    HITS("HT"),
    TAKE_AWAYS("TK"),
    GIVE_AWAYS("GV"),
    FACEOFFS_WON("FW"),
    FACEOFFS_LOST("FL");

    private final String header;

    private Stat(String header)
    {
      this.header = header;
    }

    public String getHeader()
    {
      return header;
    }

    static Map<Stat, Integer> indicesByStat(List<String> headers)
    {
      if (headers == null)
      {
        return Collections.<Stat, Integer> emptyMap();
      }

      HashMap<String, Stat> statsByHeader = new HashMap<String, Stat>(Stat.values().length);
      for (Stat stat : Stat.values())
      {
        statsByHeader.put(stat.header, stat);
      }

      HashMap<Stat, Integer> map = new HashMap<Stat, Integer>(Stat.values().length);
      for (int i = 0; i < headers.size(); ++i)
      {
        String header = headers.get(i);
        Stat stat = statsByHeader.get(header != null ? header.trim().toUpperCase() : null);
        if (stat != null)
        {
          map.put(stat, i);
        }
      }
      return map;
    }
  }

  private WebGameStatsParser()
  {
    throw new UnsupportedOperationException();
  }

  public static TeamGameStats parse(List<String> headers, List<List<String>> data)
  {
    Map<Stat, Integer> indicesByStat = Stat.indicesByStat(headers);
    if (headers.isEmpty())
    {
      return null;
    }

    HashMap<Stat, Integer> values = new HashMap<Stat, Integer>(indicesByStat.size());
    for (Map.Entry<Stat, Integer> entry : indicesByStat.entrySet())
    {
      // sum all the cells in this column
      Integer total = null;
      for (List<String> row : data)
      {
        if (entry.getValue() < row.size())
        {
          Integer value = IntegerUtils.parse(row.get(entry.getValue()));
          if (value != null)
          {
            total = total != null ? total + value : value;
          }
        }
      }

      // if there's a value for the total, add it to the map
      if (total != null)
      {
        values.put(entry.getKey(), total);
      }
    }

    return !values.isEmpty() ? mapValuesToStats(values) : null;
  }

  static TeamGameStats mapValuesToStats(Map<Stat, Integer> values)
  {
    TeamGameStats stats = new TeamGameStats();
    for (Map.Entry<Stat, Integer> entry : values.entrySet())
    {
      switch (entry.getKey())
      {
      case GOALS:
        stats.setGoals(entry.getValue());
        break;
      case SHOTS:
        stats.setShots(entry.getValue());
        break;
      case MISSED_SHOTS:
        stats.setMissedShots(entry.getValue());
        break;
      case SHOTS_BLOCKED:
        stats.setShotsBlocked(entry.getValue());
        break;
      case PENALTIES:
        stats.setPenalties(entry.getValue());
        break;
      case PENALTIES_IN_MINUTES:
        stats.setPenaltiesInMinutes(entry.getValue());
        break;
      case HITS:
        stats.setHits(entry.getValue());
        break;
      case TAKE_AWAYS:
        stats.setTakeAways(entry.getValue());
        break;
      case GIVE_AWAYS:
        stats.setGiveAways(entry.getValue());
        break;
      case FACEOFFS_WON:
        stats.setFaceoffsWon(entry.getValue());
        break;
      case FACEOFFS_LOST:
        stats.setFaceoffsLost(entry.getValue());
        break;
      }
    }
    return stats;
  }
}
