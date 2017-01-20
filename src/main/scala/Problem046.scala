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

object Problem046 {

  def main(args: Array[String]): Unit = {

    Console.println("046...")

    solve()

  }

  def solve(): Int = {

    var found = false
    var solution = 0
    var n = 9
    while (!found) {

      if (!isPrime(n)) {

        val ps = primesLessThan(n)
        val diffs = ps.map((i: Int) => { (n, i, (n - i)) })
        val halves = diffs.map((t: (Int, Int, Int)) => { (t._1, t._2, t._3, (t._3 / 2)) })
        val sqs = halves.map((t: (Int, Int, Int, Int)) => { (t._1, t._2, t._3, t._4, scala.math.sqrt(t._4)) })
        val ans = sqs.map((t: (Int, Int, Int, Int, Double)) => { (t._1, t._2, t._3, t._4, t._5, (t._5 == 1 || t._5 % 1 == 0)) })
        val sol = ans.forall(_._6 == false)

        if (sol) {
          ans.foreach(Console.println(_))
          found = true
          solution = n
        } else {
          n = n + 2
        }
      } else {
        n = n + 2
      }
    }

    n

  }

  def primesLessThan(n: Int): List[Int] = {

    // two doesn't make sense in this problem
    val ps = for (i <- (n - 1) until 2 by -1 if (isPrime(i))) yield { i }

    ps.toList

  }

}