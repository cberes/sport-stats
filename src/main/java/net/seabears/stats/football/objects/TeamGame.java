package net.seabears.stats.football.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import net.seabears.db.DatabaseEntity;
import net.seabears.stats.TeamRole;

@Entity(name = "team_game")
public class TeamGame extends DatabaseEntity
{
  @Id
  @Column(name = "_id")
  @GeneratedValue
  private int id;

  @Column(name = "game_id", nullable = false)
  private int gameId;

  @Column(name = "team_id", nullable = false)
  private int teamId;

  @Column(nullable = false)
  private TeamRole role;

  @ManyToOne
  @JoinColumn(name = "game_id", nullable = false)
  private Game game;

  @ManyToOne
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;

  @OneToOne
  private TeamGameStats stats;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getGameId()
  {
    return gameId;
  }

  public void setGameId(int gameId)
  {
    this.gameId = gameId;
  }

  public int getTeamId()
  {
    return teamId;
  }

  public void setTeamId(int teamId)
  {
    this.teamId = teamId;
  }

  public TeamRole getRole()
  {
    return role;
  }

  public void setRole(TeamRole role)
  {
    this.role = role;
  }

  public Game getGame()
  {
    return game;
  }

  public void setGame(Game game)
  {
    this.game = game;
    this.setGameId(this.game != null ? this.game.getId() : 0);
  }

  public Team getTeam()
  {
    return team;
  }

  public void setTeam(Team team)
  {
    this.team = team;
    this.setTeamId(this.team != null ? this.team.getId() : 0);
  }

  public TeamGameStats getStats()
  {
    return stats;
  }

  public void setStats(TeamGameStats stats)
  {
    this.stats = stats;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("TeamGame [id=").append(id).append(", gameId=").append(gameId).append(", teamId=").append(teamId).append(", ");
    if (role != null)
      builder.append("role=").append(role).append(", ");
    if (stats != null)
      builder.append("stats=").append(stats);
    builder.append("]");
    return builder.toString();
  }
}
