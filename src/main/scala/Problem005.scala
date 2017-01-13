import scala.collection.mutable.ListBuffer
import Commons._
import scala.collection.mutable.HashMap

object Problem005 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("005...")
    
    val factorMap = HashMap[Int,List[BigInt]]()
    
    for( i <- 1 to 20 ){
      val fs = factor( i )
      
      val groups = fs.groupBy( _.toInt )
      for( g <- groups ){
          
        factorMap.get(g._1) match {
          
          case s : Some[List[BigInt]] => {
            if( s.get.size < g._2.size ){
              factorMap += (g._1 -> g._2)
            }
          }
          case None => {
            factorMap += (g._1 -> g._2)
          }
        }
      }
    }
    
    val fs = factor( 2520 )
    Console.println( fs )
    Console.println( factorMap )
    
    val f1 = factorMap.map( _._2 ).flatten
    
    val prod = f1.foldLeft(BigInt(1))( (a:BigInt,b:BigInt) => { a * b })
    Console.println(prod)
    
  }
  
}