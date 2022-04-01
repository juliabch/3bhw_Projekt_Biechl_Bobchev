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
    subjects varchar (50) not null,
     
constraint teacherId_PK primary key(teacherId)
);
