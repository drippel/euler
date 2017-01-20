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

object Problem051 {
  
  case class Family( val start : Int, val mask : String, val primes : List[Int] )

  def main(args: Array[String]): Unit = {

    Console.println("051...")
    
    isPrime(1000000)
    
    var winner = Family( 1, "", List() )
    
    for( i <- 11 until 1000000 by 2 if( isPrime(i) ) ){
      val fs = buildFamilies( i )  
      val fss = fs.sortBy( _.primes.size )
      if( fss.last.primes.size > 7 ){
        Console.println( fss.last )
      }
      if( fss.last.primes.size > winner.primes.size ){
        winner = fss.last
      }
    }
    
    Console.println( winner )
    
  }
  
  def buildFamilies( start : Int ) : List[Family] = {
    
    val fams = ListBuffer[Family]()
    
    val masks = generate(start.toString.length())
    
    for( m <- masks ) {
      val res = applyMask( start.toString, m )
      val ps = res.filter( (s:String) => { isPrime(s.toInt) } ) 
      if( ps.size > 0 ){
        val f = Family( start, m, ps.map( _.toInt ) )
        fams += f
      }
    }
    
    fams.toList
    
  }
  
  def applyMask( start : String, mask : String ) : List[String] = {
    
    val digits = "0123456789"
    
    val ms = digits.map( (c:Char) => { applyMask( start, mask, c ) } )
    
    val ml = ms.toList
    
    ml.filter( !_.startsWith("0") ) 
    
  }
  
  def applyMask( start : String, mask : String, c : Char ) : String = {
    
    val s = for( i <- 0 until start.size ) yield {
      if( mask(i) == '1' ){ c }
      else { start(i) }
    }
    s.mkString
  }
  
  def variants( start : Int ) : List[Int] = {
    val masks = generate(start.toString.length())
    List()
  }
  
  def generate( len : Int ) : List[String] = {
    
    val end = scala.math.pow(2, len).toInt 
    val ss = for( i <- 0 until end; val s = toBits(i,len); if( on(s) > 0 && on(s) < len ) ) yield { s }
    ss.toList
    
  }
  
  def on( s : String ) : Int = {
    s.foldLeft(0)( (a:Int,c:Char) => {
     if( c =='0' ){ a }
     else { a + 1 }
    })
  }
  
  def toBits( i : Int, len : Int ) : String ={
   i.toBinaryString.reverse.padTo(len, '0').reverse
  }
  
}