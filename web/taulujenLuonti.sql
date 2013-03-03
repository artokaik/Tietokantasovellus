-- Käyttäjä-taulun luonti
CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar UNIQUE,
  password varchar,
  administrator boolean
);

-- Projekti-taulun luonti
CREATE TABLE projects (
  id serial PRIMARY KEY,
  project_name varchar NOT NULL UNIQUE
);

-- Työlaji
CREATE TABLE works (
  id serial PRIMARY KEY,
  description varchar NOT NULL UNIQUE
);

-- Osallistuminen
CREATE TABLE participation (
  id serial PRIMARY KEY,
  user_id integer REFERENCES users(id) ON DELETE CASCADE,
  project_id integer REFERENCES projects(id) ON DELETE CASCADE,
  manager boolean
);

-- Tuntikirjaustaulu
CREATE TABLE timeEntry (
  id serial PRIMARY KEY,
  participation_id integer REFERENCES participation(id) ON DELETE CASCADE,
  work_id integer REFERENCES works(id) ON DELETE CASCADE, 
  hours decimal,
  description varchar,
  workday date
);

