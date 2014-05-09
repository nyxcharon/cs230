/*
Barry Martin
Myid - bmartin4
CS 440
HW #8 second bite
April 4th, 2014
*/

/* 1. */
create or replace type studentLikeArray as VARRAY(20) of varchar2(20)
/
create or replace function Likers (i_CRN in number)
return studentLikeArray is
        v_larray studentLikeArray := studentLikeArray();

	cursor v_crn is select name from WVU where 
	CRN in (select CRN2 from likes where i_CRN=CRN);
begin
	for person in v_crn loop
	    v_larray(v_larray.count+1):=person.name;
	end loop;
	
	if v_larray.count > 0 then
	   return v_larray;
	else 
	   return null;
        end if;	   

	
        
end Likers;
/

/* 3. */
create or replace trigger student_likes_trigger
before insert ON WVU
for each row
begin
	insert into likes select :new.crn, crn from wvu where grade=:new.grade;
END student_likes_trigger;
/

