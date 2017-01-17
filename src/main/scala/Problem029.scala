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

object Problem029 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("029...")
    
    val abs = for( a <- 2 to 100; b <- 2 to 100 ) yield { BigInt(a).pow(b) }
    val uniq = abs.toList.toSet
    
    //Console.println( uniq )
    Console.println( uniq.size )
    
  }
}