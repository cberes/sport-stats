package net.seabears.stats.hockey.objects;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import net.seabears.db.DatabaseEntity;

@Entity(name = "team")
public class Team extends DatabaseEntity
{
  @Id
  @Column(name = "_id")
  @GeneratedValue
  private int id;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String mascot;

  @Column(nullable = false)
  private String abbrev;

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

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getMascot()
  {
    return mascot;
  }

  public void setMascot(String mascot)
  {
    this.mascot = mascot;
  }

  public String getAbbrev()
  {
    return abbrev;
  }

  public void setAbbrev(String abbrev)
  {
    this.abbrev = abbrev;
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
    builder.append("Team [id=").append(id).append(", ");
    if (city != null)
      builder.append("city=").append(city).append(", ");
    if (mascot != null)
      builder.append("mascot=").append(mascot).append(", ");
    if (abbrev != null)
      builder.append("abbrev=").append(abbrev).append(", ");
    if (teamGames != null)
      builder.append("teamGames=").append(teamGames);
    builder.append("]");
    return builder.toString();
  }
}
