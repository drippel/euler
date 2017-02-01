import scala.collection.mutable.ListBuffer

import Commons._

object Problem065 {
  
  // used this page http://oeis.org/A007676

  def main( args: Array[String] ): Unit = {

    Console.println( "065..." )
    
    var lines = readLines("./src/main/resources/065.dat")
    // lines = lines.slice(0,17)
    lines = lines.slice(0,100)
    val as = lines.map( (s:String) => {
      val ps = s.split(" ")
      ps(1).toInt
    } ) 
    
    // as.foreach( Console.println( _ ) )
    
    /*
     * base formula as the form of
     * 2 + 1 / a1 + 1 / a2 + 1 / ... 
     *     
     */
    
    val eq = generateFormula("( 2 + ( 1 / a ) )", "a", "( an + ( 1 / a ) )", as.tail )
    Console.println( eq )
    val sol = solveFormula(eq)
    Console.println( sol )
    
    val sum = sol._1.toString.foldLeft(BigInt(0))( (a:BigInt,c:Char) => { 
      a + BigInt( c.toInt - 48 ) } )
      
    Console.println( sum )

    
    
    
  }

  def generateFormula( base : String, token : String, replace : String, as : List[Int] ) : String = {
    
    def inner( curr : String, left : List[Int] ) : String = {
      
      left match {
        case Nil => { curr } 
        case h :: t => {
          
          if( t.isEmpty ){
            Console.println("last one" )
            val pos = curr.lastIndexOf(token)
            val beg = curr.substring(0, pos)
            val end = curr.substring( pos + token.length() )
            val next = beg + h.toString() + end 
            inner( next, t )
          }
          else {
            val pos = curr.lastIndexOf(token)
            val beg = curr.substring(0, pos)
            val end = curr.substring( pos + token.length() )
            val next = beg + replace + end 
          
            // replace an
            val p2 = next.lastIndexOf("an")
            val b2 = next.substring(0, p2)
            val e2 = next.substring( p2 + "an".length() )
            val n2 = b2 + h.toString() + e2 
            inner( n2, t )
          }
        }
      }
      
    }
    
    inner( base, as )
    
  }

  def solveFormula( start : String ) : (BigInt,BigInt) = {
    
    def inner( curr : String ) : (BigInt,BigInt) = {
      
      // steps find the last lparen
      val pos = curr.lastIndexOf('(')
      if( pos <= 0 ){
        
        // reduced
        // we should have ( XX / XX )
        val ps = curr.replace("(", "" ).replace( ")", "" ).split("/")
        ( BigInt(ps(0).trim()), BigInt(ps(1).trim()) )
      }
      else {
    
        // what is the next op to the left
        curr(pos-2) match {
          case '+' => { 
            // +  lcd - replace the next int (1 or 2) with the lcd
            val frac = toFraction( curr, pos )
            
            
            // get the next int - 4 is a bad assumption
            val lp = curr.lastIndexOf("(", pos - 2 )
            val i = curr.substring(lp + 1, pos - 2).trim().toInt
            Console.println( i )
            
            // make the lcd 
            val lcd = makeLCD( i, frac ) 
            
            val start = curr.substring(0, lp + 1 )
            val end = curr.substring( pos - 3)
            val next = start + lcd._1 +"/" + lcd._2 + end
            Console.println( next )
            

            //    add
            val sum = (frac._1 + lcd._1, frac._2)
            val s1 = next.substring(0,lp + 1)
            val p1 = next.lastIndexOf("(" )
            val p2 = next.indexOf(")", p1 + 1 )
            var n2 = s1 + sum._1 +"/" + sum._2 
            val p3 = next.indexOf(")", p2 + 1 )
            if( p3 > 0 ){
              val e2 = next.substring( p3 - 1 )
              n2 = n2 + e2
            }

            Console.println( n2 )
            inner( n2 )
          }
          case '/' => {
            // / recip - replace the 1 / 
            val frac = toFraction(curr,pos)
            val recip = (frac._2,frac._1)
            
            val s1 = curr.substring(0, pos - 4 )
            val p1 = curr.lastIndexOf("(" )
            val p2 = curr.indexOf(")", p1 + 1 )
            val p3 = curr.indexOf(")", p2 + 1 )
            val e2 = curr.substring( p3 - 1 )
            val n2 = s1 + recip._1 +"/" + recip._2 + e2
            Console.println( n2 )
            inner( n2 )
          }
          case _ => {
            throw new IllegalStateException( "unexpected char:"+ curr(pos-2) )
          }
        }
    
      }
    }
    
    
    inner(start)
  }
  
  def toFraction( s : String, pos : Int ) : (BigInt,BigInt) = {
    
    val end = s.indexOf( ")", pos )
    val sub = s.substring(pos+1, end)
    val ps = sub.split("/")
    ( BigInt(ps(0).trim()), BigInt(ps(1).trim()) )
    
  }
  
  def makeLCD( i : BigInt, frac : (BigInt,BigInt) ) : (BigInt,BigInt) = {
    (frac._2 * i, frac._2 )
  }
}
