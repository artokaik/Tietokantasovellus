-- select works.description, project_name, username, sum (hours) as tunnit from users, timeentry, works, projects, participation 
-- where timeentry.work_id = works.id 
--   and participation_id = participation.id
--   and participation.project_id = projects.id
--   and participation.user_id = users.id
--   and projects.id = 1
--   
-- 
--  group by participation_id, work_id, works.description, project_name, username
select * from projects

