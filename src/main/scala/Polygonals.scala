

object Polygonals {
  
  def triangle( n : BigInt ) : BigInt = {
    ( n * ( n + 1 ) ) / 2
  }
  
  def square( n : BigInt ) : BigInt = {
    n * n
  }

  def pentagonal( n : BigInt ) : BigInt = {
    ( n * ( ( 3 * n ) - 1 ) ) / 2
  }

  def hexagonal( n : BigInt ) : BigInt = {
    n * ( ( 2 * n ) - 1 )
  }
  
  
  def heptagonal( n : BigInt ) : BigInt = {
    ( n * ( ( 5 * n ) - 3 ) ) / 2
  }

  def octagonal( n : BigInt ) : BigInt = {
    n * ( ( 3 * n ) - 2 ) 
  }
  
  def main( args : Array[String] ) : Unit = {
    for( n <- 1 to 5 ) {
      Console.println( "n:" + n +" tri:"+ triangle( n ) +" sq:"+ square( n ) 
          +" pent:" + pentagonal(n)  
          +" hex:" + hexagonal(n)  
          +" hept:" + heptagonal(n)  
          +" oct:" + octagonal(n) )  
    }
    
  }
}