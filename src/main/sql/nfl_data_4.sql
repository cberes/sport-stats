select (hs.final_score - vs.final_score) as 'y',
(hs.final_score + vs.final_score) as 'z',
hr.yards_per_pass as 'a',
hr.yards_per_rush as 'b',
hr.yards_per_pass_allowed as 'c',
hr.yards_per_rush_allowed as 'd',
vr.yards_per_pass as 'e',
vr.yards_per_rush as 'f',
vr.yards_per_pass_allowed as 'g',
vr.yards_per_rush_allowed as 'h'
from game g
join team_game h on h.game_id = g._id and h.role = 'H'
join team_game v on v.game_id = g._id and v.role = 'V'
join team_game_stats hs on hs.team_game_id = h._id
join team_game_stats vs on vs.team_game_id = v._id
join view_relative_strength hr on hr._id = h.team_id and hr.season = g.season
join view_relative_strength vr on vr._id = v.team_id and vr.season = g.season
where g.season < 2013
