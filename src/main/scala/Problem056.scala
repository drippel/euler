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

object Problem056 {
  
  def main(args: Array[String]): Unit = {

    Console.println("056...")
    
    val ps = for( a <- 1 until 100; b <- 1 until 100 ) yield { (a,b) }
    
    val pows = ps.map( exp( _ ) )
    
    val sums = pows.map( sumDigits(_) )
    
    val ss = sums.toList.sorted
    
    Console.println( ss.last )
    
    
  }
  
  def exp( t : (Int,Int) ) : BigInt = {
    val a = BigInt(t._1)
    a.pow(t._2)
  }
  
}