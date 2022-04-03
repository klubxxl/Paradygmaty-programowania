// Daniel Kosnewicz
import scala.concurrent.{Await, Future}
import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
object Zad2a {
  def pairFut[A, B](fut1: Future[A], fut2: Future[B]): Future[(A, B)] =
    fut1 zip fut2

  def main(args: Array[String]): Unit =
    val test1 = pairFut(Future{1}, Future{2})
    val test2 = pairFut(Future{}, Future{})

    println(Await.ready(test1, Duration.Inf))
    println(Await.ready(test2, Duration.Inf))
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
object Zad2b{
  def pairFut[A,B](fut1: Future[A], fut2: Future[B]): Future[(A, B)] =
    for
      x <- fut1
      y <- fut2
    yield (x, y)

  def main(args: Array[String]): Unit =
    val test1 = pairFut(Future{1}, Future{2})
    val test2 = pairFut(Future{}, Future{})

    println(Await.ready(test1, Duration.Inf))
    println(Await.ready(test2, Duration.Inf))
}
