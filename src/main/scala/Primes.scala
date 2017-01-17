import scala.collection.mutable.HashSet


object Primes {


  var primeSet = HashSet[BigInt]()
  var lastPrime = BigInt(0)
  
  def isPrime( n : BigInt ) : Boolean = {
    
    if( primeSet.contains(n) ){
      true
    }
    else {
      
      if( lastPrime > n ){
        false
      }
      else {
        // rebuild the primes and ask again
        buildPrimes( n.toInt + 1 )
        primeSet.contains(n)
      }
    }
    
  }
    
   def buildPrimes( max : Int ) = {
     
     primeSet = HashSet[BigInt]()
    
    val all = Array.fill[Boolean](max+1)(true)
    
    all(0) = false
    all(1) = false
    
    // find first true
    for( idx <- 2 until all.size ){  
      
      if( all(idx) ){
    
        primeSet += idx
        lastPrime = idx
    
        for( i <- idx until all.size by idx ){
          all(i) = false
        }
      }

    }
  }
    
}