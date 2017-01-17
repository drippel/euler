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

object Problem030 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("030...")
    Console.println( '0'.toInt )
    Console.println( sumOfFifths( 34 ) )
    
    var sum = BigInt(0)
    
    for( n <- 100000000 until 1 by -1 ){
      
      if( n == sumOfFifths(n) ){
        Console.println( n +" "+ sumOfFifths(n) +" "+ (n == sumOfFifths(n) ) )
        sum = sum + sumOfFifths(n) 
      }
    }
    
    Console.println(sum)
    
  }
  
  def sumOfFifths( n : BigInt ) : BigInt = {
    sumOfFifths( n.toString() )
  }
  
  def sumOfFifths( s : String ) : BigInt = {
    s.foldLeft(BigInt(0))( (a:BigInt,c:Char) => { a + toFifth(c) } )
  }
  
  def toFifth( c : Char ) : BigInt = {
    fifth( c.toInt - 48 ) 
  }
  
  def fifth( i : BigInt ) : BigInt = {
    i.pow(5)
  }
}