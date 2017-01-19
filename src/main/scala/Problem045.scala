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

object Problem045 {
  
  def main( args : Array[String] ) : Unit = {
    
    Console.println("045...")
    
    val tris  = HashSet[BigInt]()
    val pents = HashSet[BigInt]()
    val hexes   = HashSet[BigInt]()

    for( i <- 1 to 100000 ) {
      tris += tri(i)
      pents += pent(i)
      hexes += hex(i)
    }
    
    val i1 = tris.intersect(pents)
    val i2 = i1.intersect(hexes)
    
    Console.println(i2)
    
  }
  
  def tri( n : BigInt ) : BigInt = {
    ( n * ( n + 1 ) ) / 2
  }

  def pent( n : BigInt ) : BigInt = {
    ( n * ( ( 3 * n ) - 1 ) ) / 2
  }
  
  def hex( n : BigInt ) : BigInt = {
    n * ( ( 2 * n ) - 1 )  
  }
}