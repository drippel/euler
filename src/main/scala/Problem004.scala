import scala.collection.mutable.ListBuffer


object Problem004 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("004...")
    
    val nums = genNumbers(100,999)
    
    val ps = for( x <- nums; y <- nums; p = ( x * y); if( isPalindrome(p.toString ) ) ) yield { p }
    
    Console.println( ps.sorted.last )
    
    
  }
  
  def genNumbers( lo : Int, hi : Int ) : List[Int] = {
    
    var nums = for( i <- lo to hi ) yield i 
    nums.toList
    
  }
  
  def isPalindrome( s : String ) : Boolean = {
    s.equals( s.reverse )
  }

  
}