/*

   Barry Martin
   CS 440
   Assignment 5
   February 21, 2014

*/

/* 1. */
select name from mountaineer where id in (select id1 from friend where id2 in (select id from mountaineer where name='Gabriel'));

/* 2. */
with x as
(select * from mountaineer natural join grade left join likes on mountaineer.id=likes.id1)
select name,grade, (select name  from x d where a.id2=d.id) "Person Liked", (select grade from x d where a.id2=d.id) "GR" from x a
where a.seq-(select seq from x b where a.id2=b.id) >= 2;

/* 3. */
select name,grade from mountaineer where id in (select id1 from likes where id1 in (select id2 from likes)  and id in (select id2 from likes where id2 in (select id1 from likes)));

/* 4. */
select  name "Person A",grade "GR A",
(select name from mountaineer where id=a.id2) "Person B",
(select grade from mountaineer where id=a.id2) "GR B",
(select name from mountaineer where id=b.id2) "Person C",
(select grade from mountaineer where id=b.id2) "GR C"
from likes a join likes b on a.id2=b.id1 join mountaineer on a.id1=mountaineer.id
where a.id1!=b.id2;

/* 5. */
select name,grade from mountaineer where id not in (select id1 from likes) and id not in (select id2 from likes) order by grade,name;

/* 6. */
select name,grade from mountaineer where id not in (select id1 from likes where id1 not in (select id2 from likes));

/* 7. */
with x as
(select * from friend right join mountaineer on friend.id1=mountaineer.id natural join grade)
select * from x a
where a.grade = all (select grade from x b where a.id2=b.id)
order by grade,name;

/* 8. */
with x as
(select * from friend right join mountaineer on friend.id1=mountaineer.id natural join grade)
select * from x a
where a.grade != all (select grade from x b where a.id2=b.id)
order by grade,name;


/* 9. */
select distinct(select name from mountaineer where id=a.id1) A,
(select grade from mountaineer where id=a.id1) "GR A",
(select name from mountaineer where id=a.id2) B,
(select grade from mountaineer where id=a.id2) "GR B",
(select name from mountaineer where id=c.id2) "C",
(select grade from mountaineer where id=c.id2) "GR C"
from likes a join friend b on a.id1=b.id1 join friend c on a.id1=c.id1
where a.id1 in (select id2 from friend) and a.id2 in (select id2 from friend) and a.id1!=b.id2 and a.id2!=b.id2 and a.id2!=c.id2;

/* 10. */
select count(name)-count(distinct name) "Number of Duplicates" from mountaineer;

/* 11. */
select avg(count(id1)) "Average Number of Friends" from friend group by id1;

/* 12. */
select
(select count(id1) from friend where id2=(select id from mountaineer where name='Cassandra'))
+
(select count(distinct id2) from friend where id1 in  (select id2 from friend where id1=(select id  from mountaineer where name='Cassandra'))
and id2 not in(select id from mountaineer where name='Cassandra'))
as "Total Number of Friends"
from dual;


/* 13. */
select name,grade from mountaineer where id in (select id2 from likes group by id2 having count(*) >=2);


/* 14. */
select name,grade from mountaineer where id in (select id2 from friend group by id2 having count(*) = (select max(count(id2)) from friend group by id2));


/* 15. */
update mountaineer set Grade=
    case upper(Grade)
    when 'FR' then 'SO'
    when 'SO' then 'JR'
    when 'JR' then 'SR'
   else 'GR' end;
select * from mountaineer;


/* 16. */
delete from mountaineer where grade='GR';
select * from mountaineer;

/* 17. */
delete from friend where id1 not in (select id  from mountaineer) or id2 not in (select id from mountaineer);
delete from likes where id1 not in (select id  from mountaineer)  or id2 not in (select id from mountaineer);
select (select count(*) from friend) + (select count(*) from likes) as "Total Rows" from dual;

/* 18. */
insert into friend select distinct a.id1,b.id2 from friend a join friend b on a.id2=b.id1
where a.id2=b.id1 and a.id1!=b.id2;
select * from friend;
