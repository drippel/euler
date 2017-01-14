import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source

object Problem015a {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("015a...")
    
    val SZ = 21
    
    val grid = initGrid(SZ)
    print(grid,SZ)
    solve(grid,SZ)
    print(grid,SZ)
    
    Console.println( "solution:"+ grid(SZ-1)(SZ-1) )

  }
  
  def solve( grid : Array[Array[BigInt]], n : Int ) = {
    
    for( y <- 1 until n ){
      for( x <- 1 until n ){
        grid(x)(y) = grid(x-1)(y) + grid(x)(y-1)
      }
    }
    
  }
  
  def print( grid : Array[Array[BigInt]], n : Int ) = {
    for( y <- 0 until n ) {
      for( x <- 0 until n ){
        Console.print( grid(x)(y) )
      }
     Console.print( '\n'  )
    }
  }
  
  def initGrid( n : Int ) : Array[Array[BigInt]] = {
    
    val grid = Array.ofDim[BigInt](n, n)
    
    // the 0 x is filled with 1
    
    for( i <- 0 until n ){
      grid(0)(i) = 1
      grid(i)(0) = 1
    }
    
    grid
    
  }
  
  
  
}