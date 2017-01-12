

object Problem002 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("2...")
    
    /*
    val r = ( 1 to 50 ).toList.map( fib( _ ) ).takeWhile( _ < 4000000 )
      .filter( _ % 2 == 0 )
      .foldLeft(0)( (a:Int,b:Int) => { a + b } )
      
    Console.println( r )
    * 
    */
    
    var sum = 0
    var n = 1
    var f = 0
    do {
      f = fib( n )
      if( f % 2 == 0 && f < 4000000 ){
        sum = sum + f 
      }
      n = n + 1
    }while( f < 4000000 )
      
    Console.println(sum)
      
    
  }
  
  def fib( n : Int ) : Int = {
    if( n < 2 ){
      1
    }
    else if( n < 3 ) {
      2
    }
    else {
      fib( n - 1 ) + fib( n - 2 )
    }
  }
  
}