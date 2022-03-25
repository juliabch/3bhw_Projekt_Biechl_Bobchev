create database school_management collate utf8mb4_general_ci;

create table teacher(
	int teacherId unsigned not null auto_increment,
    
    
constraint teacherId_PK primary key(teacherId)
)