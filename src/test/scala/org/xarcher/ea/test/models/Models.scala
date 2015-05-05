package org.xarcher.ea.test.models

import javax.persistence.{Column, Id}
import org.h2.jdbcx.JdbcDataSource
import org.xarcher.ea.macros.JpaGenerate
import scala.annotation.meta.field

import slick.driver.H2Driver.api._

/**
 * Created by djx314 on 2015/5/5.
 */

/*case class Simple(
  @(Id@field)
  @(Column@field)(name = "id")
  id: Option[Long],
  @(Column@field)(name = "namenimei")
  name: String,
  @(Column@field)(name = "miaode")
  nick: Option[String]
)*/

@JpaGenerate[Simpleaa](tableName = "dsfwfe")
class SimpleTablea()

@JpaGenerate[Simple](tableName = "miaomiaomiaomiao")
class SimpleTable()

import org.scalatest._
class SimpleTest extends FlatSpec with Matchers with BeforeAndAfterAll {

  lazy val articleTable = TableQuery[SimpleTable]

  lazy val db = {
    val datasource = new JdbcDataSource()
    datasource.setUrl(s"jdbc:h2:mem:wang;DB_CLOSE_DELAY=-1")
    Database.forDataSource(datasource)
  }

  def slickRun[R](a: DBIOAction[R, NoStream, Nothing]) = SilckUtils.slickRun(slick.driver.H2Driver)(db)(a)

  override def beforeAll() = {
    slickRun(articleTable.schema.create)
  }

  "User repo" should "insert into article table" in {
    val simple_1 = Simple(None, "sdfsgewrg", Option("userType"))
    slickRun(articleTable += simple_1) should be(1)
  }

  "User repos" should "insert into article table" in {
    val simple_1 = Simple(None, "英莉莉", Option("safgregrtgh"))
    val simple_2 = Simple(None, "金闪闪", Option("safgregrtgh"))
    slickRun(articleTable ++= List(simple_1, simple_2)) should be(Option(2))
    println(slickRun(articleTable.map(_.bbbbbbvcegehrth).result))
  }
}