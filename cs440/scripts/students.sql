
/* Delete the tables if they already exist */
drop table Mountaineer;
drop table Friend;
drop table Likes;
drop table Grade;

/* Create the schema for our tables */
create table Mountaineer(ID number, name varchar2(30), grade char(2));
create table Friend(ID1 number, ID2 number);
create table Likes(ID1 number, ID2 number);
create table Grade(seq number, grade char(2));

/* Populate the tables with our data */
insert into Mountaineer values (1510, 'Jordan', 'FR');
insert into Mountaineer values (1689, 'Gabriel', 'FR');
insert into Mountaineer values (1381, 'Tiffany', 'FR');
insert into Mountaineer values (1709, 'Cassandra', 'FR');
insert into Mountaineer values (1101, 'Haley', 'SO');
insert into Mountaineer values (1782, 'Andrew', 'SO');
insert into Mountaineer values (1468, 'Kris', 'SO');
insert into Mountaineer values (1641, 'Brittany', 'SO');
insert into Mountaineer values (1247, 'Alexis', 'JR');
insert into Mountaineer values (1316, 'Austin', 'JR');
insert into Mountaineer values (1911, 'Gabriel', 'JR');
insert into Mountaineer values (1501, 'Jessica', 'JR');
insert into Mountaineer values (1304, 'Jordan', 'SR');
insert into Mountaineer values (1025, 'John', 'SR');
insert into Mountaineer values (1934, 'Kyle', 'SR');
insert into Mountaineer values (1661, 'Logan', 'SR');

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
insert into Friend select ID2, ID1 from Friend;

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

insert into Grade values (1, 'FR');
insert into Grade values (2, 'SO');
insert into Grade values (3, 'JR');
insert into Grade values (4, 'SR');
insert into Grade values (5, 'GR');

