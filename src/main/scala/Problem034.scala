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

object Problem034 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("034...")
    
    for( i <- 3 until 1000000000 ){
      
      val s = i.toString()
      
      val sum = s.foldLeft(BigInt(0))( (a:BigInt,c:Char) => { a + toFact(c) } )
      
      if( i == sum ){
        Console.println( i +", "+ sum )
      }
    }
    
  }
  
  def toFact( c : Char ) : BigInt = {
    factorial(c.toInt - 48)
  }
  
}