package org.xarcher.ea.test

import org.scalatest._
import org.xarcher.ea.test.models._
import models.profile.api._

class GlobalImportTest extends FlatSpec with Matchers with BeforeAndAfterAll {


  override def beforeAll() = {
    h2SlickRun(globalArticleTable.schema.create)
  }

  "Golbal user repo" should "insert into article table" in {
    val article_1 = Article(None, Option(11111111), "userType")
    h2SlickRun(globalArticleTable += article_1) should be(1)
  }

  "Golbal user repos" should "insert into article table" in {
    val article_1 = Article(None, Option(6666), "英莉莉")
    val article_2 = Article(None, Option(9527), "金闪闪")
    h2SlickRun(globalArticleTable.map(_.userType).result)
    h2SlickRun(globalArticleTable ++= List(article_1, article_2)) should be(Option(2))
  }
  
}