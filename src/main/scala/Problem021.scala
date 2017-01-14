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
    
    val nums = for( i <- 1 until 10000 ) yield { i }
    
    val ams = nums.map( findAmicable( _ ) )
    
    ams.toList.flatten.foreach( Console.println( _ ) )
    
    val uniq = ams.toList.flatten.toSet
    
    Console.println(uniq)
    
    val sum = uniq.foldLeft(BigInt(0))( (a:BigInt,t : (BigInt,BigInt) ) => {
      a + t._1 + t._2
    } )
    
    Console.println(sum)
    
  }
  
  def findAmicable( n : Int ) : Option[(BigInt,BigInt)] = {
    
    val a = n
    
    val b = d(a)
    
    val db = d(b)
    
    if( a != b && a == db ){
      val p = (List(BigInt(a),b)).sorted
      Some( (p(0),p(1)) )
    }
    else {
      None
    }
    
  }
  
  def d( n : BigInt ) : BigInt = {
    val divs = divisors(n).filter( _ < n )
    val sum = divs.foldLeft(BigInt(0))( (a:BigInt,b:BigInt) => { a + b })
    sum
  }
  
}