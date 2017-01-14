import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat

object Problem023 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("023...")
    
    val nums = for( i <- 1 to 28123 ) yield { i }
    
    val abundants = nums.filter( isAbundant( _ ) )
    
    val sums = for( a <- abundants; b <- abundants ) yield { a + b }
    val sumset = sums.toSet
    
    val notsum = for( i <- 1 to 28123; if( !sumset.contains(i) ) ) yield { BigInt(i) }
    
    notsum.foreach( Console.println( _ ) )
    
    val sum = notsum.foldLeft(BigInt(0))( (a:BigInt,b:BigInt) => { a + b } )
    
    Console.println(sum)
    
    
    
  }
  
  
}