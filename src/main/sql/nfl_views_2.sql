CREATE VIEW view_advanced_stats AS
select t._id, t.city, t.mascot, g.season,
avg(s.yards_per_pass) as yards_per_pass,
avg(s.yards_per_rush) as yards_per_rush,
avg(s.yards_per_pass_allowed) as yards_per_pass_allowed,
avg(s.yards_per_rush_allowed) as yards_per_rush_allowed
from team t
join team_game tg on tg.team_id = t._id
join game g on g._id = tg.game_id
join view_game_advanced_stats s on s.team_game_id = tg._id
group by t._id, t.city, t.mascot, g.season

CREATE VIEW view_game_advanced_stats AS
select s._id, s.team_game_id,
1.0 * s.net_yards_passing / (s.total_rushing_passing_plays - s.total_rushing_plays) as yards_per_pass,
1.0 * s.net_yards_rushing / s.total_rushing_plays as yards_per_rush,
1.0 * o.net_yards_passing / (o.total_rushing_passing_plays - o.total_rushing_plays) as yards_per_pass_allowed,
1.0 * o.net_yards_rushing / o.total_rushing_plays as yards_per_rush_allowed
from team t
join team_game tg on tg.team_id = t._id
join game g on g._id = tg.game_id
join team_game_stats s on s.team_game_id = tg._id
join team_game otg on otg.game_id = g._id and otg._id <> tg._id
join team_game_stats o on o.team_game_id = otg._id
group by s._id, s.team_game_id

CREATE VIEW view_relative_game AS
select g._id, tg.team_id, g.season, g.week,
s.yards_per_pass / o.yards_per_pass_allowed as yards_per_pass,
s.yards_per_rush / o.yards_per_rush_allowed as yards_per_rush,
s.yards_per_pass_allowed / o.yards_per_pass as yards_per_pass_allowed,
s.yards_per_rush_allowed / o.yards_per_rush as yards_per_rush_allowed
from team t
join team_game tg on tg.team_id = t._id
join game g on g._id = tg.game_id
join view_game_advanced_stats s on s.team_game_id = tg._id
join team_game otg on otg.game_id = g._id and otg._id <> tg._id
join view_advanced_stats o on o._id = otg.team_id and o.season = g.season
group by g._id, tg.team_id, g.season, g.week

select t._id, t.city, t.mascot, g.season,
avg(g.yards_per_pass) as yards_per_pass,
avg(g.yards_per_rush) as yards_per_rush,
avg(g.yards_per_pass_allowed) as yards_per_pass_allowed,
avg(g.yards_per_rush_allowed) as yards_per_rush_allowed
from team t
join team_game tg on tg.team_id = t._id
join view_relative_game g on g._id = tg.game_id and g.team_id = t._id
group by t._id, t.city, t.mascot, g.season

select city || ' ' || mascot as team,
round(yards_per_pass, 2) as yards_per_pass,
round(yards_per_rush, 2) as yards_per_rush,
round(yards_per_pass_allowed, 2) as yards_per_pass_allowed,
round(yards_per_rush_allowed, 2) as yards_per_rush_allowed,
from view_relative_strength
where season = 2013
order by round(yards_per_pass, 2) desc, round(yards_per_pass_allowed, 2)

CREATE VIEW view_relative_strength_old AS
select a._id, a.city, a.mascot, a.season,
a.yards_per_pass / b.yards_per_pass_allowed as passing_offense,
a.yards_per_rush / b.yards_per_rush_allowed as rushing_offense,
a.yards_per_pass_allowed / b.yards_per_pass as passing_defense,
a.yards_per_rush_allowed / b.yards_per_rush as rushing_defense, 
(a.yards_per_pass / b.yards_per_pass_allowed)
+ (a.yards_per_rush / b.yards_per_rush_allowed)
- (a.yards_per_pass_allowed / b.yards_per_pass)
- (a.yards_per_rush_allowed / b.yards_per_rush)
as strength
from view_simple_stats a
join view_opponent_simple_stats b on b._id = a._id and b.season = a.season
