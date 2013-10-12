CREATE VIEW IF NOT EXISTS view_opponent_simple_stats AS
select t._id, t.city, t.mascot,
avg(s.yards_per_pass) as yards_per_pass,
avg(s.yards_per_rush) as yards_per_rush,
avg(s.yards_per_pass_allowed) as yards_per_pass_allowed,
avg(s.yards_per_rush_allowed) as yards_per_rush_allowed
from game g
join team_game tg on tg.game_id = g._id
join team_game otg on otg.game_id = g._id and otg._id <> tg._id
join view_simple_stats s on s._id = otg.team_id
join team t on tg.team_id = t._id
group by t._id, t.city, t.mascot
