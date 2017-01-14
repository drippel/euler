import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat

object Problem020 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("020...")
    
    val f = factorial(100)
    val s = f.toString
    
    val sum = s.foldLeft(BigInt(0))((a:BigInt,c:Char) => { a + ( c.toInt - 48 ) } )
    
    Console.println(sum)
    
  }
  
}