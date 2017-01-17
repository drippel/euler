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

object Problem028 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("028...")
    
    val sz = 1001
    
    val grid = buildGrid(sz)
    
    Console.println( (5 / 2) + 1 )
    
    // print(sz,grid)
    
    fillGrid( sz, grid )

    // print(sz,grid)
    
    Console.println( calcDiags(sz,grid) )
    
  }
  
  def calcDiags( n : Int, g : Array[Array[Int]] ) : Int = {
    
    def inner( cx : Int, cy : Int, dx : Int, dy : Int, accum : Int ) : Int = {
      
      if( !isValid( n, cx, cy ) ){
        accum
      }
      else {
        inner( cx + dx, cy + dy, dx, dy, accum + g(cx)(cy) )
      }
    }
    
    inner( 0, 0, 1, 1, 0 ) + inner( (n -1), 0, -1, 1, 0 ) - 1
  }
  
  class Move( val x : Int, val y : Int )
  case class Left() extends Move( -1, 0 )
  case class Down() extends Move( 0, 1 )
  case class Right() extends Move( 1, 0 )
  case class Up() extends Move( 0, -1 )
  
  def nextDir( m : Move ) : Move = {
    m match {
      case Left() => { Down() }
      case Down() => { Right() }
      case Right() => { Up() }
      case Up() => { Left() }
    }
  }
  
  def fillGrid( n : Int, g : Array[Array[Int]] ) = {
    
    // start at the top right - 1001,0
    var currX = n 
    var currY = 0
    var currDir : Move = Left()
    
    for( i <- ( n * n ) until 0 by -1 ){
      // move 
      var nextX = currX + currDir.x
      var nextY = currY + currDir.y
      
      if( !isValid( n, nextX, nextY ) || !isEmpty( g, nextX, nextY ) ){
        currDir = nextDir( currDir )
        currX = currX + currDir.x
        currY = currY + currDir.y
      }
      else {
        currX = nextX
        currY = nextY
      }
      
      // fill
      g(currX)(currY) = i
    }
     
    
  }
  
  def isValid( n : Int, x : Int, y : Int ) : Boolean = {
    x >= 0 && x < n && y >= 0 && y < n 
  }

  def isEmpty( g : Array[Array[Int]], x : Int, y : Int ) : Boolean = {
    g(x)(y) == 0
  }
  
  def print(n : Int, g : Array[Array[Int]] ) = {
    
    for( y <- 0 until n ){
      for( x <- 0 until n ){
        Console.print(g(x)(y) +"  " )
      }
      Console.print( '\n' )
    }
    
  }
  
  def buildGrid( n : Int ) : Array[Array[Int]] = {
    Array.fill[Int](n, n)(0)
  }
  
}