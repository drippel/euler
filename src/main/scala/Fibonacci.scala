

import scala.collection.mutable.HashMap

object Fibonacci {
  
  val fibMap = HashMap[BigInt,BigInt]()
  
  def fib( n : BigInt ) : BigInt = {
    
    fibMap.get(n) match {
      case s : Some[BigInt] => { s.get }
      case None => {
        if( n < 1 ){
          throw new IllegalArgumentException( "invalid number:"+ n )
        }
        else if( n == 1 ){
          BigInt(1)
        }
        else if( n == 2 ){
          BigInt(1)
        }
        else {
          val f = fib( n - 2 ) + fib( n - 1 )
          fibMap += ( n -> f )
          f
        }
      }
    }
  }
  
}