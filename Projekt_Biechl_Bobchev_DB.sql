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
select * from student;
select * from teacher;

drop table teacher_subjects;