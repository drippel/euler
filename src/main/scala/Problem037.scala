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

object Problem037 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("037...")
    
    // init the primes
    isPrime(10000000)
    
    var sum = 0
    
    for( i <- 9 until 10000000 if( isPrime(i) ) ) { 
      val ts = trunc( i.toString )
      if( ts.forall( (s:String) => { isPrime( s.toInt ) } ) ){
        sum = sum + i
        Console.println( "n:"+ i +" "+ ts )
      }
    }
    
    Console.println( sum )
    
  }
  
  def trunc( start : String ) : List[String] = {
    
    val ts = HashSet[String]()
    
    var work = start
    
    while( !work.isEmpty() ){
      ts += work
      work = work.tail
    }
    
    work = start
    
    while( !work.isEmpty() ) {
      
      ts += work
      work = work.reverse.tail.reverse 
      
    }
    
    ts.toList
    
  }
  
  
}