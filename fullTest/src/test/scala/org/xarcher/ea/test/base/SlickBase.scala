package org.xarcher.ea.test.base

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import org.h2.jdbcx.JdbcDataSource

trait SlickBase {

  val profile: slick.driver.JdbcProfile
  import profile.api._
  
  val h2DbName: String
  
  lazy val db = {
    val datasource = new JdbcDataSource()
    datasource.setUrl(s"jdbc:h2:mem:${h2DbName};DB_CLOSE_DELAY=-1")
    Database.forDataSource(datasource)
  }

  def dbRun[R](a: DBIOAction[R, NoStream, Nothing]): R = {
    Await.result(db.run(a), Duration.Inf)
  }

}