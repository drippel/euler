import scala.collection.mutable.ListBuffer
import Commons._
import scala.collection.mutable.HashMap

object Problem006 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("006...")
    
    val squares = for( i <- 1 to 100 ) yield ( BigInt(i * i) )
    val sum1 = squares.foldLeft(BigInt(0))( (a:BigInt,b:BigInt) => { a + b } )
    
    Console.println(sum1)
    
    val nums = for( i <- 1 to 100 ) yield ( BigInt(i) )
    val sum2 = nums.foldLeft(BigInt(0))( (a:BigInt,b:BigInt) => { a + b } )
    Console.println(sum2 * sum2)
    
    Console.println( (sum2 * sum2) - sum1 )
  }
  
}