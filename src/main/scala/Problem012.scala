import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source

object Problem012 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("012...")
    solve()
  }
  
  def solve() = {

    var found = false 
    var n = 1
    
    while( !found ) {
      
      val tn = triangleNumber(n)
      val divs = divisors(tn)
      Console.println( "n:"+ tn +" d: "+ divs.size )
      // Console.println( divs )
      
      n = n + 1
      
      if( divs.size > 500 ){
        found = true
      }
      
    }

  }
  
}