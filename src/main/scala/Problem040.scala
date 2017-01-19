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

object Problem040 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("040...")
    
    val src = buildString(1000000)
    
    // Console.println( src )
    
    val t = src(11)
    Console.println( t )
    
    val c1 = src(0)
    val c2 = src(9)
    val c3 = src(99)
    val c4 = src(999)
    val c5 = src(9999)
    val c6 = src(99999)
    val c7 = src(999999)
    Console.println( c1 +"," + c2 +"," + c3 +","+ c4 +","+ c5+","+ c6+","+ c7 )
    Console.println( toInt(c1) * toInt(c2) * toInt(c3) * toInt(c4) * toInt(c5) * toInt(c6) * toInt(c7) )
    
  }
  
  def toInt( c : Char ) : Int = {
    ( c.toInt - 48 )
  }
  
  def buildString( len : Int ) : String = {
    var ret = ""
    
    var n = 1
    
    while( ret.size < len + 10 ){
      ret = ret + n.toString()
      n = n + 1
    }
    
    ret
  }
  
}