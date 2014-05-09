/* 15. */
update mountaineer set Grade=
    case upper(Grade)
    when 'FR' then 'SO'
    when 'SO' then 'JR'
    when 'JR' then 'SR'
   else 'GR' end;
select * from mountaineer;


/* 16. */
delete from mountaineer where grade='GR';
select * from mountaineer;

/* 17. */
delete from friend where id1 not in (select id  from mountaineer) or id2 not in
 (select id from mountaineer);
delete from likes where id1 not in (select id  from mountaineer)  or id2 not in
 (select id from mountaineer);
select (select count(*) from friend) + (select count(*) from likes) as "Total Rows" from dual;

