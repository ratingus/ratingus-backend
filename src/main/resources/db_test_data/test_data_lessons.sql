-- 1 - 5
insert into subject (name, school_id) values ('Математика', 1);
insert into subject (name, school_id) values ('Химия', 1);
insert into subject (name, school_id) values ('Литература', 1);
insert into subject (name, school_id) values ('История', 1);
insert into subject (name, school_id) values ('Биология', 1);

-- 1 - 6
insert into user_role (name, surname, patronymic, user_id, school_id, role)
values ('Александр', 'Александров', 'Александрович', 3, 1, 'TEACHER');
insert into user_role (name, surname, patronymic, user_id, school_id, role)
values ('Елена', 'Сидорова', 'Петровна', 4, 1, 'TEACHER');
insert into user_role (name, surname, patronymic, user_id, school_id, role)
values ('Мария', 'Петрова', 'Ивановна', 5, 1, 'TEACHER');
insert into user_role (name, surname, patronymic, user_id, school_id, role)
values ('Павел', 'Козлов', 'Сергеевич', 6, 1, 'TEACHER');
insert into user_role (name, surname, patronymic, user_id, school_id, role)
values ('Анна', 'Смирнова', 'Андреевна', 7, 1, 'TEACHER');

-- 1-5 - 1-6 = 1-9
insert into teacher_subject (subject_id, teacher_id) values (1, 5);
insert into teacher_subject (subject_id, teacher_id) values (2, 5);
insert into teacher_subject (subject_id, teacher_id) values (2, 6);
insert into teacher_subject (subject_id, teacher_id) values (3, 6);
insert into teacher_subject (subject_id, teacher_id) values (3, 7);
insert into teacher_subject (subject_id, teacher_id) values (3, 8);
insert into teacher_subject (subject_id, teacher_id) values (4, 6);
insert into teacher_subject (subject_id, teacher_id) values (4, 9);
insert into teacher_subject (subject_id, teacher_id) values (5, 8);

-- 1-16
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  1,  1, 1);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  1,  2, 2);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  1,  2, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  1,  1, 6);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (2,  1,  1, 3);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (2,  1,  2, 4);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (3,  1,  2, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (3,  1,  1, 6);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  1,  1, 1);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  1,  2, 2);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  1,  2, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  1,  1, 6);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (5,  1,  1, 3);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (5,  1,  2, 4);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (6,  1,  2, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (6,  1,  1, 6);

-- 17-32
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  2,  3, 1);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  2,  4, 2);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  2,  4, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  2,  3, 6);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (2,  2,  3, 3);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (2,  2,  4, 4);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (3,  2,  4, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (3,  2,  3, 6);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  2,  3, 1);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  2,  4, 2);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  2,  4, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  2,  3, 6);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (5,  2,  3, 3);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (5,  2,  4, 4);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (6,  2,  4, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (6,  2,  3, 6);


-- 33-48
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  3,  5, 1);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  3,  6, 2);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  3,  6, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (1,  3,  5, 6);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (2,  3,  5, 3);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (2,  3,  6, 4);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (3,  3,  6, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (3,  3,  5, 6);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  3,  5, 1);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  3,  6, 2);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  3,  5, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (4,  3,  6, 6);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (5,  3,  6, 3);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (5,  3,  5, 4);

insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (6,  3,  5, 4);
insert into schedule  (day_of_week, class_id, subject_id, timetable_id) values  (6,  3,  6, 6);


insert into lesson (homework, theme, date_of_lesson, finished, schedule_id)
values ('Написать эссе о теме "Эволюция в культуре"', 'Эволюция культуры', '2024-05-27T08:00:00.000+00:00', true, 1);
insert into lesson (homework, theme, date_of_lesson, finished, schedule_id)
values ('Выучить стихотворение А.С.Пушкина', 'Русские классики', '2024-05-28T00:00:00.000+00:00', true, 6);
insert into lesson (homework, theme, date_of_lesson, finished, schedule_id)
values ('Подготовить презентацию на тему "Роль микроорганизмов в экосистемах"', 'Микроорганизмы', '2024-05-29T00:00:00.000+00:00', true, 8);
insert into lesson (homework, theme, date_of_lesson, finished, schedule_id)
values ('Подготовиться к тесту', 'Алканы', '2024-05-30T00:00:00.000+00:00', false, 11);
insert into lesson (homework, theme, date_of_lesson, finished, schedule_id)
values ('Решить задачи на тему "Применение производной"', 'Производные', '2024-05-31T00:00:00.000+00:00', false, 14);


insert into student_lesson (student_id, lesson_id) values (16, 1);
insert into student_lesson (student_id, lesson_id) values (16, 2);
insert into student_lesson (student_id, lesson_id) values (16, 3);
insert into student_lesson (student_id, lesson_id) values (16, 4);
insert into student_lesson (student_id, lesson_id) values (16, 5);