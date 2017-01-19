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

object Problem035 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("035...")
    
    isPrime( 2000000 )
    
    // Console.println( isPrime(719) )
    
    val primes = HashSet[Int]()
    
    for( i <- 2 until 1000000 if( isPrime(i) ) ) { 
      
      Console.println(i)
      val s = i.toString
      val ps = rotate(s) 
      
      if( ps.forall( (p:String) => {
        isPrime(p.toInt)
      } ) ){
        Console.println( "found:"+ i )
        primes += i
      }
    }
    
    Console.println(primes)
    Console.println(primes.size)
    
  }
  
  def rotate( src : String ) : List[String] = {
    
    val ret = ListBuffer[String]() 
    var work = src
    
    for( i <- 0 until src.size ){
      val next = work.tail :+ work.head
      ret += next
      work = next
    }
    
    
    ret.toList
  }
  
}