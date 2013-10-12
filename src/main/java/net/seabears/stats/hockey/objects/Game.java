package net.seabears.stats.hockey.objects;

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
  private int num;

  @Column(nullable = false)
  private Date played;

  @Column(nullable = false)
  private boolean playoffs;

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

  public void setNum(int num)
  {
    this.num = num;
  }

  public boolean isPlayoffs()
  {
    return playoffs;
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

  public int getNum()
  {
    return num;
  }

  public void setPlayoffs(boolean playoffs)
  {
    this.playoffs = playoffs;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Game [id=").append(id).append(", season=").append(season).append(", week=").append(num).append(", ");
    if (played != null)
      builder.append("played=").append(played).append(", ");
    if (teamGames != null)
      builder.append("teamGames=").append(teamGames);
    builder.append("]");
    return builder.toString();
  }
}
