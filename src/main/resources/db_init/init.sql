CREATE TYPE roles AS ENUM ('STUDENT', 'TEACHER', 'LOCAL_ADMIN', 'MANAGER');

CREATE TYPE attendances AS ENUM ('WAS', 'GOOD', 'BAD');

create table users
(
    id serial     PRIMARY KEY,
    name          VARCHAR NOT NULL,
    surname       VARCHAR NOT NULL,
    patronymic    VARCHAR,
    login         VARCHAR NOT NULL,
    password      VARCHAR NOT NULL,
    birth_date    TIMESTAMP NOT NULL
);

CREATE TABLE school
(	
  	id serial PRIMARY KEY,
  	name         VARCHAR NOT NULL,
    address       VARCHAR NOT NULL,
    phone    	 VARCHAR NOT NULL,
    email        VARCHAR NOT NULL,
    timetable_id INT NOT NULL,

    FOREIGN KEY (timetable_id) REFERENCES timetable (id)
);

CREATE TABLE users_roles
(
    id serial PRIMARY KEY,
  	user_id INT,
  	school_id INT,
  	role roles,
    name          VARCHAR NOT NULL,
    surname       VARCHAR NOT NULL,
    patronymic    VARCHAR,

    foreign key (user_id) references users (id),
  	foreign key (school_id) references school (id)
);

CREATE TABLE timetable
(
  	id serial PRIMARY KEY,
  	lesson_number INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time  TIMESTAMP NOT NULL,
    school_id INT NOT NULL,
  
  FOREIGN KEY (school_id) REFERENCES school (id)
);

CREATE TABLE classes
(
    id serial PRIMARY KEY,
    name VARCHAR NOT NULL,
    school_id INT NOT NULL,
    user_code_id INT,

    FOREIGN KEY (user_code_id) REFERENCES users_codes (id),
    FOREIGN KEY (school_id) REFERENCES school (id)
);

CREATE TABLE users_codes
(
  	id serial PRIMARY KEY,
  	code VARCHAR NOT NULL,
    activated BOOLEAN,
    name          VARCHAR NOT NULL,
    surname       VARCHAR NOT NULL,
    patronymic    VARCHAR,
    user_id  INT NOT NULL,
    school_id INT NOT NULL,
    creator_id INT NOT NULL,
    class_id INT,
    user_role_id INT,

    FOREIGN KEY (user_role_id) REFERENCES users_roles (id),
    FOREIGN KEY (class_id) REFERENCES classes (id),
    FOREIGN KEY (creator_id) REFERENCES users (id),
  	FOREIGN KEY (user_id) REFERENCES users (id),
  	FOREIGN KEY (school_id) REFERENCES school (id)

);

CREATE TABLE announcement
(
  	id serial PRIMARY KEY,
  	content VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    creator_id  INT NOT NULL,
  
  	FOREIGN KEY (creator_id) REFERENCES users (id)
);


CREATE TABLE classes_announcements
(
    class_id INT NOT NULL,
    announcement_id INT NOT NULL,

    FOREIGN KEY (announcement_id) REFERENCES announcement (id),
    FOREIGN KEY (class_id) REFERENCES classes (id)
);

CREATE TABLE classes_students
(
	class_id INT NOT NULL,
	student_id INT NOT NULL,
	
	FOREIGN KEY (student_id) REFERENCES users (id),
	FOREIGN KEY (class_id) REFERENCES classes (id)
);

CREATE TABLE subject
(
	id serial PRIMARY KEY,
	name VARCHAR NOT NULL,
	school_id INT NOT NULL,
	
	FOREIGN KEY (school_id) REFERENCES school (id)
);

CREATE TABLE subjects_teachers
(
    subject_id INT NOT NULL,
	teacher_id INT NOT NULL,
	
	FOREIGN KEY (teacher_id) REFERENCES users (id),
	FOREIGN KEY (subject_id) REFERENCES subjects (id)
);

CREATE TABLE lessons
(
  	id serial PRIMARY KEY,
  	homework VARCHAR NOT NULL,
    theme VARCHAR NOT NULL,
	date_of_lesson TIMESTAMP NOT NULL,
	lesson_number INT NOT NULL,
    subject_id  INT NOT NULL,
  
  	FOREIGN KEY (subject_id) REFERENCES subjects (id)
);

CREATE TABLE students_lessons
(
    id serial PRIMARY KEY,
	mark VARCHAR,
	note VARCHAR,
	attendance attendances,
	student_id INT NOT NULL,
	lesson_id INT NOT NULL,
	
	FOREIGN KEY (student_id) REFERENCES users (id),
	FOREIGN KEY (lesson_id) REFERENCES lessons (id)
);









