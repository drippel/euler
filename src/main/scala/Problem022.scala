import scala.collection.mutable.ListBuffer

import Commons._
import TriangleNumbers.triangleNumber
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.LinkedHashSet
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat

object Problem022 {
  
  def main( args : Array[String] ) : Unit = {
    Console.println("022...")
    
    val lines = Commons.readLines("./src/main/resources/022.dat" )
    val raw = lines(0).split(",")
    
    val names = raw.map( _.replaceAll( "\"", "" ) )
    
    val sorted = names.sorted
    
    var sum = BigInt(0)
    for( i <- 0 until sorted.size ){
     Console.println( i +" "+ sorted(i)  +" "+ scoreName( sorted(i) ) )
     sum = sum + ( (i + 1) * scoreName( sorted(i) ) )
    }
    
    Console.println( sum )
    
    
    

  }
  
  def scoreName( name : String ) : BigInt = {
    
    val score = name.foldLeft(BigInt(0))( (a:BigInt,c:Char) => {
      a + (c.toInt - 64)
    })
    
    score
    
  }
  
  
  
  
}