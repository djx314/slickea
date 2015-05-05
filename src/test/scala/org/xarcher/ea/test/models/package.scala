package org.xarcher.ea.test

import org.h2.jdbcx.JdbcDataSource
import org.xarcher.ea.test.util.SlickUtil

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.language.existentials
import slick.collection.heterogeneous._
import scala.language.reflectiveCalls

import org.xarcher.ea.macros.JpaGenerate

package object models {

  val profile = slick.driver.H2Driver

  import profile.api._
  
  @JpaGenerate[Article](tableName = "macro_article_table")
  class GlobalArticleTable
  
  val globalArticleTable = TableQuery[GlobalArticleTable]

  val db = {
    val datasource = new JdbcDataSource()
    datasource.setUrl(s"jdbc:h2:mem:miao2333;DB_CLOSE_DELAY=-1")
    Database.forDataSource(datasource)
  }

  def h2SlickRun[R](a: DBIOAction[R, NoStream, Nothing]) = SlickUtil.dbRun(db, a)
  
}