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

object Problem043 {
  
  def main( args : Array[String] ) : Unit = {
    
    val trips = List( (1, 4, 2), (2, 5, 3), (3, 6, 5), (4, 7, 7), (5, 8, 11), (6, 9, 13), (7, 10, 17))

    Console.println("043...")
    
    // val start = "1406357289"
    val start = "0123456789"
    val perms = start.permutations.filter( (s:String) => { 
      trips.forall( ( t : (Int,Int,Int)) => { divs( s, t._1, t._2, t._3 ) })
    })
    
    val ps = perms.toList
    ps.foreach( Console.println( _ ) )
    
    Console.println( ps.foldLeft(BigInt(0))( (a:BigInt,b:String) => { a + BigInt(b) } ) )
    
  }
  
  def divs( s : String, b : Int, e : Int, d : Int ) : Boolean = {
      val sb = s.substring(b, e)
      sb.toInt % d == 0
  }
  
}