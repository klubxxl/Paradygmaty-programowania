(* DANIEL KONSEWICZ - LISTA 5 *)

type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;
let rec lfrom k = LCons (k, function () -> lfrom (k+1));;
let rec ltake (n, lxs) =
  match (n, lxs) with
      (0, _) -> []
    | (_, LNil) -> []
    | (n, LCons(x,xf)) -> x::ltake(n-1, xf());;

(* ZADANIE 1 *)

let lrepeat k llist = 
  let rec helper(reps, rest) = 
    match (reps, rest) with
        (_, LNil) -> LNil
      | (0, LCons(_, tailFunction)) -> helper(k, (tailFunction()))
      | (_, LCons(head, _)) -> LCons(head, function() -> helper(reps - 1, rest))
  in helper(k, llist);;


ltake(15,(lrepeat 3(lfrom 1)))

(* ZADANIE 2 *)

let lfib =
  let rec fib(a,b) =
    LCons(a+b, function () -> fib(b, a+b))
  in LCons(0, function () -> LCons(1, function () -> fib(0,1)));;

ltake(10, lfib);;
ltake(1, lfib);;

(* ZADANIE 3 *)

type 'a lBT = LEmpty | LNode of 'a * (unit ->'a lBT) * (unit -> 'a lBT);;

(* a *)
let lBreadth ltree =
  let rec lBreadthIn treeQueue =
    match treeQueue with
      | [] -> LNil
      | LEmpty::t -> lBreadthIn t
      | LNode(v, left, right)::t -> LCons(v, (function () -> lBreadthIn (t @ left() :: right() :: []))) 
  in lBreadthIn [ltree];;
(* b *)
let rec lTree n =
  LNode(n, (function () -> lTree(2*n)), (function () -> lTree(2*n+1)));;

ltake(20, lBreadth (lTree 1)) = [1; 2; 3; 4; 5; 6; 7; 8; 9; 10; 11; 12; 13; 14; 15; 16; 17; 18; 19; 20];;
