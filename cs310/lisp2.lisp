(define area (lambda (r) 
    (* 3.141592653 (* r r))))

(say (area 3))

(define fact (lambda (n) 
    (if (<= n 1) 
        1 
            (* n (fact (- n 1))))))
(say (fact 10))
(say (fact 100))
(say (area (fact 10)))
(define first car)
(define rest cdr)
(define count (lambda (item L) 
     (if L 
         (+ (equal? item (first L)) 
             (count item (rest L))) 
         0)))

(say (count 0 
    (list 0 1 2 3 0 0)))
