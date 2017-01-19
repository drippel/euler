import scala.collection.mutable.ListBuffer
import Commons._
import Fibonacci._
import Primes._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat
import java.math.RoundingMode
import java.util.regex.Pattern
import org.apache.commons.lang3.StringUtils

object Problem042 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("042...")
    
    
    // Console.println( 'S'.toInt - 64 )
    
    val lines = readLines("./src/main/resources/042.dat")
    val words = convert(lines(0))
    val ws = words.map( (s:String) => { (s,sum(s)) })
    
    // val wss = ws.sortBy( _._2 )
    // val max = wss.last
    // Console.println(max)

    val tris = HashSet[Int]()
    for( i <- 1 to 25 ){
      tris += tri( i )
    }
    
    val skys = ws.filter( (t:(String,Int)) => { tris.contains(t._2) } )
    
    skys.foreach( Console.println(_) )
    Console.println(skys.size)
    
    
    
  }
  
  def sum( s : String ) : Int = {
    s.foldLeft(0)( (a:Int,c:Char) => {
      a + ( c.toInt - 64 )
    })
  }
  
  def convert( line : String ) : List[String] = {
    
    
    def inner( parts : List[String], accum : List[String] ) : List[String] = {
      
      parts match {
        case Nil => { accum }
        case h :: t => {
          inner( t, accum :+ h.replaceAll("\"", "") )
        }
      }
      
    }
    
    inner( line.split(",").toList, List() )
    
  }
  
  def tri( n : Int ) : Int = {
    val d = ( BigDecimal(0.5) * BigDecimal(n) ) * BigDecimal(( n + 1 ))
    d.toInt
  }
  
}