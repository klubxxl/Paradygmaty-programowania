(* DANIEL KONSEWICZ - ZADANIE 7 PUDPUNKT B *)

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

module QueueB: QUEUE_FUN =
struct

  type 'a t = 'a list * 'a list
                          		
  exception Empty of string

  let empty () = ([], [])

  let enqueue (elem, queue) = 
    match queue with
        ([], l2) -> (List.rev (elem::l2), [])
      | (l1, l2) -> (l1, elem::l2)

  let dequeue queue =
    match queue with
        ([],_) -> ([],[])
      | (_::[],l2) -> (List.rev l2,[])
      | (_::t,l2) -> (t, l2)

  let first queue =
    match queue with
        ([],_) -> raise (Empty "Empty Queue")
      | (h::_, _) -> h

  let isEmpty (l1,_) = 
    l1 = []
end
;;

QueueB.isEmpty((QueueB.enqueue(1,QueueB.empty()))) = false;;
QueueB.isEmpty(QueueB.empty()) = true;;
QueueB.dequeue(QueueB.enqueue(1,QueueB.enqueue(2,QueueB.empty()))) = QueueB.enqueue(1,QueueB.dequeue(QueueB.enqueue(2,QueueB.empty())));;
QueueB.dequeue(QueueB.enqueue(1,QueueB.empty())) = QueueB.empty();;
QueueB.dequeue(QueueB.empty()) = QueueB.empty();;
QueueB.first(QueueB.enqueue(1,QueueB.enqueue(2,QueueB.empty()))) = QueueB.first(QueueB.enqueue(2,QueueB.empty()));;
QueueB.first(QueueB.enqueue(1,QueueB.empty())) = 1;;
QueueB.first(QueueB.empty());; 
