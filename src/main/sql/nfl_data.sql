select (hs.final_score - vs.final_score) as 'y',
(hs.final_score + vs.final_score) as 'z',
(hr.passing_offense - vr.passing_offense) as 'a',
(hr.rushing_offense - vr.rushing_offense) as 'b',
(hr.passing_defense - vr.passing_defense) as 'c',
(hr.rushing_defense - vr.rushing_defense) as 'd'
from game g
join team_game h on h.game_id = g._id and h.role = 'H'
join team_game v on v.game_id = g._id and v.role = 'V'
join team_game_stats hs on hs.team_game_id = h._id
join team_game_stats vs on vs.team_game_id = v._id
join view_relative_strength hr on hr._id = h.team_id and hr.season = g.season
join view_relative_strength vr on vr._id = v.team_id and vr.season = g.season
where g.season < 2013
