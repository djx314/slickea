package org.xarcher.ea.test.models

import slick.collection.heterogeneous._
import java.sql.Timestamp
import java.util.Date
import org.xarcher.ea.test.base.SlickBase
import org.xarcher.ea.macros.table

trait MacroRepo extends SlickBase {

  import profile.api._
  
  def articleCreate = dbRun(ArticleTable.schema.create)
  def insertArticle(article: Article) = dbRun(ArticleTable += article)
  def insertArticles(articles: List[Article]) = dbRun(ArticleTable ++= articles)
  
  @table[Article](tableName = "macro_article_table")
  class ArticleTable

}