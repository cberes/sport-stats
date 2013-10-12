CREATE TABLE IF NOT EXISTS team (
_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
city TEXT NOT NULL,
mascot TEXT NOT NULL,
abbrev TEXT NOT NULL
);

INSERT INTO team
(city, mascot, abbrev) VALUES
('Anaheim', 'Ducks', 'ANA'),
('Boston', 'Bruins', 'BOS'),
('Buffalo', 'Sabres', 'BUF'),
('Calgary', 'Flames', 'CGY'),
('Carolina', 'Hurricanes', 'CAR'),
('Chicago', 'Blackhawks', 'CHI'),
('Colorado', 'Avalanche', 'COL'),
('Columbus', 'Blue Jackets', 'CBJ'),
('Dallas', 'Stars', 'DAL'),
('Detroit', 'Red Wings', 'DET'),
('Edmonton', 'Oilers', 'EDM'),
('Florida', 'Panthers', 'FLA'),
('Los Angeles', 'Kings', 'LAK'),
('Minnesota', 'Wild', 'MIN'),
('Montreal', 'Canadiens', 'MTL'),
('Nashville', 'Predators', 'NSH'),
('New Jersey', 'Devils', 'NJD'),
('New York', 'Islanders', 'NYI'),
('New York', 'Rangers', 'NYR'),
('Ottawa', 'Senators', 'OTT'),
('Philadelphia', 'Flyers', 'PHI'),
('Phoenix', 'Coyotes', 'PHX'),
('Pittsburgh', 'Penguins', 'PIT'),
('San Jose', 'Sharks', 'SJS'),
('St. Louis', 'Blues', 'STL'),
('Tampa Bay', 'Lightning', 'TBL'),
('Toronto', 'Maple Leafs', 'TOR'),
('Vancouver', 'Canucks', 'VAN'),
('Washington', 'Capitals', 'WSH'),
('Winnipeg', 'Jets', 'WPG');

CREATE TABLE IF NOT EXISTS game (
_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
season INTEGER NOT NULL,
num INTEGER NOT NULL,
played TEXT NOT NULL,
playoffs INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS team_game (
_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
game_id INTEGER NOT NULL REFERENCES game(_id),
team_id INTEGER NOT NULL REFERENCES team(_id),
role TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS team_game_stats (
_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
team_game_id INTEGER NOT NULL REFERENCES team_game(_id),
goals INTEGER,
shots INTEGER,
missed_shots INTEGER,
blocked_shots INTEGER,
shots_blocked INTEGER,
penalties INTEGER,
penalties_in_minutes INTEGER,
hits INTEGER,
take_aways INTEGER,
give_aways INTEGER,
faceoffs_won INTEGER,
faceoffs_lost INTEGER
);


sum(s.shots) + sum(s.missed_shots) + sum(s.blocked_shots) as corsi,
sum(s.shots) + sum(s.missed_shots) as fenwick,
sum(o.shots) + sum(o.missed_shots) + sum(o.blocked_shots) as corsi_allowed,
sum(o.shots) + sum(o.missed_shots) as fenwick_allowed

select t._id, t.city, t.mascot, g.season,
avg(s.shots + s.missed_shots + s.blocked_shots) as corsi,
avg(s.shots + s.missed_shots) as fenwick,
avg(o.shots + o.missed_shots + o.blocked_shots) as corsi_allowed,
avg(o.shots + o.missed_shots) as fenwick_allowed
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
avg(o.shots + o.missed_shots + o.blocked_shots) as corsi_allowed,
avg(o.shots + o.missed_shots) as fenwick_allowed
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
a.corsi_allowed / b.corsi as corsi_allowed,
a.fenwick_allowed / b.fenwick as fenwick_allowed, 
(a.yards_per_pass / b.yards_per_pass_allowed)
+ (a.yards_per_rush / b.yards_per_rush_allowed)
- (a.yards_per_pass_allowed / b.yards_per_pass)
- (a.yards_per_rush_allowed / b.yards_per_rush)
as strength
from view_simple_stats a
join view_opponent_simple_stats b on b._id = a._id and b.season = a.season
