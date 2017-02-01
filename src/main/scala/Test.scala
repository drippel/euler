

object Test {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("test...")
    for( i <- 0 until 2000 ){
      for( j <- 0 until 2000 ){
        
      }
    }
    Console.println("done...")
    
    val i = 23
    val n = scala.math.sqrt(i).toInt
    Console.println( n )
    
    val a : Int = ( 7 / 3 )
    Console.println( a )
    
    Console.println( '0'.toInt )
    
    Console.println( Int.MaxValue )

    func();
  }
  
  def func() = {
    val upperbound = 17
    var result = BigInt(0)
 
    var d = BigInt(1);
    var n = BigInt(2);
 
    for( i <- 2 to upperbound ) {
      var temp = d;
      var c = if( i % 3 == 0) { 2 * (i / 3) } else { 1 };
      d = n;
      n = c * d + temp;
    }

    Console.println( n )
  }
  
}