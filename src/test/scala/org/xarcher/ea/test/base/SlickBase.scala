package org.xarcher.ea.test.base

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration

trait SlickBase {

  val profile: slick.driver.JdbcProfile
  import profile.api._
  
  val db: Database

  def dbRun[R](a: DBIOAction[R, NoStream, Nothing]): R = {
    Await.result(db.run(a), Duration.Inf)
  }

}