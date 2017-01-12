import scala.collection.mutable.ListBuffer


object Problem003 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("003...")
    
    val start = BigInt(600851475143L)
    val fs = factor( start )

    // val start = BigInt(13195)
    // val fs = factor( 13195 )
    // val fs = factor( 144 )
    
    // buildPrimes(25)

    Console.println(fs)
    
  }

  
  def factor( n : BigInt ) : List[BigInt] = {
    
    val fs = ListBuffer[BigInt]()
    
    var reduced = n
    var currFactor = BigInt(2)
    
    while( reduced > 1 ){
      
      val next = findFactor( currFactor,  reduced )
      Console.println("found factor:"+ next )
      
      while( reduced % next == 0 ){
         reduced = reduced / next
         fs += next
      }

      Console.println("reduced:"+ reduced )
      
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
  
  def buildPrimes( limit : BigInt ) = {

    if( !primes.contains(limit) ){

      // get the max prime
      val start = primes.last 
      for( i <- start to limit by 2 ){
        addPrime(i)
      }
      
    }
  }
  
  val primes = ListBuffer[BigInt](2,3)
  
  def addPrime( n : BigInt ) : Boolean = {
    if( primes.contains(n) ){
      true
    }
    else {
     if( primes.forall( n % _ != 0 ) ){
       // Console.println("prime:"+ n )
       primes += n
       true
     }
     else {
       false
     }
    }
  }
  
}