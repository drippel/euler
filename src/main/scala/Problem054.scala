import scala.collection.mutable.ListBuffer
import Commons._
import Fibonacci._
import Primes._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat
import java.math.RoundingMode
import java.util.regex.Pattern
import org.apache.commons.lang3.StringUtils
import scala.collection.mutable.BitSet

object Problem054 {
  
  def main(args: Array[String]): Unit = {

    Console.println("054...")
    
    // read hands
    val lines = readLines("./src/main/resources/054.dat")
    
    val rounds = lines.map( toHand( _ ) )
    
    val hrs = rounds.map( (t:(Hand,Hand) ) => { (analyze(t._1), analyze(t._2)) } ) 

    val comps = hrs.map( (t:(List[HandRank],List[HandRank])) => { compareHands(t._1, t._2) } )
    
    val sums = comps.fold((0,0))( (a:(Int,Int),t:(Int,Int)) => { ( a._1 + t._1, a._2 + t._2) } )
    
    Console.println(sums)
    
    val z = comps.zip(hrs)
    
    z.foreach( Console.println( _ ) )
    
    val ts = comps.filter( ( t : (Int,Int) ) => { t._1 == 0 && t._2 == 0 })
    Console.println(ts)
  }
  
  def compareHands( hrs1 : List[HandRank], hrs2 : List[HandRank] ) : (Int,Int) = {
    
    // Console.println( hrs1 +" "+ hrs2 )
    
    def inner( h1 : List[HandRank], h2 : List[HandRank] ) : (Int,Int) ={
      
      h1 match {
        case Nil => {
          Console.println("bad tie...")
          (0,0)
        }
        case _ => {
          
          val hr1 = h1.head
          val hr2 = h2.head
          
          if( hr1.i > hr2.i ){ 
            (1,0) 
          }
          else if( hr2.i > hr1.i ){ 
            (0,1)
          }
          else {
            val c = compare( hr1, hr2 )
            if( c > 0 ){ (1,0) }
            else if( c < 0 ){ (0,1) }
            else {
              Console.println("tie...")
              inner( h1.tail, h2.tail )
            }
          }
        }
      }
    }
    
    inner( hrs1, hrs2 )
    
  }
  
  def analyze( hand : Hand ) : List[HandRank] = {
    
    def inner( remaining : List[Card], finders : List[(List[Card])=>Option[HandRank]], ranks : List[HandRank] ) : List[HandRank] ={
      
      remaining match {
        case Nil => { ranks }
        case _ => {
          // find a match
          innerFinder( remaining, finders ) match {
            case None => {
              // somethings wrong
              ranks
            }
            case Some(hr) => {
              val rem = remaining.diff(hr.allCards)
              inner( rem.sortWith(sortCard).reverse, finders, ( ranks :+ hr ) )
            }
          }
        }
      }
    }
    
    def innerFinder( remaining : List[Card], finders : List[(List[Card])=>Option[HandRank]] ) : Option[HandRank] = {
      finders match {
        case Nil => { None }
        case _ => {
          
          val f = finders.head
          f( remaining ) match {
            case Some(hr) => { Some(hr) }
            case None => {
              innerFinder( remaining, finders.tail )
            }
          }
        }
      }
    }
    
    inner( hand.cards.sortWith(sortCard).reverse, allFinders, List() )
    
  }

  def sortPairs( c1 : List[Card], c2 : List[Card] ) : Boolean = {
    sortCard( c1.head, c2.head )
  }
  
  def sortCard( c1 : Card, c2 : Card ) : Boolean = {
    if( c1.r.i != c2.r.i ){
      c1.r.i < c2.r.i
    }
    else {
      c2.s.s < c2.s.s
    }
  }
  
  def toHand( line : String ) : (Hand,Hand) = {
    
    val parts = line.split(" ")
    val cs = parts.map( toCard( _ ) )
    val css =cs.splitAt(5)
    
    val h1 = css._1.toList
    val hs1 = h1.sortWith( sortCard ).reverse
    val h2 = css._2.toList
    val hs2 = h2.sortWith( sortCard ).reverse
    (Hand(hs1), Hand(hs2) )
  }
  
  def toCard( s : String ) : Card = {
    
    val suit = suits.find( _.s == s(1) ) 
    val rank = ranks.find( _.r == s(0) )
    
    Card( suit.get, rank.get )
    
  }
  
  case class Hand( val cards : List[Card] )
  case class Card( val s : Suit, val r : Rank )
  
  class Suit( val s : Char )
  case class Spades() extends Suit( 'S' )
  case class Hearts() extends Suit( 'H' )
  case class Clubs() extends Suit( 'C' )
  case class Diamonds() extends Suit( 'D' )
  
  val suits = List( Spades(), Hearts(), Clubs(), Diamonds() )
  
  class Rank( val r : Char, val i : Int )
  case class Two() extends Rank( '2', 2 )
  case class Three() extends Rank( '3', 3 )
  case class Four() extends Rank( '4', 4 )
  case class Five() extends Rank( '5', 5 )
  case class Six() extends Rank( '6', 6 )
  case class Seven() extends Rank( '7', 7 )
  case class Eight() extends Rank( '8', 8 )
  case class Nine() extends Rank( '9', 9 )
  case class Ten() extends Rank( 'T', 10 )
  case class Jack() extends Rank( 'J', 11 )
  case class Queen() extends Rank( 'Q', 12 )
  case class King() extends Rank( 'K', 13 )
  case class Ace() extends Rank( 'A', 14 )
  
  val ranks = List( Two(), Three(), Four(), Five(), Six(), Seven(),
    Eight(), Nine(), Ten(), Jack(), Queen(), King(), Ace() )
    
  class HandRank( val i : Int, val allCards : List[Card] )
  case class HighCard( val card : Card ) extends HandRank(1,List(card))
  case class OnePair( val cards : List[Card] ) extends HandRank(2,cards)
  case class TwoPairs( val ps : List[OnePair], val comb : List[Card] ) extends HandRank(3,comb)
  case class ThreeOfAKind( val cards : List[Card] ) extends HandRank(4,cards)
  case class Straight( val cards : List[Card] ) extends HandRank(5,cards)
  case class Flush( val cards : List[Card] ) extends HandRank(6,cards) 
  case class FullHouse( val t : ThreeOfAKind, val p : OnePair ) extends HandRank(7, t.cards ++ p.cards)
  case class FourOfAKind( val cards : List[Card] ) extends HandRank(8,cards) 
  case class StraightFlush( val cards : List[Card] ) extends HandRank(9,cards)
  case class RoyalFlush( val cards : List[Card] ) extends HandRank(10,cards)
  
  val allFinders : List[ (List[Card]) => Option[HandRank]] = List(findRoyalFlush,
      findStraightFlush,findFourOfAKind, findFullHouse,
      findFlush, findStraight,findThreeOfAKind, findTwoPairs,
      findOnePair, findHighCard )
  
  def findHighCard( cards : List[Card] ) : Option[HighCard] = {
    val cs = cards.sortBy( _.r.i ).reverse
    Some(HighCard(cs.head))
  }

  def findOnePair( cards : List[Card] ) : Option[OnePair] = {

    val groups = cards.groupBy( _.r )
    val grps = groups.filter( (t:(Rank,List[Card])) => { t._2.size == 2 })
      
    if( grps.isEmpty ){ None }
    else {
      Some(OnePair(grps.head._2))
    }
  }

  def findTwoPairs( cards : List[Card] ) : Option[TwoPairs] = {
    
    val groups = cards.groupBy( _.r )
    val grps = groups.filter( (t:(Rank,List[Card])) => { t._2.size == 2 })
    val g2 = grps.map( _._2 ).toList.sortWith(sortPairs).reverse
      
    if( grps.isEmpty || grps.size < 2 ){
      None
    }
    else {
      val ps = ListBuffer[OnePair]()
      val cs = ListBuffer[Card]()
      
      for( p <- g2 ){
        ps += OnePair(p)
        cs ++= p
      }
      
      Some(TwoPairs(ps.toList,cs.toList))
    }
      
  }

  def findThreeOfAKind( cards : List[Card] ) : Option[ThreeOfAKind] = {
    
    val groups = cards.groupBy( _.r )
    val grp3 = groups.filter( (t:(Rank,List[Card])) => { t._2.size == 3 })
      
    if( grp3.isEmpty ){
      None
    }
    else {
      Some(ThreeOfAKind(grp3.head._2) )
    }
      
  }
  
  def findStraight( cards : List[Card] ) : Option[Straight] = {
    if( isStraight( cards ) ){
      Some(Straight(cards))
    }
    else {
      None
    }
      
  }
  
  def findFlush( cards : List[Card] ) : Option[Flush] = {
    if( isFlush( cards ) ){
      Some(Flush( cards ))
    }
    else {
      None
    }
    
  }

  def findFullHouse( cards : List[Card] ) : Option[FullHouse] = {
    
    val groups = cards.groupBy( _.r )
    if( groups.size == 2 ){
      // if one of the group sizes if four
      val grp3 = groups.filter( (t:(Rank,List[Card])) => { t._2.size == 3 })
      val grp2 = groups.filter( (t:(Rank,List[Card])) => { t._2.size == 2 })
      
      if( grp3.isEmpty || grp2.isEmpty ){
        None
      }
      else {
        Some(FullHouse( ThreeOfAKind(grp3.head._2), OnePair(grp2.head._2) ) )
      }
      
    }
    else {
      None
    }
    
  }
  
  def findFourOfAKind( cards : List[Card] ) : Option[FourOfAKind] = {
    
    val groups = cards.groupBy( _.r )
    if( groups.size == 2 ){
      // if one of the group sizes if four
      val grp = groups.filter( (t:(Rank,List[Card])) => { t._2.size == 4 })
      
      grp.headOption match {
        
        case Some(m) => {
          Some(FourOfAKind(m._2))
        }
        case None => { None }
      }
    }
    else {
      None
    }
    
  }
  
  def findStraightFlush( cards : List[Card] ) : Option[StraightFlush] = {
    if( isFlush( cards ) ){
      if( isStraight( cards ) ){
        Some(StraightFlush( cards ))
      }
      else {
        None
      }
    } 
    else {
      None
    }
    
  }
  def findRoyalFlush( cards : List[Card] ) : Option[RoyalFlush] = {
    if( isFlush( cards ) ){
    
      val cs = cards.sortBy( _.r.i )
      val css = cs.reverse
      
      if( css.head.r == Ace() && isStraight( cards ) ){
        Some(RoyalFlush( cards ))
      }
      else {
        None
      }
    } 
    else {
      None
    }
    
  }
  
  def isFlush( cards : List[Card] ) : Boolean = {
    val suits = cards.map( _.s ).toSet
    suits.size == 1 && cards.size == 5
  }
  
  def isStraight( cards : List[Card] ) : Boolean = {
    
    if( cards.size < 5 ){
      false
    }
    else {
    
      val cs = cards.sortBy( _.r.i )
    
      val f = cs.foldLeft(0)( (a:Int,c:Card) => {
        if( a == 0 ){ c.r.i }
        else if( a == -1 ){ -1 }
        else if( a + 1 == c.r.i ) { c.r.i }
        else { -1 }
      })
    
      f != -1
    }
    
  }
  
  def compare( hr1 : HandRank, hr2 : HandRank ) : Int = {
    
    hr1 match {
      case hc : HighCard => {
        compare( hr1.asInstanceOf[HighCard], hr2.asInstanceOf[HighCard] )
      }
      case op : OnePair => {
        compare( hr1.asInstanceOf[OnePair], hr2.asInstanceOf[OnePair] )
      }
      case fh : FullHouse => {
        compare( hr1.asInstanceOf[FullHouse], hr2.asInstanceOf[FullHouse] )
      }
      case _ => { 
        Console.println("unknown")
        Console.println( hr1 )
        Console.println( hr2 )
        0
      }
    }
    
  }
  
  def compare( fh1 : FullHouse, fh2 : FullHouse ) : Int = {
    fh1.t.cards.head.r.i.compare(fh2.t.cards.head.r.i)
  }

  def compare( hc1 : HighCard, hc2 : HighCard ) : Int = {
    hc1.card.r.i.compare( hc2.card.r.i )
  }

  def compare( p1 : OnePair, p2 : OnePair ) : Int = {
    p1.cards.head.r.i.compare( p2.cards.head.r.i )
  }
}