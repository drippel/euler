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
  
  val denoms = List(100,50,20,10, 5,  2,  1)
  val max    = List(  2, 4,10,20,40,100,200)
  val amounts = List(0,0,0,0,0,0,0)
  
  def main( args : Array[String] ) : Unit = {

    Console.println("031...")
    
    /*
    val l = List(1,1,2,0,1,1,3)
    Console.println( sum( l ) )
    */
    
    // add 1
    for( i <- 0 until denoms.size ){ 
      solve( List(0,0,0,0,0,0,0), i )
    }
    
    Console.println(solutions.size + 1)
  }
  
  var solutions = HashSet[List[Int]]()
  
  def solve( amts : List[Int], slot : Int ) : Unit = {
    
    val s = sum( amts )
    if( s == 200 ){
      // found
      Console.println( amts )
      solutions += amts
    }
    else if( s > 200 ){
      // stop  
    }
    else {
      // add one in the current slot, solve
      solve( addSlot(amts,slot), slot ) 
      
      if( (slot + 1) < amts.size ){
        // add one in the next slot, solve
        solve( addSlot(amts,(slot+1) ), slot + 1 )
      }
      
    }
    
  }
  
  def addSlot( amts : List[Int], slot : Int ) : List[Int] = {
    
    val namts = ListBuffer[Int]()
    namts ++= amts
    namts(slot) = namts(slot) + 1
    namts.toList
    
  }

  def sum( l : List[Int] ) = {
    val z = denoms.zip(l)
    z.foldLeft(0)( (a:Int,t:(Int,Int)) => { a + ( t._1 * t._2 ) } )
  }
  
}