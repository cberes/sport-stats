CREATE VIEW IF NOT EMPTY view_relative_strength AS
select a._id, a.city, a.mascot, a.season,
a.yards_per_pass / b.yards_per_pass as passing_offense,
a.yards_per_rush / b.yards_per_rush as rushing_offense,
a.yards_per_pass_allowed / b.yards_per_pass_allowed as passing_defense,
a.yards_per_rush_allowed / b.yards_per_rush_allowed as rushing_defense, 
(a.yards_per_pass / b.yards_per_pass)
+ (a.yards_per_rush / b.yards_per_rush)
- (a.yards_per_pass_allowed / b.yards_per_pass_allowed)
- (a.yards_per_rush_allowed / b.yards_per_rush_allowed)
as strength
from view_simple_stats a
join view_opponent_simple_stats b on b._id = a._id and b.season = a.season
