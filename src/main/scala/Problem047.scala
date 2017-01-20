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

object Problem047 {

  def main(args: Array[String]): Unit = {

    Console.println("047...")
    
    /*
    Console.println(factor(644))
    Console.println(factor(645))
    Console.println(factor(646))

    val gs = factor(644).groupBy( (a:BigInt) => {a} )
    Console.println(gs)
    */
    
    solve( 4 )
  }
  
  def solve( size : Int ) : Int = {
    
    var found = false
    var sol = 0
    
    while( !found ){
      
      if( sol % 1000 == 0 ){
        Console.println( sol )
      }
      
      val ns = for( i <- sol until sol + size ) yield { i } 
      val fs = ns.toList.map( groupedFactors( _ ) )
      
      if( fs.forall( _.size == size )){
        
        if( distinct( size, fs ) ){
        
          Console.println( "found" )
          Console.println( ns )
          Console.println( fs )
          found = true
        }
        else {
          sol = sol + 1
        }
      }
      else {
        sol = sol + 1
      }
      
    }
    
    sol
    
  }
  
  def groupedFactors( n : BigInt ) : List[BigInt] = {
    val gs = factor(n).groupBy( (a:BigInt) => {a} )
    val gss = gs.map( ( t:(BigInt,List[BigInt] ) ) => { t._1 * t._2.size } )
    gss.toList
  }
  
  def distinct( sz : Int, ls : List[List[BigInt]] ) : Boolean = {
    
    val fs = ls.flatten.toSet
    fs.size == ( sz * sz )
    
  }

}