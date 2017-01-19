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

object Problem036 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("036...")
    
    val n = 585
    
    Console.println( isPalindrome(n.toString) )
    Console.println( isPalindrome(n.toBinaryString ) )
    
    val pals = for( i <- 1 until 1000000; if( isPalindrome(i.toString) && isPalindrome(i.toBinaryString) ) ) yield { i }
    
    Console.println(pals)
    Console.println(pals.foldLeft(0)( (a:Int,b:Int) => { a + b } ))
    
  }
  
  
}