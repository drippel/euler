import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source

object Problem013 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("013...")
    
    val lines = readLines( "./src/main/resources/013.dat" )
    
    val is = lines.map( convert( _ ) )
    
    is.foreach( Console.println( _ ) )
    
    val sum = is.foldLeft(BigInt(0))( (a:BigInt,b:BigInt) => { a + b} )
    Console.println( sum )
  }
  
  def convert( line : String ) : BigInt = {
    BigInt(line)
  }
  
}