import scala.collection.mutable.ListBuffer

import Commons._

object Problem066 {
  
  def main( args: Array[String] ): Unit = {

    Console.println( "066..." )
    
    // val dxs = for( d <- 1 until 1001; x <- 1 until 2001 ) yield { ( d, x ) } 
    // val valid = dxs.filter( (t:(Int,Int)) => { isDiophantine(t._1,t._2).isDefined } ) 
    // val ss = valid.sortBy(_._2)
    // ss.foreach(Console.println(_))
    // val dxs = for( x <- 1 until 20 ) yield { ( 5, x ) } 
    // dxs.foreach(Console.println(_))
    // val valid = dxs.filter( (t:(Int,Int)) => { isDiophantine(t._1,t._2).isDefined } ) 
    // valid.foreach(Console.println(_))
    //Console.println( isDiophantine(5, 9) )
    //Console.println( isDiophantine(5, 3) )
    //Console.println( isDiophantine(5, 5) )
    // Console.println( d2(5, 1) )
    // val dxs = for( x <- 1 until 1000 ) yield { ( 7, x ) } 
    // dxs.foreach(Console.println(_))
    // val valid = dxs.filter( (t:(Int,Int)) => { isDiophantine(t._1,t._2).isDefined } ) 
    // valid.foreach(Console.println(_))
    solve()
  }
  
  def solve() = {
    
    def inner( d : BigInt, x : BigInt, accum : List[(BigInt,BigInt)] ) : List[(BigInt,BigInt)] = {
      
      if( d % 100 == 0 || x % 100000  == 0 ) {
        Console.print( "...(" + d +","+ x +")..." )
      }
      
      if( d > 1000 ){ 
        Console.println( "done" )
        accum 
      }
      else if( isSquare( BigDecimal(d) ) ){
        Console.println( "skip "+ d )
        inner( d + 1, 2, accum) 
      }
      else if( x > 10000001 ){ 
        Console.println( "nope... ("+ d +","+ x +")" )
        inner( d + 1, 2, accum) 
      }
      else if( isDiophantine( d, x).isDefined ){
        // found - go to next d
        Console.println( d +","+ x )
        inner( d + 1, 2, accum :+ (d,x) )
      }
      else {
        inner( d, x + 1, accum )
      }
      
    }
    
    val sols = inner( 2, 2, List() )
    
    // sols.foreach(Console.println(_))
    val ss = sols.sortBy(_._2)
    sols.foreach(Console.println(_))
    Console.println(sols.size)
  }
  
  def isDiophantine( d : BigInt, x : BigInt ) : Option[BigInt] = {
    
    
    if( isSquare( BigDecimal(d) ) ){ None }
    else {
      val x2 = ( x * x ) - 1  
      val d2 = BigDecimal(x2) / BigDecimal(d)
      // val y = scala.math.sqrt( d2.toDouble )
      if( d2 == 0 ){
        Console.println( d +" "+ x )
      }
      val y = BigSqrt.sqrt( d2 )
      if( y.setScale(20,BigDecimal.RoundingMode.HALF_DOWN).isValidInt ){ Some( y.toBigInt() ) }
      else { None }
    }
  }
  
  def diophantine( d : Int, x : Int, y : Int ) : Int = {
    ( x * x ) - ( d * ( y * y ) ) - 1
  }

  def d2( d : Int, x : Int ) : Double = {
    scala.math.sqrt( ( ( x * x ) - 1 ) / d )
  }
  
  def isSquare( n : BigDecimal ) : Boolean = {
    val s = BigSqrt.sqrt( n )
    s.setScale(20,BigDecimal.RoundingMode.HALF_DOWN).isValidInt
  }

}
