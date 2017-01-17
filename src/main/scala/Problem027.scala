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

object Problem027 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("027...")
    
    val abs = for( a <- -999 to 999; b <- -1000 to 1000 ) yield (a,b)
    
    val abns = abs.map( (ab:(Int,Int)) => { (ab._1,ab._2,findPrimes(ab).size) } )
    
    val sorted = abns.sortBy( _._3 ).reverse
    
    Console.println( sorted.head )
    Console.println( sorted.head._1 * sorted.head._2 )
    
  }
  
  def findPrimes( ab : (Int,Int) ) : List[Int] = {
    
    def inner( ab : (Int,Int), n : Int, accum : List[Int] ) : List[Int] = {
      
      val q = quad( ab._1, ab._2, n )
      
      if( isPrime(q) ){
        inner( ab, ( n + 1 ), ( accum :+ q ) )
      }
      else {
        accum
      }
    }
    
    inner( ab, 0, List() )

  }
  
  def quad(a : Int, b : Int, n : Int) : Int = {
    (n * n) + ( a * n ) + b
  }
    
}