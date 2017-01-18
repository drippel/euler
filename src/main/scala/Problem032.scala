import scala.collection.mutable.ListBuffer
import Commons._
import Fibonacci._
import Primes._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat
import java.math.RoundingMode
import java.util.regex.Pattern

object Problem032 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("032...")
    
    val prodSet = HashSet[Int]()
    
    val start = "123456789"
    
    val ps = start.permutations
    
    ps.foreach( (s:String) => {
      for( x <- 1 to 9; y <- 1 to 9; z <- 1 to 9 if( (x + y + z) == 9 ) ){
        // Console.println( x +","+ y +","+ z )
        val s1 = s.substring( 0, x )
        val s2 = s.substring( x, x + y )
        val s3 = s.substring( x + y )
        // Console.println( s1 +","+ s2 +","+ s3 )
        if( s1.toInt * s2.toInt == s3.toInt ){
          Console.println( "found..." )
          prodSet += ( s3.toInt )
        }
      }
    })
    
    val sum = prodSet.toList.foldLeft(0)( (a:Int,b:Int) => { a + b })
    
    Console.println( sum )
    
    
  }
}