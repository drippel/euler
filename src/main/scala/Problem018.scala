import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source

object Problem018 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("018...")
    
    val lines = readLines( "./src/main/resources/018.dat" )
    val data = lines.map( convert( _ ) ).toArray
    
    solve(data)
    
  }
  
  def convert( line : String ) : Array[Int] = {
    val ps = line.split(" ")
    ps.map( _.toInt )
  }
  
  def solve( grid : Array[Array[Int]] ) : Int = {
    
    // a roll up solution start at next to last row
    for( r <- grid.size - 2 to 0 by -1 ){
      
      for( x <- 0 until grid(r).size ){
        // consider r + 1, x and r + 1 x + 1
        val mx = scala.math.max( grid( r + 1 )(x), grid( r + 1)(x + 1) )
        grid(r)(x) = grid(r)(x) + mx
        Console.print( grid(r)(x) +" " )
      }
      Console.print( '\n' )
    }
    0
    
  }

}