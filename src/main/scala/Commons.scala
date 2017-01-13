import scala.collection.mutable.ListBuffer
import scala.io.Source


object Commons {
  
  def factor( n : BigInt ) : List[BigInt] = {
    
    val fs = ListBuffer[BigInt]()
    
    var reduced = n
    var currFactor = BigInt(2)
    
    while( reduced > 1 ){
      
      val next = findFactor( currFactor,  reduced )
      
      while( reduced % next == 0 ){
         reduced = reduced / next
         fs += next
      }

      currFactor = next + 1
      
    }
    
    fs.toList
    
  }
  
  
  
  def findFactor( start : BigInt, n : BigInt ) : BigInt = {
    
    var factor = BigInt(0)
    var curr = start
    
    while( factor == 0 ){
      
      if( n % curr == 0 ){
        factor = curr
      }
      
      curr = curr + 1
      
    }
    
    factor

  }

  def isPalindrome( s : String ) : Boolean = {
    s.equals( s.reverse )
  }
  
  def divisors( n : BigInt ) : List[BigInt] = {
    
    val divs = ListBuffer[BigInt]()
    
    // always start with 1 and the number
    divs += 1
    divs += n
    
    for( i <- 2 to scala.math.sqrt(n.toDouble).toInt ){
      
      if( n % i == 0 ){
        divs += i
        divs += n / i
      }
      
    }
    
    divs.toList.sorted
  }
  
  def readLines( fname : String ) : List[String] = {
    val l =  Source.fromFile( fname )
    l.getLines().toList
  }
}