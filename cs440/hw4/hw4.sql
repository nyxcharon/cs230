/*
	Barry Martin
	CS 440
	Assignment 4
	February 12, 2014
*/


/* 1. */
   --a.
   select distinct pname from p join sp using (p#) where s# = 's1' or s# = 's2' or s# = 's4';

   --b.
   select distinct pname from p join sp a using (p#) join sp b using(p#) join sp c using(p#) where a.s# = 's1' and b.s# = 's2' and c.s# = 's4';

/* 2. */
select sname from s join sp using (s#) where p# in (select p# from sp join p using (p#) where color='red') group by sname having count(p#) = (select count(p#) from p where color ='red');

/* 3. */
select distinct s# from sp where s# not in (select s# from sp where p# = 'p5') and s# in (select s# from sp where p#='p4');

/* 4. */
select sname from s join sp using (s#) where qty = (select max(qty) from sp where qty not in (select max(qty) from sp));

/* 5. */
select sname from s a where (select count(p#) from sp where a.s#=s#) >=2
intersect
select distinct sname from sp join s using (s#) where s# not in (select s# from sp where p#='p3');

/* 6. */
select sname,a.qty,b.qty,c.qty from s join sp a using (s#) join sp b using (s#) join sp c using(s#) where a.qty>b.qty and b.qty>c.qty and sname in (select sname from s d where (select count(p#) from sp where d.s#=s#) >=3) group by sname,a.qty,b.qty,c.qty;

/* 7. */
select sname,min(qty) from s join sp using (s#) group by sname having max(qty) > (select max(qty) from sp where p#='p2');

/* 8. */
break on sname;
select sname, pname, qty, 
max(qty) over (partition by sname) "Max Qty Supplied",
max(qty) over (partition by pname) "Max Qty for P#"
from sp a join s b on a.s#=b.s# join p c on a.p#=c.p#;
clear breaks;

/* 9. */
select ename, level-1 "Level" from emp
where upper(ename)!='ADAMS' and upper(job)!='ANALYST'
start with upper(ename)='ADAMS' connect by prior mgr=empno;

/* 10. */
col ENAME format a15;
select lpad(' ', 2*(level-1))||ename ENAME,
(select ename from emp where empno=a.mgr) mgr, level-1 "level"
from emp a where lower(job)!='clerk'
start with mgr is null connect by prior empno=mgr;

