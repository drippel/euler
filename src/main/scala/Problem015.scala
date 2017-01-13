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
    solve( (18,18), (20,20) )
    Console.println( solutions )
  }
  
  class Move( val x : Int, val y : Int )
  case class Right() extends Move( 1, 0 )
  case class Down() extends Move( 0, 1 )
  val moves = List[Move]( Right() , Down() )
  
  val MIN = 0
  val MAX = 20
  var solutions = BigInt(0)
  
  val counts = HashMap[(Int,Int),Int]()
  
  def solve( start : (Int,Int), goal : (Int,Int)  ) : Unit = {
    
    if( start._1 == goal._1 && start._2 == goal._2 ){
      solutions = solutions + 1
    }
    else {
      // create some moves - and solve them
      val ms = generate( start )
      if( ms.isEmpty ){
        //
        Console.println( "wha" )
      }
      else {
        ms.foreach( solve( _, goal ) )
      }
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