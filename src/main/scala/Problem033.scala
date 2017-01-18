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

object Problem033 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("033...")
    
    val nontrivials = ListBuffer[(Int,Int)]()
    
    for( n <- 10 to 99; d <- 10 to 99 if( n % 10 != 0 && d % 10 != 0 ) ){
      val f1 = toFract( n, d ) 
      if( f1 < 1 ){
        val s1 = n.toString()
        val s2 = d.toString
        val t1 = cancel( s1, s2, 0 )
        val t2 = cancel( s1, s2, 1 )
        
        
        if( t1.isDefined && f1 == t1.get ){
          Console.println( n +", " + d +", "+ f1 + ", "+ t1 + ", " + t2 )
          val t = (n,d)
          nontrivials += t
        }

        if( t2.isDefined && f1 == t2.get ){
          Console.println( n +", " + d +", "+ f1 + ", "+ t1 + ", " + t2 )
          val t = (n,d)
          nontrivials += t
        }
        
      }
    }
    
    Console.println( nontrivials )
    
    val reduce = nontrivials.foldLeft((1,1))( (a:(Int,Int),b:(Int,Int)) => { (a._1*b._1,a._2*b._2) } )
    
    Console.println( reduce )
    
    Console.println( Commons.factor(reduce._1) )
    Console.println( Commons.factor(reduce._2) )
    
    val nfs = Commons.factor(reduce._1)
    val dfs = Commons.factor(reduce._2)
    
    val d1 = nfs.diff(dfs)
    Console.println( d1 )
    
    val d2 = dfs.diff(nfs)
    Console.println( d2 )
    Console.println( d2.foldRight(BigInt(1))( (a:BigInt,b:BigInt) => { a * b } ) )
    
    
    

    
  }
  
  def cancel( s1 : String, s2 : String, pos : Int ) : Option[BigDecimal] = {
    
        if( s2.contains(s1(pos)) ){
          
          val n2 = StringUtils.removeFirst( s1, s1(pos).toString() )
          val d2 = StringUtils.removeFirst( s2, s1(pos).toString() )
          
          if( !n2.isEmpty() && !d2.isEmpty() && d2.toInt != 0 ){ 
            Some(toFract( n2.toInt, d2.toInt ))
          }
          else {
            None
          }
          
        }
        else {
          None
        }
  }
  
  def toFract( n : Int, d : Int ) : BigDecimal = {
    BigDecimal(n) / BigDecimal(d) 
  }
}