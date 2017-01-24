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
import scala.collection.mutable.BitSet

object Problem055 {
  
  def main(args: Array[String]): Unit = {

    Console.println("055...")
    
    Console.println( makePalindrome(BigInt(349)) )
    Console.println( makePalindrome(BigInt(10677)) )
    
    val ns = for( i <- 1 until 10000 ) yield { i }
    
    val ps = ns.map( makePalindrome( _ ) )
    
    val ls = ps.filter( !_.isDefined )
    
    Console.println( ls.size )
    
  }
  
  def makePalindrome( start : BigInt ) : Option[BigInt] = {
    
    def inner( curr : BigInt, round : Int ) : Option[BigInt] = {
      
      if( round >= 50 ){
        None
      }
      else {
        
        val p = reverseAdd(curr)
        
        if( isPalindrome(p.toString) ) {
          Some(p)
        }
        else {
          inner( p, (round + 1) )
        }
        
      }
      
    }
    
    inner( start, 0 )
    
  }
  
  def reverseAdd( i : BigInt ) : BigInt = {
    
    var r = BigInt(i.toString().reverse)
    
    i + r
  }

}