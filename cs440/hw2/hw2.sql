-- Barry Martin
-- CS 440
-- Assignment 2
-- Januart 22,2014

set echo on
-- Create tables
@@utlsample.sql
@@sup_part.sql




--1a
alter table dept add constraint deptno_pk primary key(deptno) deferrable initially immediate;                   
       
--1b
alter table dept modify dname constraint dname_un unique deferrable initially immediate
		  	      constraint dname_nn not null deferrable initially immediate;

--2a
alter table emp add constraint empno_pk primary key(empno) deferrable initially immediate;

--2b
alter table emp modify ename constraint ename_un unique deferrable initially immediate
		  	     constraint ename_nn not null deferrable initially immediate;

--2c
alter table emp modify mgr constraint mgr_fk references emp(empno) deferrable initially immediate;       		  

--2d
alter table emp modify deptno constraint deptno_fk references dept(deptno) deferrable initially immediate;

--2e
alter table emp modify sal constraint sal_chkbal check (sal between 500 and 10000) deferrable initially immediate;

--3a
alter table s add constraint s_pk primary key(s#) deferrable initially immediate;

--3b
alter table s modify sname constraint sname_un unique deferrable initially immediate
		  	   constraint sname_nn not null deferrable initially immediate;

--4a
alter table p add constraint p_pk primary key(p#) deferrable initially immediate;

--4b
alter table p  modify pname constraint pname_un unique deferrable initially immediate
		  	    constraint pname_nn not null deferrable initially immediate;

--5a
alter table sp add constraint sp_k primary key(s#,p#) deferrable initially immediate;

--5b
alter table sp modify qty constraint qty_c check (qty >= 0) deferrable initially immediate;

--5c
alter table sp modify s# constraint s#_fk references s(s#) deferrable initially immediate;
alter table sp modify p# constraint p#_fk references p(p#) deferrable initially immediate;

--6
create index emp_deptnoindex on emp(deptno);

--7
select add_months(hiredate, -12) from emp where hiredate > sysdate;

--8
select index_name from user_indexes;

--9
select constraint_name,table_name from user_constraints; 
