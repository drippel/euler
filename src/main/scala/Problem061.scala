import scala.collection.mutable.ListBuffer
import Commons._
import Fibonacci._
import Primes._
import Polygonals._
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
import scala.collection.mutable.PriorityQueue
import scala.collection.immutable.Stack

object Problem061 {

  def main( args: Array[String] ): Unit = {

    Console.println( "061..." )

    val ts = getTris()
    sets += ( "TRI" -> ts )
    val ss = getSqs()
    sets += ( "SQS" -> ss )
    val ps = getPents()
    sets += ( "PEN" -> ps )
    val hxs = getHexs()
    sets += ( "HEX" -> hxs )
    val hps = getHepts()
    sets += ( "HEP" -> hps )
    val os = getOcts()
    sets += ( "OCT" -> os )

    solve()

  }

  val sets = HashMap[String, List[BigInt]]()

  case class Step( n: BigInt, set: String )
  case class Path( steps: List[Step], left: Set[String] )

  class BySize() extends Ordering[Path] {
    def compare( p1: Path, p2: Path ): Int = {
      if ( p1.steps.size == p2.steps.size ) {
        p2.steps.head.n.compare( p1.steps.head.n )
      } else {
        p2.steps.size.compare( p1.steps.size )
      }
    }
  }

  var work = PriorityQueue[Path]()( ( new BySize() ) )

  def bootstrap(): List[Path] = {

    var funcs = List( "TRI", "SQS", "PEN", "HEX", "HEP", "OCT" )

    val bpaths = ListBuffer[Path]()

    for ( n <- 0 until 6 ) {

      val ( h, t ) = ( funcs.head, funcs.tail )

      for ( i <- sets.get( h ).get ) {
        // bootstrap the queue from one the sets
        val step = Step( i, h )
        bpaths += Path( List( step ), t.toSet )
      }

      funcs = t :+ h
    }

    bpaths.toList
  }

  def solve() = {

    val bpaths = bootstrap()

    for ( b <- bpaths ) {
      work.enqueue( b )
    }

    var solved: Option[Path] = None

    // while queue has work and not solved
    while ( !work.isEmpty && !solved.isDefined ) {

      // take the current path from queue
      val current = work.dequeue()

      // Console.println( current )

      // is it a solution?
      if ( current.steps.size == 6 ) {
        // Console.println( current )
        if ( matches( current.steps.last.n, current.steps.head.n ) ) {
          Console.println( "solution?" )
          // solved = Some( current )
          Console.println( current )
          var sum = current.steps.foldLeft( BigInt( 0 ) )( ( a: BigInt, s: Step ) => { a + s.n } )
          Console.println( sum )
        }
      } else {

        // for current look for matches in the remaining sets
        for ( ns <- current.left ) {

          val ms = findMatches( current, sets.get( ns ).get )

          for ( m <- ms ) {

            // make a new step and path
            val left = current.left -ns
            val st = Step( m, ns )
            val path = Path( current.steps :+ st, left ) 
            work.enqueue( path )

          }
        }

      }
    }

    Console.println( "solution:" + solved.get )

    var sum = solved.get.steps.foldLeft( BigInt( 0 ) )( ( a: BigInt, s: Step ) => { a + s.n } )
    sum = sum - solved.get.steps.last.n
    Console.println( sum )

  }
  
  def matches( a : BigInt, b : BigInt ) : Boolean = {
    val start = a.toString.substring( 2 )
    b.toString.startsWith( start )
  }

  def findMatches( p: Path, is: List[BigInt] ): List[BigInt] = {
    is.filter( ( i: BigInt ) => { matches( p.steps.last.n, i ) } )
  }

  def toSets( p: Path ): Set[String] = {
    p.steps.map( _.set ).toSet
  }

  def getNos( f: ( BigInt ) => BigInt ) =
    () => {

      def inner( n: Int, accum: List[BigInt] ): List[BigInt] = {

        val t = f( n )
        if ( t.toString.size > 4 ) {
          accum
        } else if ( t.toString.size == 4 ) {
          inner( n + 1, ( accum :+ t ) )

        } else {
          inner( n + 1, accum )
        }

      }

      inner( 1, List() )
    }

  val getTris = getNos( triangle )
  val getSqs = getNos( square )
  val getPents = getNos( pentagonal )
  val getHexs = getNos( hexagonal )
  val getHepts = getNos( heptagonal )
  val getOcts = getNos( octagonal )

}