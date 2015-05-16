package org.xarcher.ea.test.models

import slick.dbio.{NoStream, DBIOAction}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Created by djx314 on 15-5-5.
 */
object SilckUtils {

  def slickRun[R](profile: slick.driver.JdbcProfile)(db: profile.backend.DatabaseDef)(a: DBIOAction[R, NoStream, Nothing]): R =
    Await.result(db.run(a), Duration.Inf)

}