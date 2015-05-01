package org.xarcher.ea.test

import org.scalatest._
import org.xarcher.ea.test.models._

class H2Test extends FlatSpec with Matchers with Repo with BeforeAndAfterAll {

  override val profile = slick.driver.H2Driver

  override val h2DbName = "h2miaotest"
  
  override def beforeAll() = {
    articleCreate
  }

  "User repo" should "insert into article table" in {
    val article_1 = Article(None, Option(2333), "userType")
    insertArticle(article_1) should be(1)
  }

  "User repos" should "insert into article table" in {
    val article_1 = Article(None, Option(6666), "英莉莉")
    val article_2 = Article(None, Option(9527), "金闪闪")
    insertArticles(List(article_1, article_2)) should be(Option(2))
  }
}