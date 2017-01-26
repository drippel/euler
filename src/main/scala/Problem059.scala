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
import java.io.FileWriter

object Problem059 {
  
  def main(args: Array[String]): Unit = {

    Console.println("059...")
    
    // read in the file
    val lines = readLines( "./src/main/resources/059.dat" )
    
    val bs = convert( lines(0) )
    
    // brute(bs)
    
    val ns = for( i <- 0 until 3 ) yield { narrow( bs, i ) }
    val ts = trips( ns(0), ns(1), ns(2) )
    
    // Console.println( ts )
    
    // brute2( bs, ts )
    

    Console.println( decode( bs, "god".toList ) )
    val s = decode( bs, "god".toList )
    val sum = s.foldLeft(0)( (a:Int,c:Char) => { a + c.toInt } )
    Console.println( sum )
  }

  def brute2( ct : List[String], ts : List[List[Char]] ) = {
    
    val out = new FileWriter( "./src/main/resources/log.txt" ) 
    
    for( t <- ts ){
      out.write( t.mkString +" " )
      out.write( decode( ct, t ) )
      out.write( "\n" )

    }
    
    out.flush()
    out.close()
    
  }
  
  def narrow( bs : List[String], n : Int ) = { 

    var ps = "abcdefghijklmnopqrstuvwxyz".toList
    for( i <- n until bs.size by 3 ){
      ps = possibles( bs(i), ps.toList ) 
    }
    
    ps
    
  }
  
  def decode( in : String, key : String ) = {
    
    for( i <- 0 until in.size ){
      val kp = i % 3 
      val o = in(i) ^ key(kp)
      Console.print( o.toChar )
    }
  }
  
  def convert( line : String ) : List[String] = {
    line.split(",").toList
  }
  
  def possibles2( in : String ) : List[Char] = {
    
    val cs = ListBuffer[Char]()
    
    val i = in.toInt
    for( c <- 'a' to 'z' ) {

      val o = i ^ c.toInt;
      cs += o.toChar 
    }
      
    cs.toList
    
  }
  
  def possibles( in : String, ps : List[Char] ) : List[Char] = {
    
    // Console.println( "in:"+ in)
    // if( valid.contains( o.toChar ) )
    
    val i = in.toInt
    val cs = for( c <- ps;  o = i ^ c.toInt;  
       if( o >= 32 && o <= 126 )) yield { c }
    cs
    
  }
  
  def decodes( in : String, ps : List[Char] ) : List[Char] = {
    
    val i = in.toInt
    val cs = for( c <- ps;  o = i ^ c.toInt;  
       if( o >= 32 && o <= 126 )) yield { o.toChar }
    cs
    
  }
  val valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.;:?!\",\'() "
  
  def brute( ct : List[String] ) = {
    
    val all = "abcdefghijklmnopqrstuvwxyz"
    val ts = trips( all.toList, all.toList, all.toList )
    Console.println( ts.size )
    
    for( t <- ts ){
      Console.println( decode( ct, t ) )
    }
    
  }
  
  def decode( ct : List[String], key : List[Char] ) : String = {
    
    var out = ""
    
    for( i <- 0 until ct.size ){
      
      val ci = ct(i).toInt
      val p = ( i % 3 )
      val kc = key(p)
      
      val oi = ci ^ kc.toInt
      val oc = oi.toChar
      
      out = out + oc
      
    }
    
    
    out
    
  }
  
  def trips( as : List[Char], bs : List[Char], cs : List[Char] ) = {
    
    val ts = for(
        a <- as;
        b <- bs;
        c <- cs ) yield { List(a,b,c) }
    
    ts.toList
  }
  
  
}