last1(A, [A]).
last1(A, [_|B]) :-
    last1(A, B).

append([], A, A).
append([A|B], C, [A|D]) :-
    append(B, C, D).

last2(C, D) :-
    append(_, [C], D).

insert(A,B,C,D):-
    append(F,[A|G],C),
    append(F,[A,B|G],D).

demo(X) :-
    nl,
    portray_clause(goal=X),
    forall(X,(print(result=X),nl)).
    
supplier( 1  , ramesh   , 32  , ahmedabad , 2000.00  ).
supplier( 2  , khilan   , 25  , delhi     , 1500.00  ).
supplier( 3  , kaushik  , 23  , kota      , 2000.00  ).
supplier( 4  , chaitali , 25  , mumbai    , 6500.00  ).
supplier( 5  , hardik   , 27  , bhopal    , 8500.00  ).
supplier( 6  , komal    , 22  , mp        , 4500.00  ).
supplier( 7  , muffy    , 24  , indore    , 10000.00 ).

order( 102 , '2009-10-08 00:00:00' , 3 , 3000   ).
order( 100 , '2009-10-08 00:00:00' , 3 , 1500   ).
order( 101 , '2009-11-20 00:00:00' , 2 , 1560   ).
order( 103 , '2008-05-20 00:00:00' , 4 , 2060   ).

q4(ID,NAME,AGE,AMOUNT):-
supplier(ID,NAME,AGE,_,_),
order(_,_,ID,AMOUNT).


:- style_check([-singleton]).
:- demo(member(X,[1,2,3])).
:- demo(last1(X,[1,2,3])).
:- demo(last2(X,[1,2,3])).
:- demo(insert(word,anotherword,[1,2,3,4,word],X)).
:- demo(q4(_,_,_,_)).
:- halt.
