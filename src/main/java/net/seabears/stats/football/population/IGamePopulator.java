package net.seabears.stats.football.population;

import java.io.IOException;
import java.util.List;

import net.seabears.stats.football.objects.Game;

public interface IGamePopulator
{
  List<Game> populate(int season, int firstWeek, int finalWeek) throws IOException;
}
