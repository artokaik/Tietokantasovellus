INSERT INTO users (username, password, administrator) VALUES
  ('tester', 'password123', false),
  ('admin', 'admin', true),
  ('dexter', 'password123', false);

INSERT INTO projects (project_name) VALUES
('projekti numero yksi'),
('projekti numero kaksi'),
('projekti numero kolme');

INSERT INTO works (description) VALUES
  ('suunnittelu'),
  ('koodaus'),
  ('testaus');

INSERT INTO participation (user_id, project_id, manager) VALUES
    ('1', '1', true),
    ('1', '2', false),
    ('2', '1', false),
    ('2', '3', true);

INSERT INTO timeentry (participation_id,work_id,hours,description, workday) VALUES
    ('1','3','2.1','tuntikirjaus1', '2013-01-01'),
    ('2','1','4','tuntikirjaus2', '2013-01-01'),
    ('2','2','3','tuntikirjaus4', '2013-01-01'),
    ('2','1','1','tuntikirjaus4', '2013-01-01'),
    ('3','2','5','tuntikirjaus3', '2013-01-01');


