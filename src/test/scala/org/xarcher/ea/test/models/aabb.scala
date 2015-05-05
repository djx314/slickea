package org.xarcher.ea.test.models

import javax.persistence.{Column, Id}

import slick.dbio.{NoStream, DBIOAction}

import scala.annotation.meta.field
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Created by djx314 on 15-5-5.
 */
object SilckUtils {

  def slickRun[R](profile: slick.driver.JdbcProfile)(db: profile.backend.DatabaseDef)(a: DBIOAction[R, NoStream, Nothing]): R =
    Await.result(db.run(a), Duration.Inf)

}

case class Simpleaa(
                     @(Id@field)
                     @(Column@field)(name = "i22222d")
                     i2312d: Option[Long],
                     @(Column@field)(name = "namen3333imei")
                     nafsdfsdfme: String,
                     @(Column@field)(name = "miaodsfsdfsdfe")
                     nisfsfck: Option[String]
                     )