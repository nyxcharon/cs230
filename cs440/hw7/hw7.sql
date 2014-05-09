/*
Barry Martin
CS 440
HW #7
March 24, 2014
*/


/* 1. */
create or replace trigger bonus_trigger
after update or insert of comm on emp
for each row
declare 
	v_current_time number;
	v_emp_present number;
begin
	v_emp_present :=0;
	select to_number(to_char(sysdate, 'HH24.mi')) into v_current_time from dual;
	select count(ename) into v_emp_present from bonus where :new.ename = bonus.ename;

	if v_current_time < 8 or v_current_time > 15 then
	   dbms_output.put_line('Non working hours: commision not added to bonus');
	elsif v_emp_present = 0 then
	   insert into bonus values (:new.ename,:new.job,:new.sal,:new.comm);
	else
	   update bonus set comm = :new.comm where ename = :new.ename;
	end if;
end;
/


/* 2. */
create or replace trigger salary_trigger
before update of sal,job on emp
for each row
declare 
	job varchar2(9);
	v_salary_limit number;
	v_comm number;
begin
	job := upper(:old.job);
	v_salary_limit := 0;

	if job = 'ANALYST' then
	   v_salary_limit:=4000;
	elsif job = 'CLERK' then
	   v_salary_limit:=1500;
	elsif job = 'MANAGER' then
	   v_salary_limit:=3500;
	elsif job = 'SALESMAN' then
	   v_salary_limit:=2000;
	end if;
 
	if job != upper(:new.job) and :new.sal > v_salary_limit then
	   raise_application_error(-20100, 'Job modification not permitted.');
	elsif v_salary_limit != 0 and :new.sal > v_salary_limit then
	   v_comm := :new.sal - v_salary_limit;
	   :new.sal := v_salary_limit;
	   :new.comm := v_comm;
	end if;
end;
/


/* 3. */
create or replace trigger heavy_weight_trigger
before insert or update of weight on p
for each row
declare

begin
	if :new.weight > 10 then
	   :new.color := 'red';
	end if;
end;
/

/* 4. */
create or replace package light_weight_pkg as
       type ridArray is table of char(2) index by binary_integer;
       v_row_list ridArray;
end;
/ 

create or replace trigger light_weight_trigger
for update or insert of weight on p
COMPOUND TRIGGER
	 v_weight number;
AFTER EACH ROW IS
begin
	light_weight_pkg.v_row_list(light_weight_pkg.v_row_list.count+1) := :new.p#;	
end after each row;

AFTER STATEMENT IS
begin
	for i in 1 .. light_weight_pkg.v_row_list.count loop
                select weight into v_weight from p where p# = light_weight_pkg.v_row_list(i);
                if v_weight < 8 then
                        update p set color = 'blue' where p# = light_weight_pkg.v_row_list(i);
                end if;
        end loop;
end after statement;
end;
/


