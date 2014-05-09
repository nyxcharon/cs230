/*
Barry Martin
Myid - bmartin4
CS 440
HW #6
Feb 28th, 2014
*/


set serveroutput on format wrapped size 1000000;
set line 140;

CREATE OR REPLACE PROCEDURE center_text (text in out varchar2)
IS
	spaces number;
	spacer varchar2(70);
BEGIN
	spaces := (140 - length(text)) / 2;
	spacer := '';
	FOR x in 2..spaces LOOP
	    spacer := spacer || ' ';
	    end LOOP;
	    text := spacer || text || spacer;
END center_text;
/

CREATE OR REPLACE PROCEDURE Salary_Report
IS
	v_curline varchar2(140);
	v_curdept varchar2(20);
	v_cur_ename varchar2(20);
	v_cur_sal number(7,2);

BEGIN
	--Date
	v_curline := initcap(to_char(sysdate, 'FMDay, FMMonth DD, YYYY'));
	center_text(v_curline);
	dbms_output.put_line(v_curline);
	dbms_output.put_line('');
	
	--Header
	v_curline := 'Regal Lager';
	center_text(v_curline);
	dbms_output.put_line(v_curline);
	dbms_output.put_line('');
	v_curline := 'More than a Great Brew - a Palindrome';
	center_text(v_curline);
	dbms_output.put_line(v_curline);
	dbms_output.put_line('');
	dbms_output.put_line('');
	dbms_output.put_line('');
	
	--Department Salaries
	v_curline := 'Departmental Salary Report';
        center_text(v_curline);
        dbms_output.put_line(v_curline);
        dbms_output.put_line('');

	FOR v_deptno in (select deptno from dept) LOOP
	    select initcap(dname) into v_curdept from dept where dept.deptno = v_deptno.deptno;
	    v_curline := 'Department: ' || v_curdept;
            center_text(v_curline);
            dbms_output.put_line(v_curline);

            FOR v_empno in (select empno from emp where deptno = v_deptno.deptno) LOOP
		select ename into v_cur_ename from emp where empno = v_empno.empno;
		select sal into v_cur_sal from emp where empno = v_empno.empno;
		v_curline := v_cur_ename || '' || to_char(v_cur_sal, '$99,999.99');
		center_text(v_curline);
		dbms_output.put_line(v_curline);
	    end LOOP;

	    select sum(sal) into v_cur_sal from emp where deptno = v_deptno.deptno;
	    v_curline := 'Total ' || v_curdept || ' salary: ' || to_char(v_cur_sal, '$99,999.99');
	    center_text(v_curline);
	    dbms_output.put_line(v_curline);
	    select avg(sal) into v_cur_sal from emp where deptno = v_deptno.deptno;

	    v_curline := 'Average ' || v_curdept || ' salary: ' || to_char(v_cur_sal, '$99,999.99');
	    center_text(v_curline);
            dbms_output.put_line(v_curline);
	    dbms_output.put_line('');		
        end LOOP;
	dbms_output.put_line('');
	dbms_output.put_line('');
	
	--Company Salries
	v_curline := 'Company Salaries:';
        center_text(v_curline);
        dbms_output.put_line(v_curline);

	--Regal
	select sum(sal) into v_cur_sal from emp;
	v_curline := 'Total Regal Lager Salaries: ' || to_char(v_cur_sal, '$99,999.99');
        center_text(v_curline);
	dbms_output.put_line(v_curline);
	select avg(sal) into v_cur_sal from emp;
        v_curline := 'Average Regal Lager Salaries: ' || to_char(v_cur_sal, '$99,999.99');
        center_text(v_curline);
	dbms_output.put_line(v_curline);
	dbms_output.put_line('');
	
	--End
	v_curline := 'End of Report';
        center_text(v_curline);
	dbms_output.put_line(v_curline);
END Salary_Report;
/

