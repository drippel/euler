import scala.collection.mutable.ListBuffer
import Commons._
import Fibonacci._
import Primes._
import Polygonals._
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
import java.io.FileWriter
import scala.collection.mutable.PriorityQueue
import scala.collection.immutable.Stack

object Problem064 {

  def main( args: Array[String] ): Unit = {

    Console.println( "064..." )
    
    solve()
    
    
  }
  
  def solve() = {
    
    var count = 0
    
    for( n <- 2 to 10000 ){
      
      val e = expansion( n )
      
      if( e.size > 0 && e.size % 2 != 0 ){
        // odd
        count = count + 1
      }
      
    }
    
    Console.println( count )
    
  }
  
  def expansion( S : Int ) : List[(Int,Int,Int)] = {
    
    val A = scala.math.sqrt(S).toInt 
    
    def inner( t : (Int,Int,Int), accum : List[(Int,Int,Int)] ) : List[(Int,Int,Int)] ={
    
      val m0 = t._1
      val d0 = t._2
      val a0 = t._3
    
      val m1 = ( d0 * a0 ) - m0
      val d1 = ( S - ( m1 * m1 ) ) / d0
      
      if( d1 == 0 ){
        accum
      }
      else {
        val a1 = ( ( A + m1 ) / d1 )
      
        // build a new tuple
        val t1 = (m1,d1,a1)
      
        // is this tuple in the list?
        if( accum.contains(t1) ){
          accum
        }
        else {
          inner( t1, ( accum :+ t1 ) )
        }
      }
      
    }
    
    inner( (0,1,A), List() )
    
  }
  
}
