
create schema telecommDB;

use telecommDB;

CREATE TABLE users (
username VARCHAR(45) NOT NULL ,
password VARCHAR(200) NOT NULL ,
enabled TINYINT NOT NULL DEFAULT 1 ,
PRIMARY KEY (username));

 CREATE TABLE user_roles (
user_role_id int(11) NOT NULL AUTO_INCREMENT,
username varchar(45) NOT NULL,
role varchar(45) NOT NULL,
PRIMARY KEY (user_role_id),
UNIQUE KEY uni_username_role (role,username),
KEY fk_username_idx (username),
CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));


create table plan(
plan_id integer primary key,
plan_name varchar(50),
local_rate integer,
national_rate integer);

create table customer(
phone_no bigint primary key,
name varchar(50) references users(username),
age integer,
gender char,
address varchar(50),
plan_id integer references plan(plan_id)

);

create table calldetails(
id integer primary key,
called_by bigint,
called_to bigint,
called_on date,
duration integer);

create table friendfamily(
id integer primary key AUTO_INCREMENT,
phone_no bigint references customer(phone_no),
friend_and_family BIGINT 
);

   

INSERT INTO users(username,password,enabled)
VALUES ('User','$2a$10$Ot1clO5pw9wAt7o.ClbRf.98pDiYfrFKLWhqjJevERJvQ0d2C0NaS', 1);

INSERT INTO users(username,password,enabled)
VALUES ('Steve','$2a$10$ahDOConumGhCYRTO2Ogp.OuH.2tWrqMnoNg9aXwltRF3KemEwDF.K', 1);


insert into user_roles(username,role) values ('User','ROLE_CUSTOMER');

insert into user_roles(username,role) values ('Steve','ROLE_ADMIN');


insert into plan values(1,'plan1',60,60);
insert into plan values(2,'plan2',10,20);
insert into plan values(3,'plan3',30,60);

insert into customer values(9009009001,'User',25,'M','Tokyo',1);
insert into customer values(9009009002,'Steve',32,'M','Dublin',2);

insert into calldetails values(1,9009009001,9009009002,'2017-08-12',34);
insert into calldetails values(2,9009009002,9009009003,'2017-08-11',4);
insert into calldetails values(3,9009009003,9009009001,'2017-08-10',14);
insert into calldetails values(4,9009009001,9009009002,'2017-08-13',3);
insert into calldetails values(5,9009009002,9009009003,'2017-08-14',55);
insert into calldetails values(6,9009009003,9009009001,'2017-08-15',126);
insert into calldetails values(7,9009009001,9009009002,'2017-08-16',79);
insert into calldetails values(8,9009009002,9009009003,'2017-08-17',3);

insert into friendfamily values(1,9009009001,9009009002);
insert into friendfamily values(2,9009009001,9009009003);
insert into friendfamily values(3,9009009002,9009009005);
insert into friendfamily values(4,9009009003,9009009001);