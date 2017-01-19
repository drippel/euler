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

object Problem039 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("039...")
    
    val l = for( a <- 1 to 1000; b <- 1 to 1000; c <- 1 to 1000 if( (a*a) + (b*b) == (c*c) ) if( a + b + c <= 1000 ) ) yield { (a,b,c,(a+b+c)) }
    
    val abcs = l.toList 
    
    val absMap = l.groupBy( _._4 )
    
    // absMap.foreach( Console.println( _ ))
    
    val sizes = absMap.map( (t:(Int,Seq[(Int,Int,Int,Int)])) => { (t._1, t._2.size) } )
    
    val top = sizes.toList.sortBy( _._2 ).reverse.head
    
    Console.println(absMap(120))
    Console.println(top)
    
  }
  
}