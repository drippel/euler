import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat

object Problem021 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("021...")
    
    Console.println( d(220) )
    Console.println( d(284) )
    
  }
  
  def d( n : BigInt ) : BigInt = {
    val divs = divisors(n).filter( _ < n )
    val sum = divs.foldLeft(BigInt(0))( (a:BigInt,b:BigInt) => { a + b })
    sum
  }
  
}