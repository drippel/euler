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

object Problem044 {
  
  def main( args : Array[String] ) : Unit = {
    
    Console.println("044...")
    val pents = HashSet[BigInt]()
    for( j <- 1 to 100000 ){
      pents += pent(j)
    }
    
    Console.println("solve...")
    var lo = BigInt(Int.MaxValue)
    
    for( j <- 1 to 100000 ){
      for( k <- 1 to 100000 ){
        val s = pent(j) + pent(k)
        val d = pent(j) - pent(k)
        if( pents.contains(d.abs) && pents.contains(s) ){
          val df = pent(j) - pent(k)
          Console.println("found: "+ j +", "+ k + ", "+ df.abs )
          if( df.abs < lo ){
            lo = df
          }
        }
      }
    }
    
    Console.println(lo)
    
  }
  
  def pent( n : BigInt ) : BigInt = {
    ( n * ( ( 3 * n ) - 1 ) ) / 2
  }
  
}