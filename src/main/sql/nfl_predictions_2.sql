select h.abbrev as home,
v.abbrev as visiting,
l.spread as spread,
(35.6752 * hr.yards_per_pass)
+ (9.3277 * hr.yards_per_rush)
+ (-27.2490 * hr.yards_per_pass_allowed)
+ (-7.5116 * hr.yards_per_rush_allowed)
+ (-35.4956 * vr.yards_per_pass)
+ (0.3166 * vr.yards_per_rush)
+ (19.9410 * vr.yards_per_pass_allowed)
+ (18.5990 * vr.yards_per_rush_allowed)
- 10.7822 as prediction,
CASE WHEN l.spread + ((35.6752 * hr.yards_per_pass)
+ (9.3277 * hr.yards_per_rush)
+ (-27.2490 * hr.yards_per_pass_allowed)
+ (-7.5116 * hr.yards_per_rush_allowed)
+ (-35.4956 * vr.yards_per_pass)
+ (0.3166 * vr.yards_per_rush)
+ (19.9410 * vr.yards_per_pass_allowed)
+ (18.5990 * vr.yards_per_rush_allowed)
- 10.7822) >= 0 THEN h.abbrev ELSE v.abbrev END AS bet
from line l
join team h on h._id = l.home_team_id
join team v on v._id = l.visiting_team_id
join view_relative_strength hr on hr._id = l.home_team_id and hr.season = l.season
join view_relative_strength vr on vr._id = l.visiting_team_id and vr.season = l.season
where l.season = 2013 and l.week = 6
order by l._id

select h.abbrev as home,
v.abbrev as visiting,
l.spread as spread,
(35.6752 * hr.yards_per_pass)
+ (9.3277 * hr.yards_per_rush)
+ (-27.2490 * hr.yards_per_pass_allowed)
+ (-7.5116 * hr.yards_per_rush_allowed)
+ (-35.4956 * vr.yards_per_pass)
+ (0.3166 * vr.yards_per_rush)
+ (19.9410 * vr.yards_per_pass_allowed)
+ (18.5990 * vr.yards_per_rush_allowed)
- 10.7822 as prediction,
(40 * hr.yards_per_pass)
+ (6 * hr.yards_per_rush)
+ (-21 * hr.yards_per_pass_allowed)
+ (-18 * hr.yards_per_rush_allowed)
+ (-40 * vr.yards_per_pass)
+ (-6 * vr.yards_per_rush)
+ (21 * vr.yards_per_pass_allowed)
+ (18 * vr.yards_per_rush_allowed)
+3 as prediction2,
CASE WHEN l.spread + ((35.6752 * hr.yards_per_pass)
+ (9.3277 * hr.yards_per_rush)
+ (-27.2490 * hr.yards_per_pass_allowed)
+ (-7.5116 * hr.yards_per_rush_allowed)
+ (-35.4956 * vr.yards_per_pass)
+ (0.3166 * vr.yards_per_rush)
+ (19.9410 * vr.yards_per_pass_allowed)
+ (18.5990 * vr.yards_per_rush_allowed)
- 10.7822) >= 0 THEN h.abbrev ELSE v.abbrev END AS bet
from line l
join team h on h._id = l.home_team_id
join team v on v._id = l.visiting_team_id
join view_relative_strength hr on hr._id = l.home_team_id and hr.season = l.season
join view_relative_strength vr on vr._id = l.visiting_team_id and vr.season = l.season
where l.season = 2013 and l.week = 6
order by l._id

select h.abbrev as home,
v.abbrev as visiting,
l.spread as spread,
(40 * hr.yards_per_pass)
+ (6 * hr.yards_per_rush)
+ (-21 * hr.yards_per_pass_allowed)
+ (-18 * hr.yards_per_rush_allowed)
+ (-40 * vr.yards_per_pass)
+ (-6 * vr.yards_per_rush)
+ (21 * vr.yards_per_pass_allowed)
+ (18 * vr.yards_per_rush_allowed)
+3 as prediction,
CASE WHEN l.spread + ((40 * hr.yards_per_pass)
+ (6 * hr.yards_per_rush)
+ (-21 * hr.yards_per_pass_allowed)
+ (-18 * hr.yards_per_rush_allowed)
+ (-40 * vr.yards_per_pass)
+ (-6 * vr.yards_per_rush)
+ (21 * vr.yards_per_pass_allowed)
+ (18 * vr.yards_per_rush_allowed)
+3) >= 0 THEN h.abbrev ELSE v.abbrev END AS bet
from line l
join team h on h._id = l.home_team_id
join team v on v._id = l.visiting_team_id
join view_relative_strength hr on hr._id = l.home_team_id and hr.season = l.season
join view_relative_strength vr on vr._id = l.visiting_team_id and vr.season = l.season
where l.season = 2013 and l.week = 6

select * from view_relative_strength
where season = 2013 order by yards_per_pass desc

insert into line
(visiting_team_id, home_team_id, season, week, spread, ou)
select v._id, h._id, 2013, 6, 1.5, 48.5
from team v, team h
where v.abbrev = 'IND' and h.abbrev = 'SD'
