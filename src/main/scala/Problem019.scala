import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat

object Problem019 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("019...")
    
    val dts = for( year <- 1901 to 2000; month <- 1 to 12 ) yield {
      format( year, month, 1 )
    }
    
    val dates = dts.map( toDate( _ ) ) 
    val names = dates.map( unformat( _ ) )
    names.foreach( Console.println( _ ) )
    val sundays = names.filter( _.startsWith("Sun" ) )
    
    Console.println( sundays.size )
    
  }
  
  def format( y : Int, m : Int, d : Int ) : String = {
    val month = m.toString().reverse.padTo(2, "0").reverse.mkString
    val day = d.toString().reverse.padTo(2, "0").reverse.mkString
    y.toString + month + day 
  }
  
  def unformat( dt : Date ) : String = {
    val sdf = new SimpleDateFormat( "EEE yyyyMMdd" )
    sdf.format(dt)
  }

  def toDate( dt : String ) : Date = {
    val sdf = new SimpleDateFormat( "yyyyMMdd" )
    sdf.parse(dt)
  }
}