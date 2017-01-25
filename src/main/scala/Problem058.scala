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
import scala.collection.mutable.BitSet

object Problem058 {
  
  def main(args: Array[String]): Unit = {

    Console.println("058...")
    
    Console.println( solve(20001) )
    
    
  }
  
  def solve( sz : Int ) : Int = {

    val grid = buildGrid(sz)
    
    fillGrid( sz, grid )
    // print(sz,grid)

    val diags = collectDiags(sz,grid).toSet
    val parts = diags.partition( isPrime( _ ) )
   
    val b1 = BigDecimal( diags.size )
    val b2 = BigDecimal( parts._1.size )
    
    val ratio = b2 / b1 
    Console.println( sz +" "+ ratio)
    sz
    
    /*
    if( ratio < 0.1 ){
      sz
    }
    else {
      solve(sz+2)
    }
    * 
    */

  
  }
  
  def collectDiags( n : Int, g : Array[Array[Int]] ) : List[Int] = {
    
    def inner( cx : Int, cy : Int, dx : Int, dy : Int, accum : List[Int] ) : List[Int] = {
      
      if( !isValid( n, cx, cy ) ){
        accum
      }
      else {
        inner( cx + dx, cy + dy, dx, dy, accum :+ g(cx)(cy) )
      }
    }
    
    inner( 0, 0, 1, 1, List() ) ++ inner( (n -1), 0, -1, 1, List() ) 
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