package net.seabears.stats.football.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import net.seabears.db.DatabaseEntity;

@Entity(name = "team_game_stats")
public class TeamGameStats extends DatabaseEntity
{
  @Id
  @Column(name = "_id")
  @GeneratedValue
  private int id;

  @Column(name = "team_game_id", nullable = false)
  private int teamGameId;

  @OneToOne
  @JoinColumn(name = "team_game_id", nullable = false)
  private TeamGame teamGame;

  @Column(name = "total_first_downs")
  private Integer totalFirstDowns;

  @Column(name = "rushing_first_downs")
  private Integer rushingFirstDowns;

  @Column(name = "passing_first_downs")
  private Integer passingFirstDowns;

  @Column(name = "penalty_first_downs")
  private Integer penaltyFirstDowns;

  @Column(name = "third_downs_attempted")
  private Integer thirdDownsAttempted;

  @Column(name = "third_downs_converted")
  private Integer thirdDownsConverted;

  @Column(name = "fourth_downs_attempted")
  private Integer fourthDownsAttempted;

  @Column(name = "fourth_downs_converted")
  private Integer fourthDownsConverted;

  @Column(name = "total_drives")
  private Integer totalDrives;

  @Column(name = "total_net_yards")
  private Integer totalNetYards;

  @Column(name = "total_rushing_passing_plays")
  private Integer totalRushingPassingPlays;

  @Column(name = "net_yards_rushing")
  private Integer netYardsRushing;

  @Column(name = "total_rushing_plays")
  private Integer totalRushingPlays;

  @Column(name = "times_tackled_for_a_loss")
  private Integer timesTackledForALoss;

  @Column(name = "tackled_for_a_loss_yards")
  private Integer tackledForALossYards;

  @Column(name = "net_yards_passing")
  private Integer netYardsPassing;

  @Column(name = "times_sacked")
  private Integer timesSacked;

  @Column(name = "sacked_yards")
  private Integer sackedYards;

  @Column(name = "gross_yards_passing")
  private Integer grossYardsPassing;

  @Column(name = "pass_completions")
  private Integer passCompletions;

  @Column(name = "pass_attempts")
  private Integer passAttempts;

  @Column(name = "pass_interceptions")
  private Integer passInterceptions;

  @Column
  private Integer kickoffs;

  @Column(name = "kickoffs_in_endzone")
  private Integer kickoffsInEndzone;

  @Column
  private Integer touchbacks;

  @Column
  private Integer punts;

  @Column(name = "punts_average")
  private Double puntsAverage;

  @Column(name = "punts_blocked")
  private Integer puntsBlocked;

  @Column(name = "net_punting_average")
  private Double netPuntingAverage;

  @Column(name = "field_goals_blocked")
  private Integer fieldGoalsBlocked;

  @Column(name = "pats_blocked")
  private Integer patsBlocked;

  @Column(name = "total_return_yardage")
  private Integer totalReturnYardage;

  @Column(name = "punt_returns")
  private Integer puntReturns;

  @Column(name = "punt_return_yardage")
  private Integer puntReturnYardage;

  @Column(name = "kickoff_returns")
  private Integer kickoffReturns;

  @Column(name = "kickoff_return_yardage")
  private Integer kickoffReturnYardage;

  @Column(name = "interception_returns")
  private Integer interceptionReturns;

  @Column(name = "interception_return_yardage")
  private Integer interceptionReturnYardage;

  @Column
  private Integer penalties;

  @Column(name = "penalty_yardage")
  private Integer penaltyYardage;

  @Column
  private Integer fumbles;

  @Column(name = "fumbles_lost")
  private Integer fumblesLost;

  @Column(name = "fumble_yardage")
  private Integer fumbleYardage;

  @Column
  private Integer turnovers;

  @Column
  private Integer touchdowns;

  @Column(name = "touchdowns_rushing")
  private Integer touchdownsRushing;

  @Column(name = "touchdowns_passing")
  private Integer touchdownsPassing;

  @Column(name = "touchdowns_interceptions")
  private Integer touchdownsInterceptions;

  @Column(name = "touchdowns_defensive_special")
  private Integer touchdownsDefenseSpecial;

  @Column(name = "touchdowns_kickoff_returns")
  private Integer touchdownsKickoffReturns;

  @Column(name = "touchdowns_fumble_returns")
  private Integer touchdownsFumbleReturns;

  @Column(name = "touchdowns_punt_returns")
  private Integer touchdownsPuntReturns;

  @Column(name = "extra_points_made")
  private Integer extraPointsMade;

  @Column(name = "extra_points_attempted")
  private Integer extraPointsAttempted;

  @Column(name = "extra_points_made_kicking")
  private Integer extraPointsMadeKicking;

  @Column(name = "extra_points_attempted_kicking")
  private Integer extraPointsAttemptedKicking;

  @Column(name = "extra_points_made_conversions")
  private Integer extraPointsMadeConversions;

  @Column(name = "extra_points_attempted_conversions")
  private Integer extraPointsAttemptedConversions;

  @Column(name = "field_goals_made")
  private Integer fieldGoalsMade;

  @Column(name = "field_goals_attempted")
  private Integer fieldGoalsAttempted;

  @Column(name = "red_zone_attempts")
  private Integer redZoneAttempts;

  @Column(name = "red_zone_conversions")
  private Integer redZoneConversions;

  @Column(name = "goal_to_go_attempts")
  private Integer goalToGoAttempts;

  @Column(name = "goal_to_go_conversions")
  private Integer goalToGoConversions;

  @Column
  private Integer safeties;

  @Column(name = "final_score")
  private Integer finalScore;

  @Column(name = "time_of_possession")
  private Integer timeOfPossession;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getTeamGameId()
  {
    return teamGameId;
  }

  public void setTeamGameId(int teamGameId)
  {
    this.teamGameId = teamGameId;
  }

  public Integer getTotalFirstDowns()
  {
    return totalFirstDowns;
  }

  public void setTotalFirstDowns(Integer totalFirstDowns)
  {
    this.totalFirstDowns = totalFirstDowns;
  }

  public Integer getRushingFirstDowns()
  {
    return rushingFirstDowns;
  }

  public void setRushingFirstDowns(Integer rushingFirstDowns)
  {
    this.rushingFirstDowns = rushingFirstDowns;
  }

  public Integer getPassingFirstDowns()
  {
    return passingFirstDowns;
  }

  public void setPassingFirstDowns(Integer passingFirstDowns)
  {
    this.passingFirstDowns = passingFirstDowns;
  }

  public Integer getPenaltyFirstDowns()
  {
    return penaltyFirstDowns;
  }

  public void setPenaltyFirstDowns(Integer penaltyFirstDowns)
  {
    this.penaltyFirstDowns = penaltyFirstDowns;
  }

  public Integer getThirdDownsAttempted()
  {
    return thirdDownsAttempted;
  }

  public void setThirdDownsAttempted(Integer thirdDownsAttempted)
  {
    this.thirdDownsAttempted = thirdDownsAttempted;
  }

  public Integer getThirdDownsConverted()
  {
    return thirdDownsConverted;
  }

  public void setThirdDownsConverted(Integer thirdDownsConverted)
  {
    this.thirdDownsConverted = thirdDownsConverted;
  }

  public Integer getFourthDownsAttempted()
  {
    return fourthDownsAttempted;
  }

  public void setFourthDownsAttempted(Integer fourthDownsAttempted)
  {
    this.fourthDownsAttempted = fourthDownsAttempted;
  }

  public Integer getFourthDownsConverted()
  {
    return fourthDownsConverted;
  }

  public void setFourthDownsConverted(Integer fourthDownsConverted)
  {
    this.fourthDownsConverted = fourthDownsConverted;
  }

  public Integer getTotalDrives()
  {
    return totalDrives;
  }

  public void setTotalDrives(Integer totalDrives)
  {
    this.totalDrives = totalDrives;
  }

  public Integer getTotalNetYards()
  {
    return totalNetYards;
  }

  public void setTotalNetYards(Integer totalNetYards)
  {
    this.totalNetYards = totalNetYards;
  }

  public Integer getTotalRushingPassingPlays()
  {
    return totalRushingPassingPlays;
  }

  public void setTotalRushingPassingPlays(Integer totalRushingPassingPlays)
  {
    this.totalRushingPassingPlays = totalRushingPassingPlays;
  }

  public Integer getNetYardsRushing()
  {
    return netYardsRushing;
  }

  public void setNetYardsRushing(Integer netYardsRushing)
  {
    this.netYardsRushing = netYardsRushing;
  }

  public Integer getTotalRushingPlays()
  {
    return totalRushingPlays;
  }

  public void setTotalRushingPlays(Integer totalRushingPlays)
  {
    this.totalRushingPlays = totalRushingPlays;
  }

  public Integer getTimesTackledForALoss()
  {
    return timesTackledForALoss;
  }

  public void setTimesTackledForALoss(Integer timesTackledForALoss)
  {
    this.timesTackledForALoss = timesTackledForALoss;
  }

  public Integer getTackledForALossYards()
  {
    return tackledForALossYards;
  }

  public void setTackledForALossYards(Integer tackledForALossYards)
  {
    this.tackledForALossYards = tackledForALossYards;
  }

  public Integer getNetYardsPassing()
  {
    return netYardsPassing;
  }

  public void setNetYardsPassing(Integer netYardsPassing)
  {
    this.netYardsPassing = netYardsPassing;
  }

  public Integer getTimesSacked()
  {
    return timesSacked;
  }

  public void setTimesSacked(Integer timesSacked)
  {
    this.timesSacked = timesSacked;
  }

  public Integer getSackedYards()
  {
    return sackedYards;
  }

  public void setSackedYards(Integer sackedYards)
  {
    this.sackedYards = sackedYards;
  }

  public Integer getGrossYardsPassing()
  {
    return grossYardsPassing;
  }

  public void setGrossYardsPassing(Integer grossYardsPassing)
  {
    this.grossYardsPassing = grossYardsPassing;
  }

  public Integer getPassCompletions()
  {
    return passCompletions;
  }

  public void setPassCompletions(Integer passCompletions)
  {
    this.passCompletions = passCompletions;
  }

  public Integer getPassAttempts()
  {
    return passAttempts;
  }

  public void setPassAttempts(Integer passAttempts)
  {
    this.passAttempts = passAttempts;
  }

  public Integer getPassInterceptions()
  {
    return passInterceptions;
  }

  public void setPassInterceptions(Integer passInterceptions)
  {
    this.passInterceptions = passInterceptions;
  }

  public Integer getKickoffs()
  {
    return kickoffs;
  }

  public void setKickoffs(Integer kickoffs)
  {
    this.kickoffs = kickoffs;
  }

  public Integer getKickoffsInEndzone()
  {
    return kickoffsInEndzone;
  }

  public void setKickoffsInEndzone(Integer kickoffsInEndzone)
  {
    this.kickoffsInEndzone = kickoffsInEndzone;
  }

  public Integer getTouchbacks()
  {
    return touchbacks;
  }

  public void setTouchbacks(Integer touchbacks)
  {
    this.touchbacks = touchbacks;
  }

  public Integer getPunts()
  {
    return punts;
  }

  public void setPunts(Integer punts)
  {
    this.punts = punts;
  }

  public Double getPuntsAverage()
  {
    return puntsAverage;
  }

  public void setPuntsAverage(Double puntsAverage)
  {
    this.puntsAverage = puntsAverage;
  }

  public Integer getPuntsBlocked()
  {
    return puntsBlocked;
  }

  public void setPuntsBlocked(Integer puntsBlocked)
  {
    this.puntsBlocked = puntsBlocked;
  }

  public Double getNetPuntingAverage()
  {
    return netPuntingAverage;
  }

  public void setNetPuntingAverage(Double netPuntingAverage)
  {
    this.netPuntingAverage = netPuntingAverage;
  }

  public Integer getFieldGoalsBlocked()
  {
    return fieldGoalsBlocked;
  }

  public void setFieldGoalsBlocked(Integer fieldGoalsBlocked)
  {
    this.fieldGoalsBlocked = fieldGoalsBlocked;
  }

  public Integer getPatsBlocked()
  {
    return patsBlocked;
  }

  public void setPatsBlocked(Integer patsBlocked)
  {
    this.patsBlocked = patsBlocked;
  }

  public Integer getTotalReturnYardage()
  {
    return totalReturnYardage;
  }

  public void setTotalReturnYardage(Integer totalReturnYardage)
  {
    this.totalReturnYardage = totalReturnYardage;
  }

  public Integer getPuntReturns()
  {
    return puntReturns;
  }

  public void setPuntReturns(Integer puntReturns)
  {
    this.puntReturns = puntReturns;
  }

  public Integer getPuntReturnYardage()
  {
    return puntReturnYardage;
  }

  public void setPuntReturnYardage(Integer puntReturnYardage)
  {
    this.puntReturnYardage = puntReturnYardage;
  }

  public Integer getKickoffReturns()
  {
    return kickoffReturns;
  }

  public void setKickoffReturns(Integer kickoffReturns)
  {
    this.kickoffReturns = kickoffReturns;
  }

  public Integer getKickoffReturnYardage()
  {
    return kickoffReturnYardage;
  }

  public void setKickoffReturnYardage(Integer kickoffReturnYardage)
  {
    this.kickoffReturnYardage = kickoffReturnYardage;
  }

  public Integer getInterceptionReturns()
  {
    return interceptionReturns;
  }

  public void setInterceptionReturns(Integer interceptionReturns)
  {
    this.interceptionReturns = interceptionReturns;
  }

  public Integer getInterceptionReturnYardage()
  {
    return interceptionReturnYardage;
  }

  public void setInterceptionReturnYardage(Integer interceptionReturnYardage)
  {
    this.interceptionReturnYardage = interceptionReturnYardage;
  }

  public Integer getPenalties()
  {
    return penalties;
  }

  public void setPenalties(Integer penalties)
  {
    this.penalties = penalties;
  }

  public Integer getPenaltyYardage()
  {
    return penaltyYardage;
  }

  public void setPenaltyYardage(Integer penaltyYardage)
  {
    this.penaltyYardage = penaltyYardage;
  }

  public Integer getFumbles()
  {
    return fumbles;
  }

  public void setFumbles(Integer fumbles)
  {
    this.fumbles = fumbles;
  }

  public Integer getFumblesLost()
  {
    return fumblesLost;
  }

  public void setFumblesLost(Integer fumblesLost)
  {
    this.fumblesLost = fumblesLost;
  }

  public Integer getFumbleYardage()
  {
    return fumbleYardage;
  }

  public void setFumbleYardage(Integer fumbleYardage)
  {
    this.fumbleYardage = fumbleYardage;
  }

  public Integer getTurnovers()
  {
    return turnovers;
  }

  public void setTurnovers(Integer turnovers)
  {
    this.turnovers = turnovers;
  }

  public Integer getTouchdowns()
  {
    return touchdowns;
  }

  public void setTouchdowns(Integer touchdowns)
  {
    this.touchdowns = touchdowns;
  }

  public Integer getTouchdownsRushing()
  {
    return touchdownsRushing;
  }

  public void setTouchdownsRushing(Integer touchdownsRushing)
  {
    this.touchdownsRushing = touchdownsRushing;
  }

  public Integer getTouchdownsPassing()
  {
    return touchdownsPassing;
  }

  public void setTouchdownsPassing(Integer touchdownsPassing)
  {
    this.touchdownsPassing = touchdownsPassing;
  }

  public Integer getTouchdownsDefenseSpecial()
  {
    return touchdownsDefenseSpecial;
  }

  public void setTouchdownsDefenseSpecial(Integer touchdownsDefenseSpecial)
  {
    this.touchdownsDefenseSpecial = touchdownsDefenseSpecial;
  }

  public Integer getTouchdownsInterceptions()
  {
    return touchdownsInterceptions;
  }

  public void setTouchdownsInterceptions(Integer touchdownsInterceptions)
  {
    this.touchdownsInterceptions = touchdownsInterceptions;
  }

  public Integer getTouchdownsKickoffReturns()
  {
    return touchdownsKickoffReturns;
  }

  public void setTouchdownsKickoffReturns(Integer touchdownsKickoffReturns)
  {
    this.touchdownsKickoffReturns = touchdownsKickoffReturns;
  }

  public Integer getTouchdownsFumbleReturns()
  {
    return touchdownsFumbleReturns;
  }

  public void setTouchdownsFumbleReturns(Integer touchdownsFumbleReturns)
  {
    this.touchdownsFumbleReturns = touchdownsFumbleReturns;
  }

  public Integer getTouchdownsPuntReturns()
  {
    return touchdownsPuntReturns;
  }

  public void setTouchdownsPuntReturns(Integer touchdownsPuntReturns)
  {
    this.touchdownsPuntReturns = touchdownsPuntReturns;
  }

  public Integer getExtraPointsMade()
  {
    return extraPointsMade;
  }

  public void setExtraPointsMade(Integer extraPointsMade)
  {
    this.extraPointsMade = extraPointsMade;
  }

  public Integer getExtraPointsAttempted()
  {
    return extraPointsAttempted;
  }

  public void setExtraPointsAttempted(Integer extraPointsAttempted)
  {
    this.extraPointsAttempted = extraPointsAttempted;
  }

  public Integer getExtraPointsMadeKicking()
  {
    return extraPointsMadeKicking;
  }

  public void setExtraPointsMadeKicking(Integer extraPointsMadeKicking)
  {
    this.extraPointsMadeKicking = extraPointsMadeKicking;
  }

  public Integer getExtraPointsAttemptedKicking()
  {
    return extraPointsAttemptedKicking;
  }

  public void setExtraPointsAttemptedKicking(Integer extraPointsAttemptedKicking)
  {
    this.extraPointsAttemptedKicking = extraPointsAttemptedKicking;
  }

  public Integer getExtraPointsMadeConversions()
  {
    return extraPointsMadeConversions;
  }

  public void setExtraPointsMadeConversions(Integer extraPointsMadeConversions)
  {
    this.extraPointsMadeConversions = extraPointsMadeConversions;
  }

  public Integer getExtraPointsAttemptedConversions()
  {
    return extraPointsAttemptedConversions;
  }

  public void setExtraPointsAttemptedConversions(Integer extraPointsAttemptedConversions)
  {
    this.extraPointsAttemptedConversions = extraPointsAttemptedConversions;
  }

  public Integer getFieldGoalsMade()
  {
    return fieldGoalsMade;
  }

  public void setFieldGoalsMade(Integer fieldGoalsMade)
  {
    this.fieldGoalsMade = fieldGoalsMade;
  }

  public Integer getFieldGoalsAttempted()
  {
    return fieldGoalsAttempted;
  }

  public void setFieldGoalsAttempted(Integer fieldGoalsAttempted)
  {
    this.fieldGoalsAttempted = fieldGoalsAttempted;
  }

  public Integer getRedZoneAttempts()
  {
    return redZoneAttempts;
  }

  public void setRedZoneAttempts(Integer redZoneAttempts)
  {
    this.redZoneAttempts = redZoneAttempts;
  }

  public Integer getRedZoneConversions()
  {
    return redZoneConversions;
  }

  public void setRedZoneConversions(Integer redZoneConversions)
  {
    this.redZoneConversions = redZoneConversions;
  }

  public Integer getGoalToGoAttempts()
  {
    return goalToGoAttempts;
  }

  public void setGoalToGoAttempts(Integer goalToGoAttempts)
  {
    this.goalToGoAttempts = goalToGoAttempts;
  }

  public Integer getGoalToGoConversions()
  {
    return goalToGoConversions;
  }

  public void setGoalToGoConversions(Integer goalToGoConversions)
  {
    this.goalToGoConversions = goalToGoConversions;
  }

  public Integer getSafeties()
  {
    return safeties;
  }

  public void setSafeties(Integer safeties)
  {
    this.safeties = safeties;
  }

  public Integer getFinalScore()
  {
    return finalScore;
  }

  public void setFinalScore(Integer finalScore)
  {
    this.finalScore = finalScore;
  }

  public Integer getTimeOfPossession()
  {
    return timeOfPossession;
  }

  public void setTimeOfPossession(Integer timeOfPossession)
  {
    this.timeOfPossession = timeOfPossession;
  }

  public TeamGame getTeamGame()
  {
    return teamGame;
  }

  public void setTeamGame(TeamGame teamGame)
  {
    this.teamGame = teamGame;
    this.setTeamGameId(this.teamGame != null ? this.teamGame.getId() : 0);
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("TeamGameStats [id=").append(id).append(", teamGameId=").append(teamGameId).append(", ");
    if (totalFirstDowns != null)
      builder.append("totalFirstDowns=").append(totalFirstDowns).append(", ");
    if (rushingFirstDowns != null)
      builder.append("rushingFirstDowns=").append(rushingFirstDowns).append(", ");
    if (passingFirstDowns != null)
      builder.append("passingFirstDowns=").append(passingFirstDowns).append(", ");
    if (penaltyFirstDowns != null)
      builder.append("penaltyFirstDowns=").append(penaltyFirstDowns).append(", ");
    if (thirdDownsAttempted != null)
      builder.append("thirdDownsAttempted=").append(thirdDownsAttempted).append(", ");
    if (thirdDownsConverted != null)
      builder.append("thirdDownsConverted=").append(thirdDownsConverted).append(", ");
    if (fourthDownsAttempted != null)
      builder.append("fourthDownsAttempted=").append(fourthDownsAttempted).append(", ");
    if (fourthDownsConverted != null)
      builder.append("fourthDownsConverted=").append(fourthDownsConverted).append(", ");
    if (totalDrives != null)
      builder.append("totalDrives=").append(totalDrives).append(", ");
    if (totalNetYards != null)
      builder.append("totalNetYards=").append(totalNetYards).append(", ");
    if (totalRushingPassingPlays != null)
      builder.append("totalRushingPassingPlays=").append(totalRushingPassingPlays).append(", ");
    if (netYardsRushing != null)
      builder.append("netYardsRushing=").append(netYardsRushing).append(", ");
    if (totalRushingPlays != null)
      builder.append("totalRushingPlays=").append(totalRushingPlays).append(", ");
    if (timesTackledForALoss != null)
      builder.append("timesTackledForALoss=").append(timesTackledForALoss).append(", ");
    if (tackledForALossYards != null)
      builder.append("tackledForALossYards=").append(tackledForALossYards).append(", ");
    if (netYardsPassing != null)
      builder.append("netYardsPassing=").append(netYardsPassing).append(", ");
    if (timesSacked != null)
      builder.append("timesSacked=").append(timesSacked).append(", ");
    if (sackedYards != null)
      builder.append("sackedYards=").append(sackedYards).append(", ");
    if (grossYardsPassing != null)
      builder.append("grossYardsPassing=").append(grossYardsPassing).append(", ");
    if (passCompletions != null)
      builder.append("passCompletions=").append(passCompletions).append(", ");
    if (passAttempts != null)
      builder.append("passAttempts=").append(passAttempts).append(", ");
    if (passInterceptions != null)
      builder.append("passInterceptions=").append(passInterceptions).append(", ");
    if (kickoffs != null)
      builder.append("kickoffs=").append(kickoffs).append(", ");
    if (kickoffsInEndzone != null)
      builder.append("kickoffsInEndzone=").append(kickoffsInEndzone).append(", ");
    if (touchbacks != null)
      builder.append("touchbacks=").append(touchbacks).append(", ");
    if (punts != null)
      builder.append("punts=").append(punts).append(", ");
    if (puntsAverage != null)
      builder.append("puntsAverage=").append(puntsAverage).append(", ");
    if (puntsBlocked != null)
      builder.append("puntsBlocked=").append(puntsBlocked).append(", ");
    if (netPuntingAverage != null)
      builder.append("netPuntingAverage=").append(netPuntingAverage).append(", ");
    if (fieldGoalsBlocked != null)
      builder.append("fieldGoalsBlocked=").append(fieldGoalsBlocked).append(", ");
    if (patsBlocked != null)
      builder.append("patsBlocked=").append(patsBlocked).append(", ");
    if (totalReturnYardage != null)
      builder.append("totalReturnYardage=").append(totalReturnYardage).append(", ");
    if (puntReturns != null)
      builder.append("puntReturns=").append(puntReturns).append(", ");
    if (puntReturnYardage != null)
      builder.append("puntReturnYardage=").append(puntReturnYardage).append(", ");
    if (kickoffReturns != null)
      builder.append("kickoffReturns=").append(kickoffReturns).append(", ");
    if (kickoffReturnYardage != null)
      builder.append("kickoffReturnYardage=").append(kickoffReturnYardage).append(", ");
    if (interceptionReturns != null)
      builder.append("interceptionReturns=").append(interceptionReturns).append(", ");
    if (interceptionReturnYardage != null)
      builder.append("interceptionReturnYardage=").append(interceptionReturnYardage).append(", ");
    if (penalties != null)
      builder.append("penalties=").append(penalties).append(", ");
    if (penaltyYardage != null)
      builder.append("penaltyYardage=").append(penaltyYardage).append(", ");
    if (fumbles != null)
      builder.append("fumbles=").append(fumbles).append(", ");
    if (fumblesLost != null)
      builder.append("fumblesLost=").append(fumblesLost).append(", ");
    if (fumbleYardage != null)
      builder.append("fumbleYardage=").append(fumbleYardage).append(", ");
    if (turnovers != null)
      builder.append("turnovers=").append(turnovers).append(", ");
    if (touchdowns != null)
      builder.append("touchdowns=").append(touchdowns).append(", ");
    if (touchdownsRushing != null)
      builder.append("touchdownsRushing=").append(touchdownsRushing).append(", ");
    if (touchdownsPassing != null)
      builder.append("touchdownsPassing=").append(touchdownsPassing).append(", ");
    if (touchdownsInterceptions != null)
      builder.append("touchdownsInterceptions=").append(touchdownsInterceptions).append(", ");
    if (touchdownsDefenseSpecial != null)
      builder.append("touchdownsDefenseSpecial=").append(touchdownsDefenseSpecial).append(", ");
    if (touchdownsKickoffReturns != null)
      builder.append("touchdownsKickoffReturns=").append(touchdownsKickoffReturns).append(", ");
    if (touchdownsFumbleReturns != null)
      builder.append("touchdownsFumbleReturns=").append(touchdownsFumbleReturns).append(", ");
    if (touchdownsPuntReturns != null)
      builder.append("touchdownsPuntReturns=").append(touchdownsPuntReturns).append(", ");
    if (extraPointsMade != null)
      builder.append("extraPointsMade=").append(extraPointsMade).append(", ");
    if (extraPointsAttempted != null)
      builder.append("extraPointsAttempted=").append(extraPointsAttempted).append(", ");
    if (extraPointsMadeKicking != null)
      builder.append("extraPointsMadeKicking=").append(extraPointsMadeKicking).append(", ");
    if (extraPointsAttemptedKicking != null)
      builder.append("extraPointsAttemptedKicking=").append(extraPointsAttemptedKicking).append(", ");
    if (extraPointsMadeConversions != null)
      builder.append("extraPointsMadeConversions=").append(extraPointsMadeConversions).append(", ");
    if (extraPointsAttemptedConversions != null)
      builder.append("extraPointsAttemptedConversions=").append(extraPointsAttemptedConversions).append(", ");
    if (fieldGoalsMade != null)
      builder.append("fieldGoalsMade=").append(fieldGoalsMade).append(", ");
    if (fieldGoalsAttempted != null)
      builder.append("fieldGoalsAttempted=").append(fieldGoalsAttempted).append(", ");
    if (redZoneAttempts != null)
      builder.append("redZoneAttempts=").append(redZoneAttempts).append(", ");
    if (redZoneConversions != null)
      builder.append("redZoneConversions=").append(redZoneConversions).append(", ");
    if (goalToGoAttempts != null)
      builder.append("goalToGoAttempts=").append(goalToGoAttempts).append(", ");
    if (goalToGoConversions != null)
      builder.append("goalToGoConversions=").append(goalToGoConversions).append(", ");
    if (safeties != null)
      builder.append("safeties=").append(safeties).append(", ");
    if (finalScore != null)
      builder.append("finalScore=").append(finalScore).append(", ");
    if (timeOfPossession != null)
      builder.append("timeOfPossession=").append(timeOfPossession);
    builder.append("]");
    return builder.toString();
  }
}
