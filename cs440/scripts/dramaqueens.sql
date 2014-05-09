drop table Friend;
drop table Likes;
drop table WVU;
drop table year;

create table year(
abbrev char(2) primary key,
fullname varchar2(30),
position number);

create table WVU(CRN number primary key, 
name varchar2(30), 
grade char(2) references year);
create table Friend(
CRN1 number references WVU, 
CRN2 number references WVU,
primary key (CRN1, CRN2));
create table Likes(
CRN1 number references WVU, 
CRN2 number references WVU,
primary key (CRN1, CRN2));

insert into year values ('FR', 'Freshman', 1);
insert into year values ('SO', 'Sophomore', 2);
insert into year values ('JR', 'Junior', 3);
insert into year values ('SR', 'Senior', 4);
insert into year values ('GR', 'Graduate', 5);
insert into WVU values (1510, 'Jordan', 'FR');
insert into WVU values (1689, 'Gabriel', 'FR');
insert into WVU values (1381, 'Tiffany', 'FR');
insert into WVU values (1709, 'Cassandra', 'FR');
insert into WVU values (1101, 'Haley', 'SO');
insert into WVU values (1782, 'Andrew', 'SO');
insert into WVU values (1468, 'Kris', 'SO');
insert into WVU values (1641, 'Brittany', 'SO');
insert into WVU values (1247, 'Alexis', 'JR');
insert into WVU values (1316, 'Austin', 'JR');
insert into WVU values (1911, 'Gabriel', 'JR');
insert into WVU values (1501, 'Jessica', 'JR');
insert into WVU values (1304, 'Jordan', 'SR');
insert into WVU values (1025, 'John', 'SR');
insert into WVU values (1934, 'Kyle', 'SR');
insert into WVU values (1661, 'Logan', 'SR');

insert into Friend values (1510, 1381);
insert into Friend values (1510, 1689);
insert into Friend values (1689, 1709);
insert into Friend values (1381, 1247);
insert into Friend values (1709, 1247);
insert into Friend values (1689, 1782);
insert into Friend values (1782, 1468);
insert into Friend values (1782, 1316);
insert into Friend values (1782, 1304);
insert into Friend values (1468, 1101);
insert into Friend values (1468, 1641);
insert into Friend values (1101, 1641);
insert into Friend values (1247, 1911);
insert into Friend values (1247, 1501);
insert into Friend values (1911, 1501);
insert into Friend values (1501, 1934);
insert into Friend values (1316, 1934);
insert into Friend values (1934, 1304);
insert into Friend values (1304, 1661);
insert into Friend values (1661, 1025);
insert into Friend select CRN2, CRN1 from Friend;

insert into Likes values(1689, 1709);
insert into Likes values(1709, 1689);
insert into Likes values(1782, 1709);
insert into Likes values(1911, 1247);
insert into Likes values(1247, 1468);
insert into Likes values(1641, 1468);
insert into Likes values(1316, 1304);
insert into Likes values(1501, 1934);
insert into Likes values(1934, 1501);
insert into Likes values(1025, 1101);
