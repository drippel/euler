import scala.collection.mutable.ListBuffer
import Commons._
import scala.collection.mutable.HashMap

object Problem009 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("009...")
    
    solve()
  }
  
  def solve() : Unit = {
    
    val r = 1 to 500
    
    val ps = for( 
        a <- r; 
        b <- r; 
        c <- r; 
        if( 
            ( (a + b + c) == 1000)
            && ( ((a * a) + (b * b)) == (c * c) ) ) ) yield { (a,b,c) }
    
    Console.println(ps)
    
    val prods = ps.map( (t:(Int,Int,Int)) => { t._1 * t._2 * t._3 } )
    
    Console.println(prods)
  }
  
  
}