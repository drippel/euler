import scala.collection.mutable.ListBuffer


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
}