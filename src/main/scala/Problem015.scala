import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source

object Problem015 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("015...")
    
    // solve( (20,19), (20,20) )
    // solve( (18,18), (20,20) )
    Console.println( solve( (0,0), (20,20) ) )
    solveLong( (0,0), (20,20) )
    Console.println("solution:"+ solutions)
  }
  
  class Move( val x : Int, val y : Int )
  case class Right() extends Move( 1, 0 )
  case class Down() extends Move( 0, 1 )
  val moves = List[Move]( Right() , Down() )
  
  val MIN = 0
  val MAX = 20
  
  val counts : HashMap[(Int,Int),Int] = {
    val h = HashMap[(Int,Int),Int]() 
    h += (19,20) -> 1
    h += (20,19) -> 1
  }
  val totalPaths = 0
  
  def solve( start : (Int,Int), goal : (Int,Int)  ) : Int = {
    
    counts.get( start ) match {
      case s : Some[Int] => {
        s.get 
      }
      case None => {
        
        val ms = generate( start )
        val c = ms.map( solve( _, goal ) )
        
        val ttl = c.foldLeft(0)( (a:Int,b:Int) => { a + b } )
        counts.put(start, ttl)
        ttl
        
      }
    }
    
  }

  var solutions = BigInt(0)
  def solveLong( start : (Int,Int), goal : (Int,Int)  ) : Unit = {
    
    if( start._1 == goal._1 && start._2 == goal._2 ){
      solutions = solutions + 1
      if( solutions % 100000 == 0 ){
        Console.println(solutions)
      }
    }
    else {
      // create some moves - and solve them
      val ms = generate( start )
      ms.foreach( solveLong( _, goal ) )
    }
  }
  
  def generate( start : (Int,Int) ) : List[(Int,Int)] = {
    val ps = moves.map( (m:Move) => { (m.x + start._1,m.y + start._2) }) 
    ps.filter( isValid( _ ) )
  }
  
  def isValid( point : (Int,Int) ) : Boolean = {
    isValid(point._1) && isValid(point._2)
  }
  
  def isValid( n : Int ) : Boolean = {
    n >= MIN && n <= MAX
  }
  
  
}