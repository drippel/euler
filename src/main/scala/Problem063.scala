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

object Problem063 {

  def main( args: Array[String] ): Unit = {

    Console.println( "063..." )
    
    solve()
    
  }
  
  def solve() = {
    
    def inner( base : Int, pow : Int, accum : List[(Int,Int)] ) : List[(Int,Int)] = {
      
      val s = (BigInt(base)).pow(pow)
      if( s.toString.size > pow ){
        accum
      }
      else if( pow > s.toString().size ) {
        inner( base + 1, 1, accum )
      }
      else {
        // add to accum, inc
        inner( base, pow + 1, ( accum :+ (base,pow) ) )
      }
    }

    val ret = inner( 1, 1, List() )
    Console.println( ret )
    Console.println( ret.size )
  }
  
}
