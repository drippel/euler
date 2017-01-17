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

object Problem031 {
  
  val denoms = List(1,2,5,10,20,50,100)
  
  def main( args : Array[String] ) : Unit = {

    Console.println("031...")
    
    Console.println( solve( 200, denoms ) )
    
  }
  
  var solutions = 0
  
  // count down approach
  def solve( amount : Int, coins : List[Int] ) : Int = {
    
    def inner( iamount : Int, icoins : List[Int], accum : Int ) : Int = {
      if( iamount == 0 ){ accum + 1 }
      else if( iamount < 0 ){ accum }
      else {
        inner( iamount - icoins.head, icoins, accum + solve( iamount, icoins.tail ) )
      }
      
    }
    
    if( amount == 0 ){ 1 }
    else if( amount < 0 ){ 0 }
    else if( coins.isEmpty ){ 0 }
    else { 
      inner( amount, coins, 0 ) 
    }
    
  }
  
  def sum( l : List[Int] ) = {
    val z = denoms.zip(l)
    z.foldLeft(0)( (a:Int,t:(Int,Int)) => { a + ( t._1 * t._2 ) } )
  }
  
}