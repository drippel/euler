import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source

object Problem016 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("016...")
    
    val base = BigInt(2)
    
    val pow = base.pow(1000)
    
    val digits = pow.toString()
    
    val sum = digits.foldLeft(BigInt(0))( (a:BigInt,c:Char) => {
      Console.println( c +" "+ (c-48) )
      a + (c.toInt - 48) 
      } )
      
      
    Console.println(sum)
    

  }
}