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

object Problem057 {
  
  def main(args: Array[String]): Unit = {

    Console.println("057...")
    
    // 1 + 1/2
    // 1 + 1 / ( 2 + 1/2 )

    // 1 + 1 / ( 2 + 1/ ( 2 + 1/2) )
    // 1 + 1 / ( 2 + 1/ ( 4/2 + 1/2) ) - lcd
    // 1 + 1 / ( 2 + 1/(5/2) ) - add
    // 1 + 1 / ( 2 + 2/5) ) - recip
    // 1 + 1 / ( 10/5 + 2/5) ) - lcd
    // 1 + 1/(12/5) ) - add
    // 1 + 5/12 - recipe
    // 12/12 + 5/12 - lcd
    // 17/12 - add
    
    val nums = for( i <- 2 to 1000 ) yield { i }
    val fs = nums.map( generateFormula( "1 + ( 1 / 2 )", "2", "( 2 + ( 1 / 2 ) )", _ ) )
    val sols = fs.map( solveFormula(_) )
    val xs = sols.filter( (t:(BigInt,BigInt)) => { t._1.toString().size > t._2.toString().size } )
    xs.foreach( Console.println(_))
    Console.println(xs.size)

    
  }
  
  def solveFormula( start : String ) : (BigInt,BigInt) = {
    
    def inner( curr : String ) : (BigInt,BigInt) = {
      
      // steps find the last lparen
      val pos = curr.lastIndexOf('(')
      if( pos < 0 ){
        
        // reduced
        // we should have XX / XX
        val ps = curr.split("/")
        ( BigInt(ps(0).trim()), BigInt(ps(1).trim()) )
      }
      else {
    
        // what is the next op to the left
        curr(pos-2) match {
          case '+' => { 
            // +  lcd - replace the next int (1 or 2) with the lcd
            val frac = toFraction( curr, pos )
            // get the next int
            val i = curr(pos-4).toInt - 48
            
            // make the lcd 
            val lcd = makeLCD( i, frac ) 
            
            val start = curr.substring(0, pos - 4)
            val end = curr.substring( pos - 3)
            val next = start + lcd._1 +"/" + lcd._2 + end
            // Console.println( next )
            

            //    add
            val sum = (frac._1 + lcd._1, frac._2)
            val s1 = next.substring(0,pos -4)
            val p1 = next.lastIndexOf("(" )
            val p2 = next.indexOf(")", p1 + 1 )
            var n2 = s1 + sum._1 +"/" + sum._2 
            val p3 = next.indexOf(")", p2 + 1 )
            if( p3 > 0 ){
              val e2 = next.substring( p3 - 1 )
              n2 = n2 + e2
            }

            // Console.println( n2 )
            inner( n2 )
          }
          case '/' => {
            // / recip - replace the 1 / 
            val frac = toFraction(curr,pos)
            val recip = (frac._2,frac._1)
            
            val s1 = curr.substring(0, pos - 4 )
            val p1 = curr.lastIndexOf("(" )
            val p2 = curr.indexOf(")", p1 + 1 )
            val p3 = curr.indexOf(")", p2 + 1 )
            val e2 = curr.substring( p3 - 1 )
            val n2 = s1 + recip._1 +"/" + recip._2 + e2
            // Console.println( n2 )
            inner( n2 )
          }
          case _ => {
            throw new IllegalStateException( "unexpected char:"+ curr(pos-2) )
          }
        }
    
      }
    }
    
    
    inner(start)
  }
  
  def toFraction( s : String, pos : Int ) : (BigInt,BigInt) = {
    
    val end = s.indexOf( ")", pos )
    val sub = s.substring(pos+1, end)
    val ps = sub.split("/")
    ( BigInt(ps(0).trim()), BigInt(ps(1).trim()) )
    
  }
  
  def makeLCD( i : BigInt, frac : (BigInt,BigInt) ) : (BigInt,BigInt) = {
    (i * frac._2, frac._2 )
  }
  
  def generateFormula( base : String, token : String, replace : String, iters : Int ) : String = {
    
    def inner( curr : String, left : Int ) : String = {
      
      if( left < 1 ){
        curr
      }
      else {
        val pos = curr.lastIndexOf(token)
        val beg = curr.substring(0, pos)
        val end = curr.substring( pos + token.length() )
        val next = beg + replace + end 
        inner( next, left - 1 )
      }
      
    }
    
    inner( base, iters )
    
  }
  
}