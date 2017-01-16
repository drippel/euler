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
import java.math.RoundingMode

object Problem026 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("026...")
    
    for( i <- 2 until 20 ){
      
      val numer = new java.math.BigDecimal("1")
      val denom = new java.math.BigDecimal( i.toString )
      val div = numer.divide(denom,200,RoundingMode.DOWN)
      
      val fmt = new java.text.DecimalFormat( "##.##################################################################################################" )
      
      Console.println( i )
      Console.println( fmt.format( div ) )
      
      
      
    }
    
  }
  
  
}