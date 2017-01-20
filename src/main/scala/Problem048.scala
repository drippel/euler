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

object Problem048 {

  def main(args: Array[String]): Unit = {

    Console.println("048...")
    
    val ns = for( i <- 1 to 1000 ) yield {
      val b = BigInt(i)
      b.pow(i)
    }
    
    val sum = ns.foldLeft(BigInt(0))( (a:BigInt,b:BigInt) => { a + b } )
    val ssum = sum.toString
    
    Console.println( ssum.size ) 
    Console.println( ssum.substring(ssum.size - 10 ) ) 
    
  }
  
}