--      Script to populate the Supplier Parts database


drop table sp;
drop table p;
drop table s;

create table s (s# char(2), sname char(5), status number,
        city char(10));

create table p (p# char(2), pname char(10), color char(5),
weight number(7,2), city char(10));


create table sp (s# char(2), p# char(2), qty number);

        insert into s(s#, sname, status, city) values('s1',  'smith', 10, 'Paris');
        insert into s(s#, sname, status, city)  values('s2',  'jones', 20, 'Bonn');
        insert into s(s#, sname, status, city)  values('s3',  'adams', 30, 'London');
        insert into s(s#, sname, status, city)  values('s4',  'clark', 40, 'Rome');
        insert into s(s#, sname, status, city)  values('s5',  'henry', 50, 'Dublin');

        insert into p values('p1',  'hammer',  'red',   2.6,  'Paris');
        insert into p values('p2',  'pliers',  'green', 3.1,  'London');
        insert into p values('p3',  'router',  'blue',  5.6,  'Paris');
        insert into p values('p4',  'stapler', 'red',   3.7,  'Bonn');
        insert into p values('p5',  'wrench',  'green', 2.6,  'Rome');
        insert into p values('p6',  'ladder',  'blue',  10.5, 'Paris');

        insert into sp values('s1', 'p1', 100);
        insert into sp values('s1', 'p2', 200);
        insert into sp values('s1', 'p3', 150);
        insert into sp values('s1', 'p4', 120);
        insert into sp values('s1', 'p5', 240);
        insert into sp values('s1', 'p6', 210);

        insert into sp values('s2', 'p2', null); 
        insert into sp values('s2', 'p4', 100);
        insert into sp values('s2', 'p5', 120);

        insert into sp values('s3', 'p1', 150);

        insert into sp values('s4', 'p2', 120);
        insert into sp values('s4', 'p3', 100);
        insert into sp values('s4', 'p4', 250);

commit;

(END) 
