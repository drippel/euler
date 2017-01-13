import scala.collection.mutable.ListBuffer
import Commons._
import scala.collection.mutable.HashMap

object Problem007 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("007...")
    
    buildPrimesBySize(10001)
    
    Console.println(primes.last)
    
  }
  
  val primes = ListBuffer[BigInt](2,3)

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
       Console.println("prime:"+ n )
       primes += n
       true
     }
     else {
       false
     }
    }
  }
}