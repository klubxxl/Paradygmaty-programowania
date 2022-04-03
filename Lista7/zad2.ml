(* DANIEL KONSEWICZ - ZADANIE 2*)

module type QUEUE_MUT =
sig
  type 'a t
  (* The type of queues containing elements of type ['a]. *)
  exception Empty of string
  (* Raised when [first q] is applied to an empty queue [q]. *)
  exception Full of string
  (* Raised when [enqueue(x,q)] is applied to a full queue [q]. *)
  val empty: int -> 'a t
  (* [empty n] returns a new queue of length [n], initially empty. *)
  val enqueue: 'a * 'a t -> unit
  (* [enqueue (x,q)] adds the element [x] at the end of a queue [q]. *)
  val dequeue: 'a t -> unit
  (* [dequeue q] removes the first element in queue [q] *)
  val first: 'a t -> 'a
  (* [first q] returns the first element in queue [q] without removing
     it from the queue, or raises [Empty] if the queue is empty. *)
  val isEmpty: 'a t -> bool
  (* [isEmpty q] returns [true] if queue [q] is empty,
     otherwise returns [false]. *)
  val isFull: 'a t -> bool
  (* [isFull q] returns [true] if queue [q] is full,
     otherwise returns [false]. *)
end;;

module Queue : QUEUE_MUT =
struct

  type 'a t = { mutable a : 'a option array ; mutable f: int; mutable r: int }

  exception Empty of string
  exception Full of string
      		
  let empty size =
    	{ a = Array.make (size + 1) None; f = 0; r = 0 }
    			
  let isEmpty queue =
    	queue.r = queue.f
                		
  let isFull queue =
    	((queue.r + 1) mod Array.length queue.a) = queue.f
                                                 			
  let enqueue (element, queue) =
    	if (isFull queue) then raise (Full "Full queue")
    	else queue.a.(queue.r) <- Some element;
    	queue.r <- queue.r + 1

  let dequeue queue =
    	if (isEmpty queue) then ()
    	else queue.f <- ((queue.f + 1) mod Array.length queue.a)

  let first q =
    	if (isEmpty q) then raise (Empty "Empty queue")
    	else match (q.a.(q.f)) with
      	| Some value -> value
      	| None ->  failwith "first element is None"
end;;


(* *** *)

let q = Queue.empty 3;;
Queue.isEmpty q = true;;
Queue.isFull q = false;;
(* Queue.first q;;  EMPTY ERROR *)
Queue.enqueue (1,q);;
Queue.isEmpty q = false;;
Queue.isFull q = false;;
Queue.first q = 1;;
Queue.enqueue (2,q);
Queue.enqueue (3,q);
Queue.isEmpty q = false;;
Queue.isFull q = true;;
(* Queue.enqueue (4,q);; FULL ERROR *)
Queue.first q = 1;;
Queue.dequeue q;;
Queue.first q = 2;;
Queue.isFull q = false;;
Queue.dequeue q;;
Queue.first q = 3;;
Queue.dequeue q;;
Queue.isEmpty q = true;;


