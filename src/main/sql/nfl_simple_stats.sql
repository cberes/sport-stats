CREATE VIEW IF NOT EXISTS view_simple_stats AS
select t._id, t.city, t.mascot,
sum(s.net_yards_passing) * 1.0 / (sum(s.total_rushing_passing_plays) - sum(s.total_rushing_plays)) as yards_per_pass,
sum(s.net_yards_rushing) * 1.0 / sum(s.total_rushing_plays) as yards_per_rush,
sum(o.net_yards_passing) * 1.0 / (sum(o.total_rushing_passing_plays) - sum(o.total_rushing_plays)) as yards_per_pass_allowed,
sum(o.net_yards_rushing) * 1.0 / sum(o.total_rushing_plays) as yards_per_rush_allowed
from team t
join team_game tg on tg.team_id = t._id
join game g on g._id = tg.game_id
join team_game_stats s on s.team_game_id = tg._id
join team_game otg on otg.game_id = g._id and otg._id <> tg._id
join team_game_stats o on o.team_game_id = otg._id
group by t._id, t.city, t.mascot
