select t._id, t.city, t.mascot, g.season,
avg(s.shots + s.missed_shots + s.blocked_shots) as corsi,
avg(s.shots + s.missed_shots) as fenwick,
ifnull(1.0 * sum(s.goals) / nullif(sum(s.shots), 0), 0)
+ ifnull(1.0 * (sum(o.shots) - sum(o.goals)) / nullif(sum(o.shots), 0), 0) as pdo,
avg(o.shots + o.missed_shots + o.blocked_shots) as corsi_allowed,
avg(o.shots + o.missed_shots) as fenwick_allowed,
ifnull(1.0 * (sum(s.shots) - sum(s.goals)) / nullif(sum(s.shots), 0), 0)
+ ifnull(1.0 * sum(o.goals) / nullif(sum(o.shots), 0), 0) as pdo_allowed
from team t
join team_game tg on tg.team_id = t._id
join game g on g._id = tg.game_id
join team_game_stats s on s.team_game_id = tg._id
join team_game otg on otg.game_id = g._id and otg._id <> tg._id
join team_game_stats o on o.team_game_id = otg._id
group by t._id, t.city, t.mascot, g.season


select t._id, t.city, t.mascot, g.season,
avg(s.shots + s.missed_shots + s.blocked_shots) as corsi,
avg(s.shots + s.missed_shots) as fenwick,
ifnull(1.0 * sum(s.goals) / nullif(sum(s.shots), 0), 0)
+ ifnull(1.0 * (sum(o.shots) - sum(o.goals)) / nullif(sum(o.shots), 0), 0) as pdo,
avg(o.shots + o.missed_shots + o.blocked_shots) as corsi_allowed,
avg(o.shots + o.missed_shots) as fenwick_allowed,
ifnull(1.0 * (sum(s.shots) - sum(s.goals)) / nullif(sum(s.shots), 0), 0)
+ ifnull(1.0 * sum(o.goals) / nullif(sum(o.shots), 0), 0) as pdo_allowed
from team t
join team_game tg on tg.team_id = t._id
join game g on g._id = tg.game_id
-- other team's games versus this opponent
join team_game otg on otg.game_id = g._id and otg._id <> tg._id
-- all of other team's games
join team_game ootg on ootg.team_id = otg.team_id
join game og on og._id = ootg.game_id and og.season = g.season
-- other team's stats in those games
join team_game_stats s on s.team_game_id = ootg._id
-- other team's opponent's stats
join team_game oootg on oootg.game_id = og._id and oootg._id <> ootg._id
join team_game_stats o on o.team_game_id = oootg._id
group by t._id, t.city, t.mascot, g.season

select a._id, a.city, a.mascot, a.season,
a.corsi / b.corsi_allowed as corsi,
a.fenwick / b.fenwick_allowed as fenwick,
a.pdo / b.pdo_allowed as pdo,
a.corsi_allowed / b.corsi as corsi_allowed,
a.fenwick_allowed / b.fenwick as fenwick_allowed,
a.pdo_allowed / b.pdo as pdo_allowed, 
(a.corsi / b.corsi_allowed)
+ (a.fenwick / b.fenwick_allowed)
- (a.corsi_allowed / b.corsi)
- (a.fenwick_allowed / b.fenwick)
as strength
from view_simple_stats a
join view_opponent_simple_stats b on b._id = a._id and b.season = a.season
