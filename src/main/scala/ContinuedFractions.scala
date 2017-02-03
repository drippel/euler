

object ContinuedFractions {
  
  def main( args : Array[String] ) : Unit = {
  
    Console.println("square root continued fractions...")
    // x - the number we are trying to find the square of
    // 1 + ( x - 1 / 2 + ( f ) )
    // f - repeats
    
    val sqf = generateFormula( "1 + ( n / f )", "( 2 + ( n / f ) )", "( 2/1 )", 5, 5 )
    Console.println( sqf )
    
    val sq = solveFormula( sqf )
    Console.println( sq )
    
  }
  
  def firstReduction( start : String ) : String = {
    
    val lastlp = start.lastIndexOf('(')
    val nextlp = start.lastIndexOf('(', lastlp - 1)
    
    val lastrp = start.indexOf(')', lastlp )
    val nextrp = start.indexOf(')', lastrp + 1 )
    
    val inner = start.substring(lastlp+1, lastrp) 
    val outer = start.substring(nextlp, nextrp + 1) 
    Console.println(inner)
    Console.println(outer)
    
    val ps = inner.split("/")
    val f1 = (ps(0).trim().toInt, ps(1).trim().toInt)
    
    start
  }

  def solveFormula( start : String ) : (BigInt,BigInt) = {
    
    // first reduction is a special case
    firstReduction(start)
    
    def inner( curr : String ) : (BigInt,BigInt) = {
      
      // steps find the last lparen
      val pos = curr.lastIndexOf('(')
      if( pos < 0 ){
        
        // reduced
        // we should have XX / XX
        val ps = curr.split("/")
        ( BigInt(ps(0).trim()), BigInt(ps(1).trim()) )
      }
      else {
    
        // what is the next op to the left
        curr(pos-2) match {
          case '+' => { 
            // +  lcd - replace the next int (1 or 2) with the lcd
            val frac = toFraction( curr, pos )
            // get the next int
            val i = curr(pos-4).toInt - 48
            
            // make the lcd 
            val lcd = makeLCD( i, frac ) 
            
            val start = curr.substring(0, pos - 4)
            val end = curr.substring( pos - 3)
            val next = start + lcd._1 +"/" + lcd._2 + end
            Console.println( next )
            

            //    add
            val sum = (frac._1 + lcd._1, frac._2)
            val s1 = next.substring(0,pos -4)
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

  def generateFormula( base : String, repeat : String, last : String, times : Int, n : Int ) : String = {
    val f = generateFormula( base, repeat, last, times )
    val s = f.replaceAll("n", ( n - 1 ).toString )
    s
  }
  
  def generateFormula( base : String, repeat : String, last : String, times : Int ) : String = {
    
    def inner( curr : String, left : Int ) : String = {
      
      if( left < 1 ){
        curr
      }
      else if( left == 1 ) {
        val p = curr.lastIndexOf("f")
        val start = curr.substring(0, p)
        val end = curr.substring(p + 1)
        val next = start + last + end
        inner( next, left - 1 )
      }
      else {
        val p = curr.lastIndexOf("f")
        val start = curr.substring(0, p)
        val end = curr.substring(p + 1)
        val next = start + repeat + end
        inner( next, left - 1 )
      }
      
    }
    
    inner( base, times )
    
  }
  
  def toFraction( s : String, pos : Int ) : (BigInt,BigInt) = {
    
    val end = s.indexOf( ")", pos )
    val sub = s.substring(pos+1, end)
    if( sub.indexOf('/') > -1 ){
      val ps = sub.split("/")
      ( BigInt(ps(0).trim()), BigInt(ps(1).trim()) )
    }
    else {
      ( BigInt(sub.trim()), BigInt(1) )
    }
    
  }
  
  def makeLCD( i : BigInt, frac : (BigInt,BigInt) ) : (BigInt,BigInt) = {
    (i * frac._2, frac._2 )
  }
  
}