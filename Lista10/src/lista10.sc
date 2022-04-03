// DANIEL KONSEWICZ

// ZADANIE 4

import scala.collection.mutable

def copy[T](dest: mutable.Seq[T], src: mutable.Seq[T]): Unit =
  require(dest.length >= src.length)
  var i = 0
  src.foreach(elem=> {
    dest.update(i, elem)
    i += 1
  })

val src = mutable.Seq(0,1,2,3,4,5,6,7,8,9)
val dest = mutable.Seq(0,0,0,0,0,0,0,0,0,0,0)
copy(dest,src)
dest.apply(0) == 0
dest.apply(1) == 1
dest.apply(2) == 2
dest.apply(3) == 3
dest.apply(4) == 4
dest.apply(5) == 5
dest.apply(6) == 6
dest.apply(7) == 7
dest.apply(8) == 8
dest.apply(9) == 9