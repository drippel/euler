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

object Problem038 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("038...")
    
    val found = ListBuffer[Int]()
    
    for( i <- 1 until 1000000; n <- 1 to 9 ) { 
      val ps = allProds( i, n )
      val c = concat(ps)
      if( isPandigital(c) ){
        found += c.toInt
        Console.println( i +", "+ n )
        Console.println( c )
      }
    }
    
    Console.println(found.size)
    Console.println(found.sorted.reverse.head)
  }
  
  def concat( is : List[Int] ) : String = {
    var s = ""
    
    for( i <- is ){
      s = s + i.toString()
    }
    
    s
  }
  
  def allProds( i : Int, n : Int ) : List[Int] = {
    val ps = ListBuffer[Int]()
    
    for( y <- 1 to n ){
      ps += i * y
    }
    
    ps.toList
  }
  
  val digits = "123456789"
  def isPandigital( s : String ) : Boolean = {
    s.length() == 9 && digits.forall( (c:Char) => { s.indexOf(c) > -1 } )
  }
    
  
}