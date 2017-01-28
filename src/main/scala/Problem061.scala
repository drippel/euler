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
  case class Path( steps : List[Step], left : List[String] )
  
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
    
    val left = List( "TRI", "SQS","PEN","HEX","HEP","OCT" )
    
    val bpaths = ListBuffer[Path]()
    
    for( t <- sets.get("TRI").get ){
      // bootstrap the queue from one the sets
      val step = Step( t, "TRI" )
      bpaths += Path( List( step ), left )
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
      if( current.steps.size == 7 ){
        if( current.steps.head.n == current.steps.last.n ){ 
          solved = Some(current)
        }
      }
      else {
        
        // for current look for matches in the next set
        val nextset = current.left.head
        
        val ms = findMatches( current, sets.get(nextset).get )
        
        for( m <- ms ){
          
          // make a new step and path
          val st = Step( m, nextset )
          val path = Path( current.steps :+ st, current.left.tail )
          work.enqueue(path)
          
        }
        
      }
    }
    
    Console.println( "solution:"+ solved.get )
    
    var sum = solved.get.steps.foldLeft(BigInt(0))( (a:BigInt,s:Step) => { a + s.n } ) 
    sum = sum - solved.get.steps.last.n
    Console.println( sum ) 

    
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