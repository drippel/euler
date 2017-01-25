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

object Problem058 {
  
  def main(args: Array[String]): Unit = {

    Console.println("058...")
    
    solve()
  }
  
  def solve() : Int = {
    
    def inner( n : Int, accum : (Int,Int) ) : Int = {
      
      val cs = calcCorners(n,accum)
      val ratio = BigDecimal(cs._1) / BigDecimal(cs._2)
      
      Console.println( n +" "+ ratio )
      
      if( ratio < 0.10 ){
        n
      }
      else {
        inner( n + 2, cs )
      }
    }
    
    inner(3, (0,1) )
  }
  
  def calcCorners( n : Int, accum : (Int,Int) ) : (Int,Int) = {
    
        val c1 = ( n * n )
        var naccum = (accum._1,accum._2+1)
        
        val c2 = c1 - ( n - 1 )  
        naccum = if( isPrime2(c2) ){
          (naccum._1 + 1,naccum._2+1)
        }
        else {
          (naccum._1,naccum._2+1)
        }

        val c3 = c2 - ( n - 1 )  
        naccum = if( isPrime2(c3) ){
          (naccum._1 + 1,naccum._2+1)
        }
        else {
          (naccum._1,naccum._2+1)
        }

        val c4 = c3 - ( n - 1 )  
        naccum = if( isPrime2(c4) ){
          (naccum._1 + 1,naccum._2+1)
        }
        else {
          (naccum._1,naccum._2+1)
        }
        
        naccum


  }
  
}