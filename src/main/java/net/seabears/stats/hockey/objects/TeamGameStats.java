package net.seabears.stats.hockey.objects;

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

  @Column
  private Integer goals;

  @Column
  private Integer shots;

  @Column(name = "missed_shots")
  private Integer missedShots;

  @Column(name = "blocked_shots")
  private Integer blockedShots;

  @Column(name = "shots_blocked")
  private Integer shotsBlocked;

  @Column
  private Integer penalties;

  @Column(name = "penalties_in_minutes")
  private Integer penaltiesInMinutes;

  @Column
  private Integer hits;

  @Column(name = "take_aways")
  private Integer takeAways;

  @Column(name = "give_aways")
  private Integer giveAways;

  @Column(name = "faceoffs_won")
  private Integer faceoffsWon;

  @Column(name = "faceoffs_lost")
  private Integer faceoffsLost;

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

  public TeamGame getTeamGame()
  {
    return teamGame;
  }

  public void setTeamGame(TeamGame teamGame)
  {
    this.teamGame = teamGame;
  }

  public Integer getGoals()
  {
    return goals;
  }

  public void setGoals(Integer goals)
  {
    this.goals = goals;
  }

  public Integer getShots()
  {
    return shots;
  }

  public void setShots(Integer shots)
  {
    this.shots = shots;
  }

  public Integer getMissedShots()
  {
    return missedShots;
  }

  public void setMissedShots(Integer missedShots)
  {
    this.missedShots = missedShots;
  }

  public Integer getBlockedShots()
  {
    return blockedShots;
  }

  public void setBlockedShots(Integer blockedShots)
  {
    this.blockedShots = blockedShots;
  }

  public Integer getShotsBlocked()
  {
    return shotsBlocked;
  }

  public void setShotsBlocked(Integer shotsBlocked)
  {
    this.shotsBlocked = shotsBlocked;
  }

  public Integer getPenalties()
  {
    return penalties;
  }

  public void setPenalties(Integer penalties)
  {
    this.penalties = penalties;
  }

  public Integer getPenaltiesInMinutes()
  {
    return penaltiesInMinutes;
  }

  public void setPenaltiesInMinutes(Integer penaltiesInMinutes)
  {
    this.penaltiesInMinutes = penaltiesInMinutes;
  }

  public Integer getHits()
  {
    return hits;
  }

  public void setHits(Integer hits)
  {
    this.hits = hits;
  }

  public Integer getTakeAways()
  {
    return takeAways;
  }

  public void setTakeAways(Integer takeAways)
  {
    this.takeAways = takeAways;
  }

  public Integer getGiveAways()
  {
    return giveAways;
  }

  public void setGiveAways(Integer giveAways)
  {
    this.giveAways = giveAways;
  }

  public Integer getFaceoffsWon()
  {
    return faceoffsWon;
  }

  public void setFaceoffsWon(Integer faceoffsWon)
  {
    this.faceoffsWon = faceoffsWon;
  }

  public Integer getFaceoffsLost()
  {
    return faceoffsLost;
  }

  public void setFaceoffsLost(Integer faceoffsLost)
  {
    this.faceoffsLost = faceoffsLost;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("TeamGameStats [id=").append(id).append(", teamGameId=").append(teamGameId).append(", ");
    if (goals != null)
      builder.append("goals=").append(goals).append(", ");
    if (shots != null)
      builder.append("shots=").append(shots).append(", ");
    if (missedShots != null)
      builder.append("missedShots=").append(missedShots).append(", ");
    if (blockedShots != null)
      builder.append("blockedShots=").append(blockedShots).append(", ");
    if (shotsBlocked != null)
      builder.append("shotsBlocked=").append(shotsBlocked).append(", ");
    if (penalties != null)
      builder.append("penalties=").append(penalties).append(", ");
    if (penaltiesInMinutes != null)
      builder.append("penaltiesInMinutes=").append(penaltiesInMinutes).append(", ");
    if (hits != null)
      builder.append("hits=").append(hits).append(", ");
    if (takeAways != null)
      builder.append("takeAways=").append(takeAways).append(", ");
    if (giveAways != null)
      builder.append("giveAways=").append(giveAways).append(", ");
    if (faceoffsWon != null)
      builder.append("faceoffsWon=").append(faceoffsWon).append(", ");
    if (faceoffsLost != null)
      builder.append("faceoffsLost=").append(faceoffsLost);
    builder.append("]");
    return builder.toString();
  }
}
