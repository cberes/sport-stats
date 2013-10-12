package net.seabears.stats.football.population.web;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.seabears.stats.IntegerUtils;
import net.seabears.stats.football.objects.TeamGameStats;

public final class WebGameStatsParser
{
  private static int INDEX_FIRST_DOWNS = 0;
  private static int INDEX_FIRST_DOWNS_PASSING = 1;
  private static int INDEX_FIRST_DOWNS_RUSHING = 2;
  private static int INDEX_FIRST_DOWNS_PENALTIES = 3;
  private static int INDEX_THIRD_DOWNS = 4;
  private static int INDEX_FOURTH_DOWNS = 5;
  private static int INDEX_TOTAL_PLAYS = 6;
  private static int INDEX_TOTAL_YARDS = 7;
  private static int INDEX_TOTAL_DRIVES = 9;
  private static int INDEX_PASSING_YARDS = 10;
  private static int INDEX_PASSING_PLAYS = 11;
  private static int INDEX_INTERCEPTIONS = 13;
  private static int INDEX_SACKS = 14;
  private static int INDEX_RUSHING_YARDS = 15;
  private static int INDEX_RUSHING_ATTEMPTS = 16;
  private static int INDEX_REDZONES = 18;
  private static int INDEX_PENALTIES = 19;
  private static int INDEX_TURNOVERS = 20;
  private static int INDEX_FUMBLES_LOST = 21;
  private static int INDEX_TOUCHDOWNS_DEFENSIVE_SPECIAL_TEAMS = 23;
  private static int INDEX_POSSESSION_TIME = 24;

  private static final Pattern intPairPattern = Pattern.compile("(\\d+)\\s*[-:]\\s*(\\d+)");

  private WebGameStatsParser()
  {
    throw new UnsupportedOperationException();
  }

  public static TeamGameStats parse(List<String> rawData)
  {
    TeamGameStats stats = new TeamGameStats();
    setTotalFirstDowns(stats, rawData);
    setPassingFirstDowns(stats, rawData);
    setRushingFirstDowns(stats, rawData);
    setPenaltyFirstDowns(stats, rawData);
    setThirdDowns(stats, rawData);
    setFourthDowns(stats, rawData);
    setTotalPlays(stats, rawData);
    setTotalYards(stats, rawData);
    setPassingYards(stats, rawData);
    setPassing(stats, rawData);
    setInterceptions(stats, rawData);
    setSacks(stats, rawData);
    setRushingYards(stats, rawData);
    setRushingPlays(stats, rawData);
    setRedZones(stats, rawData);
    setPenalties(stats, rawData);
    setTurnovers(stats, rawData);
    setFumblesLost(stats, rawData);
    setDefensiveTouchdowns(stats, rawData);
    setPossessionTime(stats, rawData);
    return stats;
  }

  static int offset(List<String> rawData)
  {
    return rawData.size() - (INDEX_POSSESSION_TIME + 1);
  }

  static List<Integer> parseIntegerPair(String text)
  {
    Matcher matcher = intPairPattern.matcher(text);
    if (matcher.find())
    {
      return Arrays.asList(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }
    return null;
  }

  static void setTotalFirstDowns(TeamGameStats stats, List<String> rawData)
  {
    stats.setTotalFirstDowns(IntegerUtils.parse(rawData.get(INDEX_FIRST_DOWNS)));
  }

  static void setPassingFirstDowns(TeamGameStats stats, List<String> rawData)
  {
    stats.setPassingFirstDowns(IntegerUtils.parse(rawData.get(INDEX_FIRST_DOWNS_PASSING)));
  }

  static void setRushingFirstDowns(TeamGameStats stats, List<String> rawData)
  {
    stats.setRushingFirstDowns(IntegerUtils.parse(rawData.get(INDEX_FIRST_DOWNS_RUSHING)));
  }

  static void setPenaltyFirstDowns(TeamGameStats stats, List<String> rawData)
  {
    stats.setPenaltyFirstDowns(IntegerUtils.parse(rawData.get(INDEX_FIRST_DOWNS_PENALTIES)));
  }

  static void setThirdDowns(TeamGameStats stats, List<String> rawData)
  {
    List<Integer> ints = parseIntegerPair(rawData.get(INDEX_THIRD_DOWNS));
    stats.setThirdDownsConverted(ints != null ? ints.get(0) : null);
    stats.setThirdDownsAttempted(ints != null ? ints.get(1) : null);
  }

  static void setFourthDowns(TeamGameStats stats, List<String> rawData)
  {
    List<Integer> ints = parseIntegerPair(rawData.get(INDEX_FOURTH_DOWNS));
    stats.setFourthDownsConverted(ints != null ? ints.get(0) : null);
    stats.setFourthDownsAttempted(ints != null ? ints.get(1) : null);
  }

  static void setTotalPlays(TeamGameStats stats, List<String> rawData)
  {
    stats.setTotalRushingPassingPlays(IntegerUtils.parse(rawData.get(INDEX_TOTAL_PLAYS)));
  }

  static void setTotalYards(TeamGameStats stats, List<String> rawData)
  {
    stats.setTotalNetYards(IntegerUtils.parse(rawData.get(INDEX_TOTAL_YARDS)));
  }

  static void setTotalDrives(TeamGameStats stats, List<String> rawData)
  {
    stats.setTotalDrives(IntegerUtils.parse(rawData.get(INDEX_TOTAL_DRIVES)));
  }

  static void setPassingYards(TeamGameStats stats, List<String> rawData)
  {
    stats.setNetYardsPassing(IntegerUtils.parse(rawData.get(INDEX_PASSING_YARDS + offset(rawData))));
  }

  static void setPassing(TeamGameStats stats, List<String> rawData)
  {
    List<Integer> ints = parseIntegerPair(rawData.get(INDEX_PASSING_PLAYS + offset(rawData)));
    stats.setPassCompletions(ints != null ? ints.get(0) : null);
    stats.setPassAttempts(ints != null ? ints.get(1) : null);
  }

  static void setInterceptions(TeamGameStats stats, List<String> rawData)
  {
    stats.setPassInterceptions(IntegerUtils.parse(rawData.get(INDEX_INTERCEPTIONS + offset(rawData))));
  }

  static void setSacks(TeamGameStats stats, List<String> rawData)
  {
    List<Integer> ints = parseIntegerPair(rawData.get(INDEX_SACKS + offset(rawData)));
    stats.setTimesSacked(ints != null ? ints.get(0) : null);
    stats.setSackedYards(ints != null ? ints.get(1) : null);
  }

  static void setRushingYards(TeamGameStats stats, List<String> rawData)
  {
    stats.setNetYardsRushing(IntegerUtils.parse(rawData.get(INDEX_RUSHING_YARDS + offset(rawData))));
  }

  static void setRushingPlays(TeamGameStats stats, List<String> rawData)
  {
    stats.setTotalRushingPlays(IntegerUtils.parse(rawData.get(INDEX_RUSHING_ATTEMPTS + offset(rawData))));
  }

  static void setRedZones(TeamGameStats stats, List<String> rawData)
  {
    List<Integer> ints = parseIntegerPair(rawData.get(INDEX_REDZONES + offset(rawData)));
    stats.setRedZoneConversions(ints != null ? ints.get(0) : null);
    stats.setRedZoneAttempts(ints != null ? ints.get(1) : null);
  }

  static void setPenalties(TeamGameStats stats, List<String> rawData)
  {
    List<Integer> ints = parseIntegerPair(rawData.get(INDEX_PENALTIES + offset(rawData)));
    stats.setPenalties(ints != null ? ints.get(0) : null);
    stats.setPenaltyYardage(ints != null ? ints.get(1) : null);
  }

  static void setTurnovers(TeamGameStats stats, List<String> rawData)
  {
    stats.setTurnovers(IntegerUtils.parse(rawData.get(INDEX_TURNOVERS + offset(rawData))));
  }

  static void setFumblesLost(TeamGameStats stats, List<String> rawData)
  {
    stats.setFumblesLost(IntegerUtils.parse(rawData.get(INDEX_FUMBLES_LOST + offset(rawData))));
  }

  static void setDefensiveTouchdowns(TeamGameStats stats, List<String> rawData)
  {
    stats.setTouchdownsDefenseSpecial(IntegerUtils.parse(rawData.get(INDEX_TOUCHDOWNS_DEFENSIVE_SPECIAL_TEAMS + offset(rawData))));
  }

  static void setPossessionTime(TeamGameStats stats, List<String> rawData)
  {
    List<Integer> ints = parseIntegerPair(rawData.get(INDEX_POSSESSION_TIME + offset(rawData)));
    stats.setTimeOfPossession(ints != null ? ints.get(1) + (ints.get(0) * 60) : null);
  }
}
