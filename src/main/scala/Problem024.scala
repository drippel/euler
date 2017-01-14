import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat

object Problem024 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("024...")
    
    val l = List( '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' )
    // val l = List( '0', '1', '2' )
    
    val perms = for( p <- l.permutations ) yield { p.mkString } 
    
    val sorted = perms.toList.sorted
    
    sorted.foreach( Console.println( _ ) )

    Console.println( sorted(999999) )
    Console.println( sorted(1000000) )
    
  }
  
  
}