import scala.collection.mutable.ListBuffer

import Commons._
import Fibonacci._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat

object Problem025 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("025...")
    
    for( i <- 1 until 20 ){
      Console.println( i +" "+ fib(i) )
    }
    
    var found = false 
    var n = BigInt(1)
    var fn = BigInt(0) 
    
    while( !found ){
      
     fn = fib(n) 
     
     if( fn.toString.length() > 999 ){
       found = true
     }
     else {
       n = n + 1
     }
      
    }
    
    Console.println("n:"+ n +" fn:"+ fn )
    
    
    
  }
  
  
}