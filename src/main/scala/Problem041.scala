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

object Problem041 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("041...")
    
    val start = "1234567"
    
    val ps = start.permutations.toList
    Console.println( "perms:"+ ps.size )
    
    val is = ps.par.map( _.toInt )
    Console.println( "is:"+ is.size )
    
    val odds = is.par.filter( (n:Int) => {  
      val c = n.toString.last
      ((c - 48) % 2 != 0) && c != '5'
    })
    Console.println( "odds:"+ odds.size )

    val primes = odds.toList.sorted.reverse.find( (n:Int) => {  
      //Console.println( "t:"+ n )
      isPrime2( n ) 
    })
    Console.println(primes)
    
    
  }
  
}