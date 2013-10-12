CREATE TABLE IF NOT EXISTS team (
_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
city TEXT NOT NULL,
mascot TEXT NOT NULL,
abbrev TEXT NOT NULL
);

INSERT INTO team
(city, mascot, abbrev) VALUES
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', ''),
('', '', '');

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
INTEGER,
INTEGER,
INTEGER,
INTEGER,
INTEGER,
INTEGER,
INTEGER,
INTEGER,
INTEGER,
INTEGER,
INTEGER,
INTEGER,
INTEGER
);
