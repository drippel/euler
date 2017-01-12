

object Problem001 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("1...")
    
    var sum = 0
    
    for( i <- 1 until 1000 ){
      
      if( i % 3 == 0 ){
        sum = sum + i
      }
      else if( i % 5 == 0 ){
        sum = sum + i
      }
      
    }
    Console.println( sum )
  }
  
}