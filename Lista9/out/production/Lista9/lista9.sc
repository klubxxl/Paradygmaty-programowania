// DANIEL KONSEWICZ - LISTA 9

// ZADANIE 1
class Time(private var h: Int):
  if (h < 0) h = 0
  def hour: Int = h
  def hour_=(newH: Int): Unit =
    if (newH < 0)
      h = 0
    else
      h = newH


val test = new Time(-20)
test.hour == 0
test.hour = 10
test.hour == 10
test.hour = -69
test.hour == 0

// ZADANIE 2
// podpunkt A
class Time2a(private var h: Int,private var m: Int):
  require(h >= 0 && h < 24, s"hour=$h")
  require(m >= 0 && m < 60, s"minute-$m")
  def hour: Int = h
  def hour_=(newH: Int): Unit =
    require(0 <= newH && newH < 24, s"new hour=$newH")
    h = newH
  def minute: Int = m
  def minute_=(newM: Int): Unit =
    require(0 <= newM && newM < 60, s"new minute-$newM")
    m = newM
  def before(other: Time2a): Boolean =
    if (hour<other.hour || hour == other.hour && minute < other.minute)
      true
    else
      false

val test2a = new Time2a(10,30)
test2a.hour == 10
test2a.minute == 30
test2a.before(new Time2a(10,40)) == true
test2a.before(new Time2a(9,40)) == false
test2a.minute = 5
test2a.hour = 1
test2a.hour == 1
test2a.minute == 5
// test2a.hour = 26 java.lang.IllegalArgumentException: requirement failed: new hour=26
// test2a.hour

//podpunkt B
class Time2b(private var h: Int,private var m: Int):
  require(h >= 0 && h < 24, s"hour=$h")
  require(m >= 0 && m < 60, s"minute-$m")
  private var minAfterMidnight = h * 60 + m
  def minutesAfterMidnight: Int = minAfterMidnight
  def hour: Int = minAfterMidnight / 60
  def hour_=(newH: Int): Unit =
    require(0 <= newH && newH < 24, s"new hour=$newH")
    minAfterMidnight = minute + newH * 60
  def minute: Int = minAfterMidnight % 60
  def minute_=(newM: Int): Unit =
    require(0 <= newM && newM < 60, s"new minute-$newM")
    minAfterMidnight = hour * 60 + newM
  def before(other: Time2b): Boolean =
    if (minutesAfterMidnight < other.minutesAfterMidnight)
      true
    else
      false

val test2b = new Time2b(10,30)
test2b.hour == 10
test2b.minute == 30
test2b.before(new Time2b(10,40)) == true
test2b.before(new Time2b(9,40)) == false
test2b.minute = 5
test2b.hour = 1
test2b.hour == 1
test2b.minute == 5


//
//// ZADANIE 4
//
//class UzycieWyjatkow {
//  public static void main(String … args) {
//    // public static void main(String[] args) {
//    try {
//      metoda1();
//    } catch ( Exception e ) {
//      System.err.println( e.getMessage() + "\n" );
//      e.printStackTrace();
//    }
//  }
//  public static void metoda1() throws Exception {
//    metoda2();
//  }
//  public static void metoda2() throws Exception {
//    metoda3();
//  }
//  public static void metoda3() throws Exception {
//    throw new Exception( "Wyjatek zgloszony w metoda3" );
//  }
//}
