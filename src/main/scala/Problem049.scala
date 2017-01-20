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

object Problem049 {

  def main(args: Array[String]): Unit = {

    Console.println("049...")
    
    /*
    val start = "1234567890"
    val combs = combinations(start,4).sorted
    combs.foreach( Console.println( _ ) )
    */
    isPrime(10001)
    
    val nums = for( i <- 1000 to 9999 ) yield { i }
    val ps = nums.toList.filter( isPrime(_) )
    val pss = ps.map( _.toString )
    
    val ms = pss.map( (s:String) => { findMatches(s, pss ).sorted } )
    val mss = ms.filter( _.size >= 3 ).toSet
    // mss.foreach( Console.println( _ ) )
    
    val l = List(1487, 1847, 4817, 4871, 7481, 7841, 8147, 8741)
    Console.println( findEquidistant(l) )
    
    for( m <- mss ){
      val is = m.map( _.toInt )
      Console.println( findEquidistant(is) )
    }
    
   
  }
  
  def findMatches( src : String, ss : List[String] ) : List[String] = {
    for( s <- ss; if( src.diff(s).isEmpty() ) ) yield { s }
  }
  
  def findEquidistant( is : List[Int] ) : List[List[Int]] = {
    
    val diffs = for( i <- 0 until is.size - 1; j <- i + 1 until is.size ) yield {
      val i1 = is(i)
      val i2 = is(j)
      val d1 = i2.toInt - i1.toInt
      
      val i3 = i2.toInt + d1
      if( is.contains(i3) ){
        Some( i1, i2, i3 )
      }
      else {
        None
      }
    }
    
    val f = diffs.toList.flatten
    
    f.map( (t:(Int,Int,Int)) => { List( t._1, t._2, t._3 ) } )

  }
  
}