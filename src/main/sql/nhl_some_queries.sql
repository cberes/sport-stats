select r.city, r.corsi, r.corsi_allowed --,r.fenwick,  r.fenwick_allowed
from view_relative_strength r
join team t on t._id = r._id
where r.season = 2013
order by CASE t.abbrev
WHEN 'COL' then 0 WHEN 'TOR' then 0
WHEN 'PHX' then 1 WHEN 'NYI' then 1
WHEN 'FLA' then 2 WHEN 'PHI' then 2
WHEN 'CAR' then 3 WHEN 'PIT' then 3
WHEN 'TBL' then 4 WHEN 'BUF' then 4
WHEN 'MIN' then 5 WHEN 'NSH' then 5
WHEN 'NJD' then 6 WHEN 'VAN' then 6
WHEN 'NYR' then 7 WHEN 'SJS' then 7
ELSE 100 END

select
r.*
from view_relative_strength r
join view_simple_stats s on s._id = r._id
join team t on t._id = r._id
where t.abbrev in ('EDM', 'NYR', 'LAK', 'NJD')

2.79411764705882 * s.corsi * o.corsi

njd, edm
3.27964704
1.06542056074766 * 2.79411764705882 * 1.10169491525424
0.908616187989556 * 2.79411764705882 * 0.77720207253886
1.973145486

lak. nyr
3.162412802
0.980902777777778 * 2.79411764705882 * 1.15384615384615
0.784615384615385 * 2.79411764705882 * 0.97985347985348
2.148140321


select
t.city,
corsi * 0.0492483151892172
from view_simple_stats s
join team t on t._id = s._id
where t.abbrev in ('EDM', 'NYR', 'LAK', 'NJD')
group by t.city

select 1.0 * sum(goals) / sum(shots + missed_shots + blocked_shots),
avg(shots + missed_shots + blocked_shots),
1.0 * sum(goals) / sum(shots + missed_shots + blocked_shots) * 
avg(shots + missed_shots + blocked_shots),
avg(goals)
from team_game_stats