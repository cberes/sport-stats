package net.seabears.stats.hockey.population;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.seabears.stats.hockey.objects.Game;

public interface IGamePopulator
{
  List<Game> populate(int season, Date minimumDate, Date maximumDateExclusive) throws IOException;
}
