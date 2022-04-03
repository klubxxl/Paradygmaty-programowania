(* DANIEL KONSEWICZ *)

(* ZADANIE 2 *)

let curry3 f x y z = f (x, y, z);; (* Rozwijanie funkcji *)
let uncurry3 f (x, y, z) = f x y z;; (* Zwijanie funkcji *)


let curry3_bezlukru = function f -> function x -> function y -> function z -> f (x,y,z) ;;

let uncurry3_bezlukru = function f -> function (x,y,z) -> f x y z;;

let plus1 (x,y,z) = x+y+z;;
let plus2 x y z = x+y+z;;

curry3 plus1 1 2 3;;
curry3_bezlukru plus1 1 2 3;;

uncurry3 plus2(1,2,3);;
uncurry3_bezlukru plus2(1,2,3);;

(* ZADANIE 3 *)

let sumProd l = List.fold_left (fun (s, p) h -> (s + h, p * h)) (0, 1) l;;

sumProd([1;2;3;4;5]);;
