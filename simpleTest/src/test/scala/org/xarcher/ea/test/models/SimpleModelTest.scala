package org.xarcher.ea.test.models

import org.h2.jdbcx.JdbcDataSource
import org.xarcher.ea.macros.jpa.{JpaJavaGenerate, JpaTableGenerate}

import slick.driver.H2Driver.api._

/**
 * Created by djx314 on 2015/5/5.
 */

@JpaTableGenerate[Simple](tableName = "miaomiaomiaomiao")
class SimpleTable()

@JpaTableGenerate[ScalaServant](tableName = "miaomiaomiaomiao")
class ScalaServantTable()

@JpaJavaGenerate[aa]
class ScalaSimple1111111

import org.scalatest._
class SimpleModelTest extends FlatSpec with Matchers with BeforeAndAfterAll {

  lazy val simpleTable = TableQuery[SimpleTable]

  lazy val db = {
    val datasource = new JdbcDataSource()
    datasource.setUrl(s"jdbc:h2:mem:wang;DB_CLOSE_DELAY=-1")
    Database.forDataSource(datasource)
  }

  def slickRun[R](a: DBIOAction[R, NoStream, Nothing]) = SilckUtils.slickRun(slick.driver.H2Driver)(db)(a)

  override def beforeAll() = {
    slickRun(simpleTable.schema.create)
  }

  "Simple model" should "insert into simple table by annotationed TableQuery" in {
    val simple_1 = Simple(None, "sdfsgewrg", Option("userType"))
    slickRun(simpleTable += simple_1) should be(1)
  }

  "Simple models" should "insert into simple table by annotationed TableQuery" in {
    val simple_1 = Simple(None, "英莉莉", Option("safgregrtgh"))
    val simple_2 = Simple(None, "金闪闪", Option("safgregrtgh"))
    slickRun(simpleTable ++= List(simple_1, simple_2)) should be(Option(2))
    println(slickRun(simpleTable.map(_.nick).result))
  }
}