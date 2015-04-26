package org.xarcher.ea.test

import org.scalatest._
import org.h2.jdbcx.JdbcDataSource
import org.xarcher.ea.test.models._
import org.xarcher.ea.test.base.SlickBase

class H2Test extends FlatSpec with Matchers with Repo with SlickBase with BeforeAndAfterAll {

  override val profile = slick.driver.H2Driver
  import profile.api._

  override def beforeAll() = {
    dbRun(articleTable.schema.create)
  }

  override val db = {
    val datasource = new JdbcDataSource()
    datasource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
    Database.forDataSource(datasource)
  }

  "User repo" should "insert into article table" in {
    val article_1 = Article(None, Option(2333), "userType")
    dbRun(articleTable += article_1) should be(1)
    
  }

  it should "insert into large table" in {
    val article_1 = Article(None, Option(6666), "英莉莉")
    val article_2 = Article(None, Option(9527), "金闪闪")
    dbRun(articleTable ++= List(article_1, article_2)) should be(Option(2))
  }
}