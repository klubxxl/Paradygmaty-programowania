// DANIEL KONSEWICZ

// ZADANIE 3

def sumProd(xs: List[Int]) = (xs foldLeft(0,1)) ((sp,h) => (h+sp._1, h*sp._2))

sumProd(List(1,2,3,4,5))