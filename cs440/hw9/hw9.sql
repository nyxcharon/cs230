/*                                                                                                                                                                                                               
Barry Martin                                                                                                                                                                                          
CS 440                                                                                                                                                                                           
HW #9                                                                                                                                                                                                            
Apr 7th, 2014                                                                                                                                                                                                    
*/                                                                                                                                                                                         

/* WU tables
WU                             DEPARTMENTS
WU                             CLASSES
WU                             STUDENTS
*/

set serveroutput on;
set echo on;
drop table classes_ot;
drop table student_plus;
drop type classes_ref_ty;
drop type classes_ty;

/* 1. */
create or replace type classes_ty as object(
       CRN	  varchar(25),
       Department varchar2(8),
       CourseTitle	varchar2(25)
)
/

/* 2. */
create table classes_ot of classes_ty;
alter table classes_ot add constraint CRN_PK primary key(CRN) deferrable initially immediate;
/* 3. */
insert into classes_ot select * from wu.classes;

/* 4. */
create or replace type classes_ref_ty as  table of ref classes_ty
/

/* 5. */
create table student_plus(
       student#		varchar2(11),
       student_name	varchar2(10),
       major		varchar2(8),
       advisor		varchar2(10),
       enrolled		classes_ref_ty,
       constraint stu#_pk primary key(student#)
)
nested table enrolled store as classes_ref_ty_tab;

/* 6. */
begin
	insert into student_plus select student_id, name, 
	dept, advisor, classes_ref_ty() from wu.students;
	for var_stu in (select student_id from wu.students) loop
	    insert into table(select enrolled from student_plus where student#=var_stu.student_id)
	    select ref(a) from classes_ot a where CRN in 
	    	   (select * from table (select classes from wu.students where student_id=var_stu.student_id));
	end loop; 
end;
/

/* 7. */
select distinct student_name from student_plus, table(enrolled) where deref(column_value).crn in ('45673', '34228'); 

/  8. */
select CourseTitle from classes_ot, table(select enrolled from student_plus where lower(student_name)='sherman') 
where deref(column_value).crn=crn;

/* 9. */
select student_name from student_plus where lower(advisor)='vanscoy';

/* 10. */
select count(distinct student#) NUM_ENROLLED from student_plus, table(enrolled) 
where lower(deref(column_value).CourseTitle)='linear algebra';

/* 11. */
update student_plus set major='PHYSICS' where lower(student_name)='adams';

/* 12. */
create or replace procedure AddClass(in_stu_num in varchar2, in_crn in varchar2)
is
begin
	insert into table(select enrolled from student_plus where student#=in_stu_num)
	select ref(a) from classes_ot a where CRN=in_crn;
end;
/


/* 13. */
declare
	v_hood#	varchar2(20);
begin
	select student# into v_hood# from student_plus where lower(student_name)='hood';
	AddClass(v_hood#, 31245);
end;
/

/* 14. */
select deref(column_value).crn HOODS_CLASSES from table(select enrolled from student_plus where lower(student_name)='hood');

/* 15. */
create or replace procedure DeleteClass(in_stu_num in varchar2, in_crn in varchar2)
is
	cursor crn_cursor is select deref(column_value).crn x from
                                table(select enrolled from student_plus where student#=in_stu_num);
begin
	for v_crn in crn_cursor loop
	    if v_crn.x = in_crn then
	       delete from table(select enrolled from student_plus where student#=in_stu_num) where deref(column_value).crn=in_crn;
	       return;
	    end if;
        end loop;
	raise_application_error(-20200, 'Student not enrolled in that class');
end;
/

/* 16. */
declare
	v_sherman# varchar2(20);
begin
	select student# into v_sherman# from student_plus where lower(student_name)='sherman';
	DeleteClass(v_sherman#, 34129);
end;
/

/* 17. */
select deref(column_value).crn SHERMANS_CLASSES from table(select enrolled from student_plus where lower(student_name)='sherman');

/* 18. */
select student_name from student_plus a where 
(select count(deref(column_value).crn) from 
table(select enrolled from student_plus where a.student#=student#)) = 0;
