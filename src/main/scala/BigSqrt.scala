import java.math.MathContext
import scala.collection.mutable.HashMap


object BigSqrt {
  
  def main( args : Array[String] ) : Unit = {
    Console.println(sqrt( BigDecimal(4) ) )
    Console.println(sqrt( BigDecimal(4) ).toInt )
    Console.println(sqrt( BigDecimal(4) ).isValidInt )
    Console.println(sqrt( BigDecimal(4) ).isWhole() )
    Console.println(sqrt( BigDecimal(4) ).setScale(20,BigDecimal.RoundingMode.HALF_DOWN).isValidInt )
    Console.println(sqrt( BigDecimal(4) ).round(MathContext.DECIMAL64).isValidInt )
    Console.println(sqrt( BigDecimal(4) ).round(MathContext.DECIMAL64).isWhole )
    Console.println(sqrt( BigDecimal(5) ) )
  }
  
  val sqrtMap = HashMap[BigDecimal,BigDecimal]()

 def isGoodEnough(guess: BigDecimal, x: BigDecimal) = {
    val d = guess.pow(2) - x 
    (d.abs / x) < 0.000000000000000000001
 }

  def improve(guess: BigDecimal, x: BigDecimal): BigDecimal = {
    (guess + x / guess) / 2
  }

  def sqrtIter(guess: BigDecimal, x: BigDecimal): BigDecimal = {
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)
  }

  def sqrt(x: BigDecimal): BigDecimal = {
    sqrtMap.get(x) match {
      case Some(s) => { 
        s
      }
      case None => {
        val g = if( x >= 100 ){ x / 10 } 
        else { BigDecimal( "1.0" ) }

        val s = sqrtIter( g, x)
        sqrtMap += ( x -> s )
        s
      }
    }
  } 
}