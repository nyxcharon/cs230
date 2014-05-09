/* 
   Barry Martin
   CS440 
   Assigment 4, second bite
   February 17th, 2014
*/

/* 6. */
with x as (select * from sp natural join p)
select sname, a.pname, b.pname, c.pname
from s
join x a on s.s#=a.s#
join x b on a.s#=b.s# and a.p# != b.p# and a.qty>b.qty
join x c on b.s#=c.s# and b.p# != c.p# and b.qty>c.qty
where c.qty >= all
(select qty from sp where s#=s.s# and p# not in (a.p#,b.p#));

/* 8. */
select sname,pname,qty,
(select max(qty) from sp where s#=sp.s#) "Max Qty Supplied",
(select max(qty) from sp where s#=sp.s#)  "Max Qty for P#"
from s left join sp on s.s#=sp.s#
left join p on p.p#=sp.p#;
