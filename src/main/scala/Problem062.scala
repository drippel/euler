import scala.collection.mutable.ListBuffer
import Commons._
import Fibonacci._
import Primes._
import Polygonals._
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat
import java.math.RoundingMode
import java.util.regex.Pattern
import org.apache.commons.lang3.StringUtils
import scala.collection.mutable.BitSet
import java.io.FileWriter
import scala.collection.mutable.PriorityQueue
import scala.collection.immutable.Stack

object Problem062 {

  def main( args: Array[String] ): Unit = {

    Console.println( "062..." )
    
    val cubeMap = HashMap[String,List[(BigInt,BigInt)]]()
    
    var found = false
    var n = BigInt(1)
    
    while( !found ){
      
      // calc the n
      val cube = n * n * n
      
      // convert to string / sort
      val s = cube.toString().sorted
      
      // add to map
      cubeMap.get(s) match {
        case Some(l) => {
          val nl = l :+ (n,cube)
          if( nl.size == 5 ){
            found = true
          }
          cubeMap.put(s, nl)
        }
        case None => {
          cubeMap.put(s, List((n,cube)))
        }
      }
      
      n = n + 1
      
    }
    
    val sol = cubeMap.filter( (e:(String,List[(BigInt,BigInt)])) => { e._2.size == 5 } )
    
    sol.foreach( Console.println(_) )
    
    
    
  }
}