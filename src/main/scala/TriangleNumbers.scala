import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer


object TriangleNumbers {
  
  val triangleMap = HashMap[Int,BigInt]( (0->0),(1->1))
  val triangleList = ListBuffer[BigInt]( 0, 1)
  
  def triangleNumber( n : Int ) : BigInt = {
    
    triangleMap.get(n) match {
      case s : Some[BigInt] => {
        s.get
      }
      case None => {
        generateTriangleNumber(n)
      }
    }
  }
  
  
  def generateTriangleNumber( n : Int ) : BigInt = {
    
    val hi = triangleList.length - 1
    
    for( i <- (hi + 1) to n ) {
      val next = triangleList.last + i 
      triangleList += next
      triangleMap += ( i -> next )
    }
    
    triangleList.last
    
  }
}