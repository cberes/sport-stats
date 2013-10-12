Sports stats
============

This project scrapes sports stats websites (currently ice hockey and American football). It imports the stats into a SQLite database. I've removed the references to those websites to prevent abuse.

Because I was really lazy, the two main entry points are in the tests:

0. FootballTest.test
0. HockeyTest.test

Properties
----------

There are a few system properties you will need to set:

### Football

| Property | Description
| -------- | -----------
| net.seabears.stats.url | Main stats site base URL
| net.seabears.stats.football.db | Path to football database
| net.seabears.stats.football.league | Name of football league
| net.seabears.stats.football.url | Football-specific base URL

### Hockey

| Property | Description
| -------- | -----------
| net.seabears.stats.url | Main stats site base URL
| net.seabears.stats.football.db | Path to football database
| net.seabears.stats.football.league | Name of football league
| net.seabears.stats.football.url | Football-specific base URL

