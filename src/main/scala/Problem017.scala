import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source

object Problem017 {
  
  val numMap = {
    
    val m = HashMap[Int,String]()
    
    m += ( 1 -> "one" )
    m += ( 2 -> "two" )
    m += ( 3 -> "three" )
    m += ( 4 -> "four" )
    m += ( 5 -> "five" )
    m += ( 6 -> "six" )
    m += ( 7 -> "seven" )
    m += ( 8 -> "eight" )
    m += ( 9 -> "nine" )
    m += ( 10 -> "ten" )
    m += ( 11 -> "eleven" )
    m += ( 12 -> "twelve" )
    m += ( 13 -> "thirteen" )
    m += ( 14 -> "fourteen" )
    m += ( 15 -> "fifteen" )
    m += ( 16 -> "sixteen" )
    m += ( 17 -> "seventeen" )
    m += ( 18 -> "eighteen" )
    m += ( 19 -> "nineteen" )
    m += ( 20 -> "twenty" )
    m += ( 30 -> "thirty" )
    m += ( 40 -> "forty" )
    m += ( 50 -> "fifty" )
    m += ( 60 -> "sixty" )
    m += ( 70 -> "seventy" )
    m += ( 80 -> "eighty" )
    m += ( 90 -> "ninety" )
    m += ( 100 -> "hundred" )
    m += ( 1000 -> "thousand" ) 
    
    m
    
  }
  
  def main( args : Array[String] ) : Unit = {
    Console.println("017...")
    
    val nums = for( i <- 1 to 999 ) yield i
    
    val words = nums.map( translate( _ ) )
    
    var sum = words.foldLeft(0)( (a:Int,b:String) => { a + b.length() } )
    
    sum = sum + "onethousand".length()
    Console.println( sum )

    Console.println( translate( 342 ) )
    Console.println( translate( 342 ).length() )
    Console.println( translate( 113 ) )
    Console.println( translate( 113 ).length() )
  }
  
  def translate( n : Int ) : String = {
    if( n >= 100 ){

      val s = n.toString() 
      val h = s(0).toInt - 48
      
      // remainder
      val rem = n - ( h * 100 )
      
      if( rem > 0 ){
        translate( h ) + "hundredand" + translate( rem )
      }
      else {
        translate( h ) + "hundred"
      }
      
    }
    else if( n >= 20 ){
      val s = n.toString() 
      val t = s(0).toInt - 48

      val rem = n - ( t * 10 )
      numMap.get((t * 10)).get + translate( rem )
      
    }
    else if( n > 0 ) {
      numMap.get(n).get
    }
    else {
      ""
    }
  }
}