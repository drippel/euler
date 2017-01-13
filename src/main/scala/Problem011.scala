import scala.collection.mutable.ListBuffer
import Commons._
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source

object Problem011 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("011...")
    
    val grid = read("./src/main/resources/011.dat" )
    print( grid )
    
    Console.println( solve( grid ) )
    
    
  }

  def solve( grid : Array[Array[Int]] ) : Int = {
    
    var max = 0
    val allLines = makeLines( grid, 4 )
    
    for( line <- allLines ){
      
      line match {
        case s : Some[List[Int]] => {
          
          val ttl = s.get.foldLeft(1)( (a : Int, b : Int) => { a * b } )
          
          if( ttl > max ){
            max = ttl
          }
          
        }
        case None => {
          // nada
        }
      }
      
    }
    
    max

  }
  
  def print( grid : Array[Array[Int]] ) = {
    for( y <- 0 until 20 ){
      for( x <- 0 until 20 ){
        Console.print( grid(x)(y) + " " )
      }
      Console.print( "\n" )
    }
  }
  
  def makeLines( grid : Array[Array[Int]], len : Int ) : List[Option[List[Int]]] = {
    
    val lines = for( x <- 0 until 20; y <- 0 until 20 )
      yield { makeLines( grid, x, y, len ) }
    
    lines.toList.flatten
    
  }
  
  def makeLines( grid : Array[Array[Int]], x : Int, y : Int, len : Int ) : List[Option[List[Int]]] = {
    directions.map( makeLine( grid, x, y, _, len ) )
  }
  
  def makeLine( grid : Array[Array[Int]], x : Int, y : Int, dir : Direction, len : Int ) : Option[List[Int]] = {
    
    var currX = x
    var currY = y
    
    val points = ListBuffer[Int]()
    
    points += grid(currX)(currY) 
    
    for( i <- 1 until len ){
      
      currX = currX + dir.x
      currY = currY + dir.y
      
      if( isValid( currX ) && isValid( currY ) ){
        points += grid(currX)(currY) 
      }
      else {
        // dont add
      }
    }
    
    if( points.length == len ){
      Some(points.toList)
    }
    else {
      None
    }
    
  }
  
  def isValid( x : Int ) : Boolean = {
    if( x < 0 || x > 19 ){
      false
    }
    else {
      true
    }
  }
  
  class Direction( val x : Int, val y : Int )
  case class N() extends Direction( 0, -1 )
  case class NE() extends Direction( 1, -1 )
  case class E() extends Direction( 1, 0 )
  case class SE() extends Direction( 1, 1 )
  case class S() extends Direction( 0, 1 )
  case class SW() extends Direction( -1, 1 )
  case class W() extends Direction( -1, 0 )
  case class NW() extends Direction( -1, -1 )
  
  var directions = List( N(), NE(), E(), SE(), S(), SW(), W(), NW() )
  
  def read( fname : String ) : Array[Array[Int]] = {
    
    val lines = readLines( fname )
    
    val first = lines.head
    
    val parts = first.split(" ")
    
    val grid = Array.ofDim[Int](parts.size, lines.size)
    
    for( y <- 0 until lines.size ){
      val line = lines(y)
      
      val parts = line.split(" ")
      
      for( x <- 0 until parts.size ){
        grid(x)(y) = parts(x).toInt
      }
      
    }
    
    
    grid
    
    
  }
  
  def readLines( fname : String ) : List[String] = {
    val l =  Source.fromFile( fname )
    l.getLines().toList
  }

}