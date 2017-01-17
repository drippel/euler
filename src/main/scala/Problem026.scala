import scala.collection.mutable.ListBuffer
import Commons._
import Fibonacci._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat
import java.math.RoundingMode
import java.util.regex.Pattern

object Problem026 {
  
  def main( args : Array[String] ) : Unit = {

    Console.println("026...")
    
    /*
    var n = 0
    var max = 0
    val pat = buildPattern()
    val fmt = new java.text.DecimalFormat( pat )
    
    for( i <- 2 until 1000 if( i % 2 != 0 ) if( i % 3 != 0 ) if( i % 5 != 0 ) ){
      
      val numer = new java.math.BigDecimal("1")
      val denom = new java.math.BigDecimal( i.toString )
      val div = numer.divide(denom,10000,RoundingMode.DOWN)
      val s = f"$div%f"
      Console.println( i +" "+ s )
      
      
      // Console.println( fmt.format( div ) )
      val ret = repeatingRegex( fmt.format( div ) )
      if( ret._1 ){
        
        if( ret._2.length() > max ){
          max = ret._2.length()
          n = i
          Console.println( "new max:"+ i +" "+ max )
        }
        
      }
      
    }
    
    Console.println( n +", "+ max )
    */
    
    var max = BigInt(0)
    var n = 0
    
    for( i <- 2 until 1000 if( i % 2 != 0 ) if( i % 5 != 0 ) ){
    
      val denom = BigInt(i)
      val r = calcRepeat(denom)
      
      if( r > max ){
        max = r
        n = i
        Console.println( n )
      }

    }
    
   Console.println( n + " " + max )

   val numer = new java.math.BigDecimal("1")
   val denom = new java.math.BigDecimal( 983 )
   val div = numer.divide(denom,10000,RoundingMode.DOWN)
      
   val fmt = new java.text.DecimalFormat( buildPattern() )
   val ret = repeatingRegex( fmt.format( div ) )
   Console.println(ret)
        
  }
  
  def buildPattern() : String = {
      var fmt = "##."
      for( i <- 1 to 10000 ){
       fmt = fmt + "#"
      }
      fmt
  }
  
  def findRepeating( s : String ) : (Boolean,String) = {
    
    var found = (false,"")
    var pos = 0

    while( pos < s.length() / 2 ){
    
      val f = findRepeatingFrom( s, pos )
      
      if( f._1 == false ){
        pos = pos + 1
      }
      else {
        // is this longer 
        found = f 
      }
      
      pos = pos + 1
      
    }
    
    found
    
  }
  
  def findRepeatingFrom( s : String, start : Int ) : (Boolean,String) = {
    
    var ret = (false,"")
    
    var c = s(start)
    var next = s.indexOf(c, start + 1)
    
    while( next != -1 ){
      
      if( next + 1 < (s.length() - 1) ){
     
        val s1 = s.substring(start, next)
        val s2 = s.substring(next, next + s1.length() )
      
        ret = if( s1.equals(s2) ){
          (true, s1 )
        }
        else {
          next = s.indexOf(c, next + 1)
          (false,"")
        }
      }
      
    }
    
    ret

  }
  
  def findRepeating2( s : String ) : (Boolean,String) = {
    
    val repeats = ListBuffer[String]()
    
    var ret = (false,"")
    
    for( i <- 0 until (s.length()/2) ){
      Console.println(s)
      for( j <- 1 until ((s.length()/2) - 2) ){
        Console.println( i +","+ j )
        val p = s.substring(i, i + j + 1)
        Console.println(p)
      }
    }

    ret
    
  }
  
  def findRepeatingNew( s : String ) : (Boolean,String) = {
    
    val repeats = ListBuffer[String]()
    
    var ret = (false,"")
    
    for( i <- 0 until (s.length()/2) ){
      Console.println(s)
      for( j <- 1 until ((s.length()/2) - 2) ){
        Console.println( i +","+ j )
        val p1 = s.substring(i, i + j + 1)
        Console.println(p1)
        val p2 = s.substring( i + j + 1, ( i + j + 1 ) + p1.length() ) 
        if( p1.equals(p2) ){
          ret = (true,p1)
        }
      }
    }

    ret
    
  }
  
  def repeatingRegex( s : String ) : (Boolean,String) = {
    var ret = (false,"")
    
    val p = Pattern.compile("(.+?)\\1+");
    val m = p.matcher(s);
    while (m.find()) {
        val repeated = m.group(1);
        ret = (true,repeated)
    }
    
    
    ret
  }
  
  def calcRepeat( denom : BigInt ) : BigInt = {
    
    var found = false
    var cycle = BigInt(0)
    var n = BigInt(1)
    var ten = BigInt(10)
    
    while( !found ) {
      
      // Console.println( ten.pow(n.toInt) - 1 )
      if( ( ( ten.pow(n.toInt) ) - BigInt(1) ) % denom == 0 ){
        found = true
        cycle = n
      }
      
      n = n + 1
      
    }

    cycle
  }
}