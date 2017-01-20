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

object Problem049 {

  def main(args: Array[String]): Unit = {

    Console.println("049...")
    
    val start = "1234567890"
    
    val combs = combinations(start,4).sorted
    combs.foreach( Console.println( _ ) )
   
  }
  
  def combinations( start : String, size : Int ) : List[String] = {
    val all = HashSet[String]()
    val combs = start.combinations(size)
    
    for( c <- combs ){
      val cp = c.permutations
      for( cpp <- cp ){
        all += cpp
      }
    }
    all.toList
  }
  
}