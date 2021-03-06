SET foreign_key_checks = 0;
DROP TABLE IF EXISTS APP_USER, APP_USER_PROFILE, APP_USER_USER_PROFILE, APP_THEATER, APP_MOVIE, APP_SCREENING,
APP_USER_THEATER, APP_SESSION;
SET foreign_key_checks = 1;
/*All User's gets stored in APP_USER table*/
create table APP_USER (
  id BIGINT NOT NULL AUTO_INCREMENT,
  sso_id VARCHAR(30) NOT NULL,
  password VARCHAR(100) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name  VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  state VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (sso_id)
);

/* USER_PROFILE table contains all possible roles */
create table APP_USER_PROFILE(
  id BIGINT NOT NULL AUTO_INCREMENT,
  type VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (type)
);

/* APP_THEATER table contains all theaters' info */
create table APP_THEATER(
  id BIGINT NOT NULL AUTO_INCREMENT,
  number VARCHAR(30) NOT NULL,
  street VARCHAR(30) NOT NULL,
  city VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);

/* APP_MOVIE table contains all movies' info */
create table APP_MOVIE(
  id INT NOT NULL AUTO_INCREMENT,
  tmdb_id INT NOT NULL,
  original_title VARCHAR(300) NOT NULL,
  backdrop_path VARCHAR(300) NOT NULL,
  poster_path VARCHAR(300) NOT NULL,
  overview VARCHAR(1500) NOT NULL,
  release_date DATE NOT NULL,
  budget INT NOT NULL,
  runtime INT NOT NULL,
  cast VARCHAR(300) NOT NULL,
  director VARCHAR(300) NOT NULL,
  producer VARCHAR(300) NOT NULL,
  vote_average Double NOT NULL,
  vote_count BIGINT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (tmdb_id)
);


/* JOIN TABLE for MANY-TO-MANY relationship*/
CREATE TABLE APP_USER_USER_PROFILE (
  user_id BIGINT NOT NULL,
  user_profile_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, user_profile_id),
  CONSTRAINT FK_APP_USER1 FOREIGN KEY (user_id) REFERENCES APP_USER (id),
  CONSTRAINT FK_USER_PROFILE1 FOREIGN KEY (user_profile_id) REFERENCES APP_USER_PROFILE (id)
);

/* JOIN TABLE for MANY-TO-MANY relationship*/
CREATE TABLE APP_USER_THEATER (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  theater_id BIGINT NOT NULL,
  PRIMARY KEY (id, user_id, theater_id),
  CONSTRAINT FK_APP_USER2 FOREIGN KEY (user_id) REFERENCES APP_USER (id),
  CONSTRAINT FK_APP_THEATER1 FOREIGN KEY (theater_id) REFERENCES APP_THEATER (id)
);

/* APP_SCREENING table contains all possible roles */
CREATE TABLE APP_SCREENING(
  id BIGINT NOT NULL AUTO_INCREMENT,
  movie_id INT NOT NULL,
  theater_id BIGINT NOT NULL,
  start_date VARCHAR(30) NOT NULL,
  end_date VARCHAR(30) NOT NULL,
  age_limit VARCHAR(30) NOT NULL,
  PRIMARY KEY (id, movie_id, theater_id),
  CONSTRAINT FK_APP_MOVIE FOREIGN KEY (movie_id) REFERENCES APP_MOVIE (tmdb_id),
  CONSTRAINT FK_USER_THEATER2 FOREIGN KEY (theater_id) REFERENCES APP_THEATER (id)
);

CREATE TABLE APP_SESSION(
  id BIGINT NOT NULL AUTO_INCREMENT,
  screening_id BIGINT NOT NULL,
  day VARCHAR(30) NOT NULL,
  hour TIME NOT NULL,
  PRIMARY KEY (id, screening_id),
  CONSTRAINT FK_APP_SCREENING FOREIGN KEY (screening_id) REFERENCES APP_SCREENING (id)
);

/* Populate USER_PROFILE Table */
INSERT INTO APP_USER_PROFILE(type)
VALUES ('USER');

INSERT INTO APP_USER_PROFILE(type)
VALUES ('ADMIN');

INSERT INTO APP_USER_PROFILE(type)
VALUES ('DBA');


/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO APP_USER(APP_USER.sso_id, password, first_name, last_name, email, state)
VALUES ('sam','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Sam','Smith','samy@xyz.com', 'Active');


/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM APP_USER user, APP_USER_PROFILE profile
  where user.sso_id='sam' and profile.type='ADMIN';

INSERT INTO APP_THEATER(number, street, city)
VALUES ('12', 'rue des minimes', 'Paris');


/* Populate JOIN Table */
INSERT INTO APP_USER_THEATER (user_id, theater_id)
  SELECT user.id, theater.id FROM APP_USER user, APP_THEATER theater
  where user.sso_id='sam' and theater.number='12';