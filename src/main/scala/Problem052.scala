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
import scala.collection.mutable.BitSet

object Problem052 {
  
  def main(args: Array[String]): Unit = {

    Console.println("052...")
    
    for( i <- 1 until 1000000 ){
      if( allSame(i) ){
        Console.println(i)
      }
    }
    
  }
  
  def allSame( i : Int ) : Boolean = {
    if( sameDigits( i, i * 2 ) 
        && sameDigits( i, i * 3 ) 
        && sameDigits( i, i * 4 ) 
        && sameDigits( i, i * 5 ) 
        && sameDigits( i, i * 6 ) ){
      true
    }
    else {
      false
    }
  }
  
  
  def sameDigits( i1 : Int, i2 : Int ) : Boolean = {
    val res = i1.toString().diff(i2.toString())
    res.size == 0
  }
  
}