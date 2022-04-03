(* DANIEL KONSEWICZ - ZADANIE 7 PUDPUNKT A *)

module type QUEUE_FUN =
sig
  type 'a t
  exception Empty of string
  val empty: unit -> 'a t
  val enqueue: 'a * 'a t -> 'a t
  val dequeue: 'a t -> 'a t
  val first: 'a t -> 'a
  val isEmpty: 'a t -> bool
end;;

module QueueA: QUEUE_FUN =
struct

  type 'a t = 'a list

  exception Empty of string

  let empty () = []

  let enqueue (elem, queue) = queue @ [elem]

  let dequeue queue =
    match queue with
        [] -> []
      | _::t -> t

  let first queue =
    match queue with
        [] -> raise (Empty "Empty Queue")
      | h::_ -> h

  let isEmpty queue = 
    queue = []
end
;;

QueueA.isEmpty((QueueA.enqueue(1,QueueA.empty()))) = false;;
QueueA.isEmpty(QueueA.empty()) = true;;
QueueA.dequeue(QueueA.enqueue(1,QueueA.enqueue(2,QueueA.empty()))) = QueueA.enqueue(1,QueueA.dequeue(QueueA.enqueue(2,QueueA.empty())));;
QueueA.dequeue(QueueA.enqueue(1,QueueA.empty())) = QueueA.empty();;
QueueA.dequeue(QueueA.empty()) = QueueA.empty();;
QueueA.first(QueueA.enqueue(1,QueueA.enqueue(2,QueueA.empty()))) = QueueA.first(QueueA.enqueue(2,QueueA.empty()));;
QueueA.first(QueueA.enqueue(1,QueueA.empty())) = 1;;
QueueA.first(QueueA.empty());; 
