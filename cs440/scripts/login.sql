rem 
rem Stored in SQL_PATH ($HOME) 
rem 
SET PAGESIZE 9999 
SET LINESIZE 140 
SET NEWPAGE 0 
rem SET TIME ON 
SET ARRAYSIZE 100 
rem SET AUTOCOMMIT OFF 
SET serveroutput on 
define   _editor =  'emacs -nw' 
rem SHOWMODE ON 
rem SET TIMING ON 
set trimspool on 
set long 5000 
column plan_plus_exp format a80 
column global_name new_value gname 
set sqlprompt 'sqlplus>' 
rem '&_user>'
SET ECHO ON 
