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


select * from subjects;
select * from student;
select * from teacher;

alter table teacher add subjectID int not null;
alter table teacher add constraint subjectID_FK foreign key (subjectID) references subjects(subjectId);