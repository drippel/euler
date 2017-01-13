import scala.collection.mutable.ListBuffer
import Commons._
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet

object Problem010 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("010...")
    
    buildPrimes3(2000000)
    // buildPrimes3(100)
    // val sum = primes.foldLeft(BigInt(0))( (a:BigInt,b:BigInt) => { a + b} )
    // Console.println(sum)
    // Console.println( primes.head + "," + primes.last )
    Console.println( primes.head +"," + primes.last )

    val sum = primes.foldLeft(BigInt(0))( (a:BigInt,b:BigInt) => { a + b} )
    Console.println( sum )
    
  }

  // val primes = ListBuffer[BigInt](2,3)
  val primes = ListBuffer[BigInt]()

  def buildPrimes( limit : BigInt ) = {

    if( !primes.contains(limit) ){

      // get the max prime
      val start = primes.last 
      for( i <- start to limit by 2 ){
        addPrime(i)
      }
      
    }
  }
  
  def buildPrimesBySize( size : BigInt ) = {

    var current = primes.last 
    while( primes.size < size ){
      addPrime(current)
      current = current + 2
    }
      
  }
  
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
  
  def buildPrimes2( max : Int ) = {
    
    val all = LinkedHashSet[Int]()
    
    val start = for( i <- 2 to max ){
      all += i 
    }
    
    primes += 2
    primes += 3
    primes += 5
    primes += 7
    primes += 11
    
    all -= 2
    all -= 3
    all -= 5
    all -= 7
    all -= 11
    
    for( i <- 4 to max ){
      if( (i % 2 == 0) || (i % 3 == 0) || ( i % 5 == 0 ) || ( i % 7 == 0 ) || ( i % 11 == 0 ) ){
        all -= i
      }
    }

    Console.println( all.size )
    
    while( all.size > 0 ){
      
      val p = all.head
      
      primes += p
      all -= p
      
      // reduce
      for( i <- (p + 1) to max ){
        if( i % p == 0 ){
          all -= i
        }
      }
      
    }
    
  }

  def buildPrimes3( max : Int ) = {
    
    val all = Array.fill[Boolean](max+1)(true)
    
    all(0) = false
    all(1) = false
    
    // find first true
    for( idx <- 2 until all.size ){  
      
      if( all(idx) ){
    
        primes += idx
    
        for( i <- idx until all.size by idx ){
          all(i) = false
        }
      }

    }
    
  }
}