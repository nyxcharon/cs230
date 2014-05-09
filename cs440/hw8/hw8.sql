/*
Barry Martin
Myid - bmartin4
CS 440
HW #8
Mar 31st, 2014
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

        return v_larray;
end Likers;
/


/* 2. */
create or replace procedure Heremitify (CRN in number)
is
	 
begin
	delete from likes where  crn1=CRN or crn2=CRN;
	delete from friend where  crn1=CRN or crn2=CRN;
end Heremitify;
/


/* 3. */
create or replace trigger student_likes_trigger
for insert ON WVU
compound trigger

  type stu_ty is table of  wvu%rowtype index by binary_integer;
  stu_tab stu_ty;
  
  AFTER EACH ROW IS
  BEGIN
    stu_tab(stu_tab.count+1).crn := :new.crn;
  END AFTER EACH ROW;

  AFTER STATEMENT IS
  BEGIN
	for cntr in 1..stu_tab.count loop
	    for c1_rec in (select * from wvu where grade=(select grade from wvu where crn=stu_tab(cntr).crn) and crn!=stu_tab(cntr).crn) loop		
            	insert into likes (crn1,crn2)  values (stu_tab(cntr).crn, c1_rec.crn);                   
	    end loop;
	end loop;
  END AFTER STATEMENT;

END student_likes_trigger;
/

/* 4. */
create or replace trigger new_stud_gr_trigger
before insert on WVU
for each row
begin
	if :new.grade is NULL then
	   :new.grade := 'FR';
	   end if;
end;
/

/* 5. */
create or replace trigger new_stud_fr_trigger
for insert on friend
compound trigger

type stu_ty is table of  friend%rowtype index by binary_integer;
  stu_tab stu_ty;
  
  AFTER EACH ROW IS
  BEGIN
    stu_tab(stu_tab.count+1).crn1 := :new.crn1;
    stu_tab(stu_tab.count).crn2 := :new.crn2;
  END AFTER EACH ROW;

  AFTER STATEMENT IS
  BEGIN
	for cntr in 1..stu_tab.count loop
	    if stu_tab(cntr).crn2!=NULL and stu_tab(cntr).crn1 != NULL then
	       insert into friend (crn1,crn2) values (stu_tab(cntr).crn2,stu_tab(cntr).crn1);
	    end if;
	end loop;
  END AFTER STATEMENT;

end;
/

/* 6. */
create or replace trigger student_grade_trigger
for update ON WVU
compound trigger

  type stu_ty is table of  wvu%rowtype index by binary_integer;
  stu_tab stu_ty;
  old_stu_tab stu_ty;

  AFTER EACH ROW IS
  BEGIN
    stu_tab(stu_tab.count+1).grade := :new.grade;
    stu_tab(stu_tab.count).crn := :new.crn;
    old_stu_tab(old_stu_tab.count).grade := :old.grade;
  END AFTER EACH ROW;

  AFTER STATEMENT IS
  BEGIN
    for cntr in 1..stu_tab.count loop
        update wvu set grade=stu_tab(cntr).grade where grade=old_stu_tab(cntr).grade;
    end loop;	
  END AFTER STATEMENT;

END student_grade_trigger;
/


/* 7. */
create or replace trigger student_graduate_trigger
for update  of GRADE ON WVU
compound trigger

  type stu_ty is table of  wvu%rowtype index by binary_integer;
  stu_tab stu_ty;

  AFTER EACH ROW IS
  BEGIN
    stu_tab(stu_tab.count+1).crn := :new.crn;
  END AFTER EACH ROW;


  AFTER STATEMENT IS
  BEGIN    
        for cntr in 1..stu_tab.count loop
	    Heremitify(stu_tab(cntr).crn);
	end loop;

	delete from wvu where grade='GR';
  END AFTER STATEMENT;

END student_graduate_trigger;
/



/* 8. */
create or replace trigger student_friend_trigger
before update of CRN2 on Likes
for each row
begin	 
	 delete Friend where CRN1=:old.CRN2 and CRN2=:new.CRN2 or CRN1=:new.CRN2 and CRN2=:old.CRN2;
end;
/
