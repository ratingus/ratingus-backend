CREATE TYPE roles AS ENUM ('STUDENT', 'TEACHER', 'LOCAL_ADMIN', 'MANAGER');

CREATE TYPE attendances AS ENUM ('WAS', 'GOOD', 'BAD');

create table users
(
    id            serial  primary key,
    name          varchar NOT null,
    surname       varchar NOT null,
    patronymic    varchar,
    login         varchar NOT null,
    password      varchar NOT null,
    birth_date    TIMESTAMP NOT null
);

CREATE TABLE school
(	
  	id serial PRIMARY KEY,
  	name         varchar NOT null,
    address       varchar NOT null,
    phone    	 varchar NOT null,
    email        varchar NOT null,
    timetable_id int NOT null,

    FOREIGN KEY (timetable_id) REFERENCES timetable (id)
);

CREATE TABLE users_roles
(	
  	user_id int,
  	school_id int,
  	role roles,
    name          varchar NOT null,
    surname       varchar NOT null,
    patronymic    varchar,
    foreign key (user_id) references users (id),
  	foreign key (school_id) references school (id)
);

CREATE TABLE timetable
(
  	id serial PRIMARY KEY,
  	lesson_number int NOT null,
    start_time TIMESTAMP NOT null,
    end_time  TIMESTAMP NOT null,
    school_id int NOT null,
  
  FOREIGN KEY (school_id) REFERENCES school (id)
);

CREATE TABLE classes
(
    id serial PRIMARY KEY,
    name VARCHAR NOT null,
    school_id int NOT null,
    user_code_id int,

    FOREIGN KEY (user_code_id) REFERENCES users_codes (id),
    FOREIGN KEY (school_id) REFERENCES school (id)
);

CREATE TABLE users_codes
(
  	id serial PRIMARY KEY,
  	code VARCHAR NOT null,
    activated BOOLEAN,
    name          varchar NOT null,
    surname       varchar NOT null,
    patronymic    varchar,
    user_id  int NOT null,
    school_id int NOT null,
    creator_id int NOT null,
    class_id int,

    FOREIGN KEY (class_id) REFERENCES classes (id),
    FOREIGN KEY (creator_id) REFERENCES users (id),
  	FOREIGN KEY (user_id) REFERENCES users (id),
  	FOREIGN KEY (school_id) REFERENCES school (id)

);

CREATE TABLE announcement
(
  	id serial PRIMARY KEY,
  	content VARCHAR NOT null,
    name VARCHAR NOT null,
    creator_id  int NOT null,
  
  	FOREIGN KEY (creator_id) REFERENCES users (id)
);


CREATE TABLE classes_announcements
(
    class_id int NOT null,
    announcement_id int NOT null,

    FOREIGN KEY (announcement_id) REFERENCES announcement (id),
    FOREIGN KEY (class_id) REFERENCES classes (id)
);

CREATE TABLE classes_students
(
	class_id int NOT null,
	student_id int NOT null,
	
	FOREIGN KEY (student_id) REFERENCES users (id),
	FOREIGN KEY (class_id) REFERENCES classes (id)
);

CREATE TABLE studies
(
	id serial PRIMARY KEY,
	name VARCHAR NOT null, 
	school_id int NOT null,
	
	FOREIGN KEY (school_id) REFERENCES school (id)
);

CREATE TABLE studies_teachers
(
	study_id int NOT null,
	teacher_id int NOT null,
	
	FOREIGN KEY (teacher_id) REFERENCES users (id),
	FOREIGN KEY (study_id) REFERENCES studies (id)
);

CREATE TABLE lessons
(
  	id serial PRIMARY KEY,
  	homework VARCHAR NOT null,
    theme VARCHAR NOT null,
	date_of_lesson TIMESTAMP NOT null,
	lesson_number int NOT null,
    study_id  int NOT null,
  
  	FOREIGN KEY (study_id) REFERENCES studies (id)
);

CREATE TABLE students_lessons
(
	mark VARCHAR,
	note VARCHAR,
	attendance attendances,
	student_id int NOT null,
	lesson_id int NOT null,
	
	FOREIGN KEY (student_id) REFERENCES users (id),
	FOREIGN KEY (lesson_id) REFERENCES lessons (id)
);









