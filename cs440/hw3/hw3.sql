/*
Barry Martin
CS 440
Assignment 3
January 31,2014
*/



/*1.*/
update emp
set MGR = (select MGR from emp where ENAME='TURNER') 
where ename='SCOTT';
select * from emp;
rollback;

/*2.*/
insert into dept ( DEPTNO,DNAME,LOC) values (50,'PR','MORGANTOWN');
update emp
set DEPTNO = (select DEPTNO from dept where DNAME='PR')
where  DEPTNO = (select DEPTNO from dept where DNAME='RESEARCH');
select * from emp;
rollback;

/*3.*/
select ENAME from emp 
where MGR not in (select MGR from emp where ENAME='MARTIN') or MGR is null;

/*4.*/
break on sname;
SELECT sname, pname from s
LEFT JOIN sp using (s#)
LEFT JOIN p using (p#)
ORDER BY sname, pname;
clear breaks;

/*5.*/
select distinct pname from
sp natural join p
where pname != 'stapler' and
s# in (select s# from sp natural join p where pname='stapler');

/*6.*/
select pname from p 
where p# not in (select distinct p# from s natural join sp where city='Bonn');

/*7.*/
SELECT sname FROM s
WHERE 3 <= (select count(distinct city)
from sp natural join p where s# = s.s#);

/*8.*/
SELECT dname, avg(sal) from dept
LEFT JOIN emp using (deptno) group by (dname);

/*9.*/
select dname from dept 
where deptno not in (select deptno from emp);

/*10.*/
select dname,ename,sal 
from emp join dept using (deptno)  
where sal in (select max(sal) from emp join dept using (deptno) group by dname);


