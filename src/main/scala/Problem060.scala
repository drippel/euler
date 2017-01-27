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

object Problem060 {

  def main( args: Array[String] ): Unit = {

    Console.println( "060..." )

    buildPrimes( 1501 )
    val primeList = Primes.primeSet.toList.sorted.tail
    solve( primeList )

  }

  val knownNonPrimes = ListBuffer[List[BigInt]]()

  def solve( primeList: List[BigInt] ) {

    var allCombs = primeList.combinations( 5 )

    var lo = BigInt( Int.MaxValue )
    var winner = List[BigInt]()
    var count = 0

    for( comb <- allCombs ) {

      if( count % 1000000 == 0 ) {
        Console.println( "testing:" + comb )
        Console.println( count )
        Console.println( "current:" + lo +" "+ winner )
        Console.println( "non:" + knownNonPrimes.size )
      }
      count = count + 1

      if ( !containsKnownNonPrimes( comb ) ) {
        // sum the current combo
        val csum = comb.foldLeft( BigInt( 0 ) )( ( a: BigInt, b: BigInt ) => { a + b } )
        if ( csum > lo ) {
          // don't bother
        } else {
          if ( primesOnly( comb ) ) {
            // new winner
            Console.println( "winner:" + comb )
            lo = csum
            winner = comb
          } else {
            // Console.println( "nope:"+ c )
          }
        }
      } else {
        // Console.println("screened out" )
      }
    }

    Console.println( winner )
    Console.println( lo )
    Console.println( count )
  }

  def primesOnly( is: List[BigInt] ): Boolean = {
    // Console.println(is)
    val combs = is.combinations( 2 )
    combs.forall( isPrime( _ ) )
  }

  def isPrime( ps: List[BigInt] ): Boolean = {

    val c1 = ps( 0 ).toString + ps( 1 ).toString
    val c2 = ps( 1 ).toString + ps( 0 ).toString

    // if this combination is not prime - we can filter out the non prime combos
    val p = ( isPrime2( BigInt( c1 ) ) && isPrime2( BigInt( c2 ) ) )

    if ( !p ) {
      // filter
      // Console.println( "adding:" + ps )
      knownNonPrimes += ps
    }

    p
  }

  /*
  def filterCombos(combo: List[BigInt]) = {
    Console.println("before:" + allCombs.size)
    allCombs = allCombs.filter((c: List[BigInt]) => { !(c.contains(combo(0)) && c.contains(combo(1))) })
    Console.println("after:" + allCombs.size)
  }
  * 
  */

  def containsKnownNonPrimes( combo: List[BigInt] ): Boolean = {
    val o = knownNonPrimes.find( ( k: List[BigInt] ) => { combo.contains( k( 0 ) ) && combo.contains( k( 1 ) ) } )
    o.isDefined
  }

}