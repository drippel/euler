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
import org.apache.commons.lang3.StringUtils
import scala.collection.mutable.BitSet

object Problem053 {
  
  def main(args: Array[String]): Unit = {

    Console.println("053...")
    
    val start = "12345"
    
    val cs = start.combinations(3)
   
    Console.println( f( 23 ,10 ) )
    
    var count = 0
    
    for( n <- 1 to 100 ) {
      for( r <- 1 to 100 ){
        if( f( n, r ) > 1000000 ){
          count = count + 1
        }
      }
    }
    
    Console.println( count )
  }
  
  def f( n : BigInt, r : BigInt ) : BigInt = {
    factorial(n) / ( factorial(r) * factorial( n - r ) )
  }
  
  
}