select *
from game g
join team_game htg on htg.game_id = g._id and htg.role = 'H'
join team_game vtg on vtg.game_id = g._id and vtg.role = 'V'
join line l on l.home_team_id = htg.team_id and l.visiting_team_id = vtg.team_id
and l.season = g.season and l.week = g.week

insert into line
(home_team_id, visiting_team_id, season, week, spread, ou)
select h._id, v._id, 2013, 5, -9.5, 44
from team h, team v
where v.abbrev = 'NYJ' and h.abbrev = 'ATL';
select * from line where season = 2013 and week = 5;