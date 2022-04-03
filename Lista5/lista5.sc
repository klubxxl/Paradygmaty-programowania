// DANIEL KONSEWICZ - LISTA 5

// ZADANIE 1

def lrepeat[A](k: Int)(lxs: LazyList[A]): LazyList[A] =
  def inrepeat[A](i: Int)(lys: LazyList[A]): LazyList[A] =
    if (lys.isEmpty) LazyList.empty
    else if (i==0) inrepeat(k)(lys.tail)
    else lys.head #:: inrepeat(i-1)(lys)
  inrepeat(k)(lxs)

lrepeat(3)(LazyList('a','b','c','d')).toList == List('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'd', 'd', 'd')
lrepeat(3)(LazyList.from(1)).take(15).toList == List(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5)
lrepeat(3)(LazyList()).take(15).toList == List()

// ZADANIE 2

val lfib : LazyList[Int] =
  def fib(a: Int, b: Int): LazyList[Int] =
    (a+b) #:: fib(b, a+b)
  0 #:: 1 #:: fib(0,1)

lfib.take(10).toList == List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)

// ZADANIE 3

sealed trait lBT[+A]
case object LEmpty extends lBT[Nothing]
case class LNode[+A](elem: A, left: () => lBT[A], right: () => lBT[A]) extends lBT[A]

// a)
def lBreadth[A](ltree: lBT[A]): LazyList[A] = {
  def BreadthIn[A](treeQueue: List[lBT[A]]): LazyList[A] = {
    treeQueue match {
      case Nil => LazyList.empty
      case LEmpty :: t => BreadthIn(t)
      case LNode(value, left, right):: tail => value #:: BreadthIn(tail ++ List(left(), right()))
    }
  }
  BreadthIn(List(ltree))
}
// b)
def lTree(n: Int): lBT[Int] =
  LNode(n, () => lTree(2*n), () => lTree(2*n+1))

lBreadth(lTree(1)).take(20).toList == List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
lBreadth(LEmpty).take(20).toList == List()