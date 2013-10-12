select h.abbrev as home,
v.abbrev as visiting,
l.spread as spread,
25.8109 * (hr.passing_offense - (1.0 / vr.passing_defense))
+ 10.1395 * (hr.rushing_offense - (1.0 / vr.rushing_defense))
- 27.2779 * (vr.passing_offense - (1.0 / hr.passing_defense))
+ 0.3481 * (vr.rushing_offense - (1.0 / hr.rushing_defense))
+ 2.8949 as prediction,
CASE WHEN l.spread + (25.8109 * (hr.passing_offense - (1.0 / vr.passing_defense))
+ 10.1395 * (hr.rushing_offense - (1.0 / vr.rushing_defense))
- 27.2779 * (vr.passing_offense - (1.0 / hr.passing_defense))
+ 0.3481 * (vr.rushing_offense - (1.0 / hr.rushing_defense))
+ 2.8949) >= 0 THEN h.abbrev ELSE v.abbrev END AS bet
from line l
join team h on h._id = l.home_team_id
join team v on v._id = l.visiting_team_id
join view_relative_strength hr on hr._id = l.home_team_id and hr.season = l.season
join view_relative_strength vr on vr._id = l.visiting_team_id and vr.season = l.season
where l.season = 2013 and l.week = 4