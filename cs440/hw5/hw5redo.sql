/*
Barry Martin
CS 440 HW5 Second Bite
Feb 26th, 2014
*/


/* 3. */
select a.name,a.grade,b.name,b.grade
from mountaineer a
join likes on a.id=id1
join mountaineer b on b.id=id2
where (b.id,a.id) in
(select * from likes)
and a.id<b.id;

/* 6. */
select a.name,a.grade,b.name,b.grade
from mountaineer a
join likes on a.id=id1
join mountaineer b on id2=b.id
where b.id not in (select id1 from likes);

/* 7. */
select name,grade
from mountaineer m
where not exists
(select 1 from friend where id1=m.id and id2 in (select id from mountaineer where grade=m.grade))
order by grade,name;

/* 8. */
select name,grade
from mountaineer m
where not exists
(select 1 from friend where id1=m.id and id2 not in (select id from mountaineer where grade=m.grade));


/* 9. */
select a.name,a.grade,b.name,b.grade,c.name,c.grade
from mountaineer a
join likes on a.id=id1
join mountaineer b on b.id=id2
join mountaineer c on (a.id,c.id) in (select id1,id2 from friend) and (b.id,c.id) in (select id1,id2 from friend)
where (a.id,b.id) not in (select id1,id2 from friend);

/* 18. */
insert into friend
select a.id1,b.id1
from friend a
join friend b on a.id2=b.id1
where (a.id1,b.id2) not in (select id1,id2 from friend)
and a.id1!=b.id2;
select * from friend;