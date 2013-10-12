CREATE VIEW view_ranked_team_game AS
select tg._id, tg.game_id, tg.team_id, tg.role, count(og._id) as rank
from game g
join team_game tg on tg.game_id = g._id
join game og on og.season = g.season and og.num >= g.num
join team_game otg on otg.game_id = og._id and otg.team_id = tg.team_id
group by tg._id, tg.game_id, tg.team_id, tg.role
union all
select null as _id, null as game_id, t._id as team_id, null as role,  null as rank
from team t

CREATE VIEW view_advanced_stats AS
select t._id, t.city, t.mascot, g.season, rtg.rank,
avg(s.corsi) as corsi,
avg(s.fenwick) as fenwick,
avg(s.pdo) as pdo,
avg(s.corsi_allowed) as corsi_allowed,
avg(s.fenwick_allowed) as fenwick_allowed,
avg(s.pdo_allowed) as pdo_allowed
from team t
join view_ranked_team_game rtg on rtg.team_id = t._id
join view_ranked_team_game tg on tg.team_id = t._id and tg.rank <= ifnull(rtg.rank, 1000)
join game g on g._id = tg.game_id
join view_game_advanced_stats s on s.team_game_id = tg._id
group by t._id, t.city, t.mascot, g.season, rtg.rank

CREATE VIEW view_game_advanced_stats AS
select s._id, s.team_game_id,
s.shots + s.missed_shots + s.blocked_shots as corsi,
s.shots + s.missed_shots as fenwick,
ifnull(1.0 * s.goals / nullif(s.shots, 0), 0)
+ ifnull(1.0 * (o.shots - o.goals) / nullif(o.shots, 0), 0) as pdo,
o.shots + o.missed_shots + o.blocked_shots as corsi_allowed,
o.shots + o.missed_shots as fenwick_allowed,
ifnull(1.0 * (s.shots - s.goals) / nullif(s.shots, 0), 0)
+ ifnull(1.0 * o.goals / nullif(o.shots, 0), 0) as pdo_allowed
from team t
join team_game tg on tg.team_id = t._id
join game g on g._id = tg.game_id
join team_game_stats s on s.team_game_id = tg._id
join team_game otg on otg.game_id = g._id and otg._id <> tg._id
join team_game_stats o on o.team_game_id = otg._id
group by s._id, s.team_game_id

CREATE VIEW view_relative_game AS
select g._id, tg.team_id, g.season, g.num, o.rank,
s.corsi / o.corsi_allowed as corsi,
s.fenwick / o.fenwick_allowed as fenwick,
s.pdo / o.pdo_allowed as pdo,
s.corsi_allowed / o.corsi as corsi_allowed,
s.fenwick_allowed / o.fenwick as fenwick_allowed,
s.pdo_allowed / o.pdo as pdo_allowed
from team t
join team_game tg on tg.team_id = t._id
join game g on g._id = tg.game_id
join view_game_advanced_stats s on s.team_game_id = tg._id
-- get this game's other team
join view_ranked_team_game otg on otg.game_id = g._id and otg._id <> tg._id
-- get the other team's stats for all time and for their game and earlier
join view_advanced_stats o on o._id = otg.team_id and o.season = g.season and (o.rank is null or o.rank >= otg.rank)
group by g._id, tg.team_id, g.season, g.num, o.rank

-- relative strength
select t._id, t.city, t.mascot, g.season, rtg.rank,
avg(g.corsi) as corsi,
avg(g.fenwick) as fenwick,
avg(g.pdo) as pdo,
avg(g.corsi_allowed) as corsi_allowed,
avg(g.fenwick_allowed) as fenwick_allowed,
avg(g.pdo_allowed) as pdo_allowed
from team t
join view_ranked_team_game rtg on rtg.team_id = t._id
join view_ranked_team_game tg on tg.team_id = t._id and tg.rank <= ifnull(rtg.rank, 1000)
join view_relative_game g on g._id = tg.game_id and g.team_id = t._id and ifnull(g.rank, -1) = ifnull(rtg.rank, -1)
group by t._id, t.city, t.mascot, g.season, rtg.rank

select city || ' ' || mascot as team,
round(corsi, 2) as corsi,
round(corsi_allowed, 2) as corsi_allowed,
round(pdo, 2) as pdo,
round(pdo_allowed, 2) as pdo_allowed
from view_relative_strength
where season = 2013
order by round(corsi, 2) desc, round(corsi_allowed, 2)
