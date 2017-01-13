import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source

object Problem014 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("014...")
    
    Console.println( collatz( BigInt(500000) ) )
    
    val nums = for( i <- 1 until 1000000 ) yield i
    
    var max = 0 
    var num = 0 
    
    for( n <- nums ){
      val c = collatz( n )
      if( c.size > max ){
        max = c.size
        num = n
        Console.println( num +" : " + max )
      }
    }
    
    Console.println( num +" " + max ) 
    
  }
  
}