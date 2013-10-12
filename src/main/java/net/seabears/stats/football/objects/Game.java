package net.seabears.stats.football.objects;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import net.seabears.db.DatabaseEntity;

@Entity(name = "game")
public class Game extends DatabaseEntity
{
  @Id
  @Column(name = "_id")
  @GeneratedValue
  private int id;

  @Column(nullable = false)
  private int season;

  @Column(nullable = false)
  private int week;

  @Column(nullable = false)
  private Date played;

  @OneToMany
  private List<TeamGame> teamGames;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getSeason()
  {
    return season;
  }

  public void setSeason(int season)
  {
    this.season = season;
  }

  public int getWeek()
  {
    return week;
  }

  public void setWeek(int week)
  {
    this.week = week;
  }

  public Date getPlayed()
  {
    return played;
  }

  public void setPlayed(Date played)
  {
    this.played = played;
  }

  public List<TeamGame> getTeamGames()
  {
    return teamGames;
  }

  public void setTeamGames(List<TeamGame> teamGames)
  {
    this.teamGames = teamGames;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Game [id=").append(id).append(", season=").append(season).append(", week=").append(week).append(", ");
    if (played != null)
      builder.append("played=").append(played).append(", ");
    if (teamGames != null)
      builder.append("teamGames=").append(teamGames);
    builder.append("]");
    return builder.toString();
  }
}
