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

object Problem061 {

  def main( args: Array[String] ): Unit = {

    Console.println( "061..." )
    
    
    val ts = getTris()
    sets += ("TRI" ->  ts)
    val ss = getSqs()
    sets += ("SQS" ->  ss)
    val ps = getPents()
    sets += ("PEN" ->  ps)
    val hxs = getHexs()
    sets += ("HEX" ->  hxs)
    val hps = getHepts()
    sets += ("HEP" ->  hps)
    val os = getOcts()
    sets += ("OCT" ->  os)
    
    solve()
    
  }
  
  val sets = HashMap[String,List[BigInt]]()

  case class Step( n : BigInt, set : String )
  case class Path( steps : List[Step] )
  
  class BySize() extends Ordering[Path] {
      def compare( p1 : Path, p2 : Path ) : Int = {
        if( p1.steps.size == p2.steps.size ){
          p2.steps.head.n.compare(p1.steps.head.n)
        }
        else {
          p2.steps.size.compare(p1.steps.size)
        }
      }
  }

  var work = PriorityQueue[Path]()( (new BySize()) )
  
  def bootstrap() : List[Path] = {
    
    val bpaths = ListBuffer[Path]()
    
    for( s <- sets ){
      // bootstrap the queue from one the sets
      val bsteps = s._2.map( (b:BigInt) => { Step( b, s._1 ) } )
      bsteps.foreach( (st:Step) => { bpaths += Path( List( st ) ) } )
    }
    
    bpaths.toList
  }
  
  def solve() = {
    
    val bpaths = bootstrap()
    
    for( b <- bpaths ){
      work.enqueue(b)
    }
    
    var solved : Option[Path] = None
    
    // while queue has work and not solved
    while( !work.isEmpty && !solved.isDefined ){
      
      // take the current path from queue
      val current = work.dequeue()
      
      Console.println( current )
    
      // is it a solution?
      if( current.steps.size == 6 ){
        solved = Some(current)
      }
      else {
    
        // for each set not in the current path
        val left = sets.keySet.diff(toSets(current))
        
        // find possible matches
        for( l <- left ){
          
          val ms = findMatches( current, sets.get(l).get )
          
          for( m <- ms ){
            // queue those new paths
            val s = Step( m, l )
            work.enqueue( Path( current.steps :+ s ) )
          }

        }
    
      }
    }
    
    Console.println( "solution:"+ solved.get )
    
    Console.println( solved.get.steps.foldLeft(BigInt(0))( (a:BigInt,s:Step) => { a + s.n } ) )
    
  }
  
  def findMatches( p : Path, is : List[BigInt] ) : List[BigInt] = {
    val start = p.steps.last.n.toString.substring(2)
    is.filter( (i:BigInt) => { i.toString.startsWith(start) } ) 
  }
  
  def toSets( p : Path ) : Set[String] ={
    p.steps.map(_.set ).toSet
  }
  
  def getNos( f : (BigInt) => BigInt ) =
    () => {
    
    def inner( n : Int, accum : List[BigInt] ) : List[BigInt] = {
      
      val t = f(n)
      if( t.toString.size > 4 ){
        accum
      }
      else if( t.toString.size == 4 ){
        inner( n + 1, ( accum :+ t ) )
        
      }
      else {
        inner( n + 1, accum )
      }
      
    }
    
    inner(1, List() )
  }
  
  val getTris = getNos( triangle ) 
  val getSqs = getNos( square ) 
  val getPents = getNos( pentagonal ) 
  val getHexs = getNos( hexagonal ) 
  val getHepts = getNos( heptagonal ) 
  val getOcts = getNos( octagonal ) 
  
}