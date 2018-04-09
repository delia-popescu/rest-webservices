insert into course (id, name, description) values (1, 'Data structures & algorithms', 'Data structures & algorithms');
insert into course (id, name, description) values (2, 'OOP programming', 'OOP programming');

insert into student (id, name) values (1, 'Popescu Ionut');
insert into student (id, name) values (2, 'Popescu Maria');

insert into student_courses (student_id, courses_id) values (1, 1);
insert into student_courses (student_id, courses_id) values (1, 2);
insert into student_courses (student_id, courses_id) values (2, 1);
