 supplier(s1, smith, 20, london).
 supplier(s2, jones, 10, paris).
 supplier(s3, blake, 30, paris).
 supplier(s4, clark, 20, london).
 supplier(s5, adams, 30, athens).

 part(p1, nut, red, 12, london).
 part(p2, bolt, green, 17, paris).
 part(p3, screw, blue, 17, rome).
 part(p4, screw, red, 14, london).
 part(p5, cam, blue, 12, paris).
 part(p6, cog, red, 19, london).

 job(j1, sorter, paris).
 job(j2, display, rome).
 job(j3, ocr, athens).
 job(j4, console, athens).
 job(j5, raid, london).
 job(j6, eds, oslo).
 job(j7, tape, london).

 shipment(s1, p1, j1, 200).
 shipment(s1, p1, j4, 700).
 shipment(s2, p3, j1, 400).
 shipment(s2, p3, j2, 200).
 shipment(s2, p3, j3, 200).
 shipment(s2, p3, j4, 500).
 shipment(s2, p3, j5, 600).
 shipment(s2, p3, j6, 400).
 shipment(s2, p3, j7, 800).
 shipment(s2, p5, j2, 100).
 shipment(s3, p3, j1, 200).
 shipment(s3, p4, j2, 500).
 shipment(s4, p6, j3, 300).
 shipment(s4, p6, j7, 300).
 shipment(s5, p2, j2, 200).
 shipment(s5, p2, j4, 100).
 shipment(s5, p5, j5, 500).  
 shipment(s5, p5, j7, 100).
 shipment(s5, p6, j2, 200).
 shipment(s5, p1, j4, 100).
 shipment(s5, p3, j4, 200).
 shipment(s5, p4, j4, 800).
 shipment(s5, p5, j4, 400).
 shipment(s5, p6, j4, 500).

 q5(S,P,N):-
    shipment(S,P,_,_),
    part(P,N,_,_,_).

 q6(P,W,N):-
    shipment(_,_,_,_),
    part(_,P,_,_,W),
    supplier(_,N,_,W).

 demo(X) :-
    nl,
    portray_clause(goal=X),
    forall(X,(print(result=X),nl)).

 :- style_check([-singleton]).
 :- demo(print(hello)).
 :- demo(q5(_,_,_)).
 :- demo(q6(_,_,_)).
 :- halt.

