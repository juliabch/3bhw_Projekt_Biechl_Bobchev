create database school_management collate utf8mb4_general_ci;

use school_management;

create table teacher(
	teacherId int unsigned not null auto_increment,
    l_name varchar (100) not null,
	f_name varchar (100) not null,
    bdate Date not null,
    gender int not null,
    mailAddress varchar (100) not null,
    formTeacher varchar (20) not null,
     
constraint teacherId_PK primary key(teacherId)
);

create table student(
	studentId int unsigned not null auto_increment,
    l_name varchar (100) not null,
	f_name varchar (100) not null,
    bdate Date not null,
    gender int not null,
    mailAddress varchar (100) not null,
    classRoom varchar (10) not null,
    class varchar (20) not null,
     
constraint studentId_PK primary key(studentId)
);

create table subjects(
	subjectId int unsigned not null auto_increment,
    subject_name varchar (100) not null,
     
constraint subjectId_PK primary key(subjectId)
);

create table teacher_subjects(
teacher_subject_id int unsigned not null auto_increment,
subject_Id int unsigned null,
teacher_Id int unsigned null,

constraint teacher_subject_id primary key(teacher_subject_id),
constraint subject_Id_Fk foreign key(subject_Id) references subjects(subjectId),
constraint teacherId_FK foreign key(teacher_Id) references teacher(teacherId)
);


select * from subjects;
drop table subjects;
select * from student;
select * from teacher;
select * from teacher_subjects;

select teacherId, l_name, f_name, bdate, gender, formTeacher, subject_name from teacher
join teacher_subjects on teacher_Id
join subjects on subject_id;

insert into teacher_subjects values(null, 1, 1);
insert into student value(null, "Hirschi", "Ian", '2004-09-27', 1, "ianhirschi@gmail.com", "S101", "3bhwii");
insert into student value(null, "Gendu", "Joni", '2004-08-24', 1, "gendu@gmail.com", "S101", "3bhwii");
insert into student value(null, "Brandi", "Oli", '2005-10-04', 1, "olibrandi@gmail.com", "S102", "2bhwii");
insert into student value(null, "Kirchi", "Jonas", '2004-03-16', 1, "kirchi@gmail.com", "S103", "3bhwii");
insert into student value(null, "Bobi", "Evi", '2005-05-06', 2, "veabobi@gmail.com", "I151", "1bhwii");

insert into teacher value(null, "Biechl", "Julia", '2005-09-01', 2, "biechl@gmail.com", "2ahwii");
insert into teacher value(null, "Haider", "Felix", '2004-01-21', 2, "haider@gmail.com", "2ahwii");
insert into teacher value(null, "Klaric", "Patrick", '2006-12-23', 2, "biechl@gmail.com", "2ahwii");
insert into teacher value(null, "Adami", "Johannes", '2005-08-19', 2, "adami@gmail.com", "2ahwii");

insert into subjects value(null, "Mathematik");
insert into subjects value(null, "Deutsch");
insert into subjects value(null, "Englisch");
insert into subjects value(null, "Informatik");

delimiter //
create procedure SubjectTeacher()
begin
	select f_name, l_name, subject_name from teacher as t join teacher_subjects as ts on t.teacherId = ts.teacher_Id
    join subjects as s on ts.subject_Id = subjectId;
end//
delimiter ;

drop procedure SubjectTeacher;
call SubjectTEacher();