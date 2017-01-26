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
    
    buildPrimes(1501)
    Console.println( Primes.primeSet.size )
    val s = Primes.primeSet.toList.sorted.tail
    // Console.println( s.head +" "+ s.tail )
    val combs = s.combinations(5)
    
    for( c <- combs ){
      Console.println( c )
    }
    

  }

}