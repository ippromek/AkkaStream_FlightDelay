/**
  * Created by oleg.baydakov on 25/04/2017.
  * https://github.com/oel/akka-streams-text-mining/blob/master/src/main/scala/ngrams/TextProcessor.scala
  */
package ngrams

import scala.collection.mutable.ListBuffer

object TextProcessor {
  // Subset of Punct character class """!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~"""
  val puncts: String = """.,;?!"""

  // Generate a list of "sliding" n-grams
  def genNgrams(text: String, n: Int): String = {
    var listWords = new ListBuffer[String]()

    text.
      toLowerCase.
      replaceAll(s"""[${puncts}]""", "").
      split("""\s+""").
      sliding(n).
      foreach{
        x => listWords += x.mkString(" ")
      }

    listWords.mkString(", ")
  }
}
