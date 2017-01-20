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

object Problem050 {

  def main(args: Array[String]): Unit = {

    Console.println("050...")
    
    // build the primes list
    isPrime(1000000)
    
    val ps = for( i <- 2 until 1000000 if( isPrime( i ) ) ) yield { i } 
    var psl = ps.toList 
    
    var allSums = List[List[Int]]()
    
    while( !psl.isEmpty ){ 
      
      allSums = allSums ++ primeSums( psl, 1000000 )
      psl = psl.tail

    }
    
    val psss = allSums.sortBy( _.size )
    
    Console.println( psss.last )
    val sum = psss.last.foldLeft(0)( (a:Int,b:Int) => { a + b } ) 
    Console.println( sum )
    
    
  }
  
  def primeSums( ps : List[Int], max : Int ) : List[List[Int]] = {
    
    def inner( sofar : List[Int], left : List[Int], accum : List[List[Int]] ) : List[List[Int]] = {
      
      left match {
        
        case Nil => {
          Console.println( "done " + sofar.last )
          accum
        }
        case head :: tail => {
          // Console.println(head)
          
          val sum = sofar.foldLeft(0)( (a:Int,b:Int) => { a + b } ) + head
          if( sum >= max ){
            accum
          }
          else {
            val naccum = if( isPrime( sum ) ){
              accum :+ ( sofar :+ head ) 
            }
            else {
              accum
            }
          
            inner( ( sofar :+ head), tail, naccum ) 
          }
        }
        
      }
    }
    
    if( ps.isEmpty ){
      List()
    }
    else {
      inner( List(), ps, List() ) 
    }
    
  }
  
  
}