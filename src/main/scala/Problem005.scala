import scala.collection.mutable.ListBuffer
import Commons._
import scala.collection.mutable.HashMap

object Problem005 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("005...")
    
    val factorMap = HashMap[Int,List[Int]]()
    
    for( i <- 1 to 10 ){
      val fs = factor( i )
      Console.println( fs )
      
      val groups = fs.groupBy( _.toInt )
      Console.println( groups )
      for( g <- groups ){
      }
    }
    
    val fs = factor( 2520 )
    Console.println( fs )
    
  }
  
}