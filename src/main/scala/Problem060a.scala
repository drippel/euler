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

object Problem060a {

  def main( args: Array[String] ): Unit = {

    Console.println( "060a..." )
    
    buildPrimes(99011009)

    Console.println( (new Date()).getTime )

    val allPrimes = Primes.primeSet.filter( _ <= 9001 ).toList.sorted.tail
    Console.println(allPrimes.size)
    var ps = allPrimes.map( Set(_) ).toSet
    
    for( i <- 0 until 4 ){
      ps = build( ps, allPrimes )
    }

    Console.println( (new Date()).getTime )
    Console.println( ps )
    Console.println( ps.size )

  }
  
  def build( start : Set[Set[BigInt]], all : List[BigInt] ) : Set[Set[BigInt]] = {
    
    val out = HashSet[Set[BigInt]]()
    
    for( s <- start ){
      for( a <- all if( a > s.last ) ){
          val n = (s + a)
          if( primesOnly( n ) ){
            out += n
          }
      }
    }
    
    
    out.toSet
    
  }

  def primesOnly( is: Set[BigInt] ): Boolean = {
    // Console.println(is)
    val combs = is.toList.combinations( 2 )
    combs.forall( isPrime( _ ) )
  }

  def isPrime( ps: List[BigInt] ): Boolean = {

    val c1 = ps( 0 ).toString + ps( 1 ).toString
    val c2 = ps( 1 ).toString + ps( 0 ).toString

    // if this combination is not prime - we can filter out the non prime combos
    ( Primes.isPrime( BigInt( c1 ) ) && Primes.isPrime( BigInt( c2 ) ) )
  }
  
}