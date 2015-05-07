package org.xarcher.ea.test.util

import slick.dbio.{NoStream, DBIOAction}
import slick.jdbc.JdbcBackend

import scala.language.higherKinds
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object SlickUtil {

  def dbRun[R](db: JdbcBackend#DatabaseDef, a: DBIOAction[R, NoStream, Nothing]): R = {
    Await.result(db.run(a), Duration.Inf)
  }

}