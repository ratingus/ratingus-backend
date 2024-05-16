CREATE TYPE roles AS ENUM ('STUDENT', 'TEACHER', 'LOCAL_ADMIN', 'MANAGER');

CREATE TYPE attendances AS ENUM ('WAS', 'GOOD', 'BAD');


CREATE TABLE school
(
    id serial PRIMARY KEY,
    name         VARCHAR NOT NULL,
    address       VARCHAR NOT NULL,
    phone    	 VARCHAR NOT NULL,
    email        VARCHAR NOT NULL

);

CREATE TABLE class
(
    id serial PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE ,
    school_id INT NOT NULL,

    FOREIGN KEY (school_id) REFERENCES school (id)
);

CREATE TABLE user_role
(
    id serial PRIMARY KEY,
    school_id INT NOT NULL,
    role roles NOT NULL,
    name          VARCHAR NOT NULL,
    surname       VARCHAR NOT NULL,
    patronymic    VARCHAR,
    class_id INT NOT NULL,

    foreign key (class_id) references class (id),
    foreign key (school_id) references school (id)
);

create table users
(
    id serial     PRIMARY KEY,
    name          VARCHAR NOT NULL,
    surname       VARCHAR NOT NULL,
    patronymic    VARCHAR,
    login         VARCHAR NOT NULL UNIQUE ,
    password      VARCHAR NOT NULL,
    birth_date    TIMESTAMP NOT NULL,
    user_role_id INT,

    foreign key (user_role_id) references user_role (id)
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


CREATE TABLE user_code
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
    role roles NOT NULL,


    FOREIGN KEY (class_id) REFERENCES class (id),
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


CREATE TABLE class_announcement
(
    class_id INT NOT NULL,
    announcement_id INT NOT NULL,

    FOREIGN KEY (announcement_id) REFERENCES announcement (id),
    FOREIGN KEY (class_id) REFERENCES class (id)
);

CREATE TABLE class_student
(
    class_id INT NOT NULL,
    student_id INT NOT NULL,

    FOREIGN KEY (student_id) REFERENCES users (id),
    FOREIGN KEY (class_id) REFERENCES class (id)
);

CREATE TABLE subject
(
    id serial PRIMARY KEY,
    name VARCHAR NOT NULL,
    school_id INT NOT NULL,

    FOREIGN KEY (school_id) REFERENCES school (id)
);

CREATE TABLE subject_teacher
(
    subject_id INT NOT NULL,
    teacher_id INT NOT NULL,

    FOREIGN KEY (teacher_id) REFERENCES users (id),
    FOREIGN KEY (subject_id) REFERENCES subject (id)
);


CREATE TABLE lesson
(
    id serial PRIMARY KEY,
    homework VARCHAR NOT NULL,
    theme VARCHAR NOT NULL,
    date_of_lesson TIMESTAMP NOT NULL,
    lesson_number INT NOT NULL,
    subject_id  INT NOT NULL,

    FOREIGN KEY (subject_id) REFERENCES subject (id)
);

CREATE TABLE schedule
(
    id serial PRIMARY KEY,
    lesson_number INT,
    day_of_week INT,
    lesson_id INT NOT NULL,
    timetable_id INT,
    subject_id INT,

    FOREIGN KEY (lesson_id) REFERENCES lesson (id),
    FOREIGN KEY (subject_id) REFERENCES subject (id),
    FOREIGN KEY (timetable_id) REFERENCES timetable (id)

);

CREATE TABLE student_lesson
(
    id serial PRIMARY KEY,
    mark VARCHAR,
    note VARCHAR,
    attendance attendances,
    student_id INT NOT NULL,
    lesson_id INT NOT NULL,

    FOREIGN KEY (student_id) REFERENCES users (id),
    FOREIGN KEY (lesson_id) REFERENCES lesson (id)
);

CREATE TABLE application
(
    id serial PRIMARY KEY,
    organisation_mail VARCHAR NOT NULL,
    organisation_name VARCHAR NOT NULL,
    organisation_address VARCHAR NOT NULL,
    organisation_phone VARCHAR NOT NULL,
    creator_id INT NOT NULL,

    FOREIGN KEY (creator_id) REFERENCES users (id)

);



