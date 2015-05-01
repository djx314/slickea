package org.xarcher.ea.test.models

//import java.sql.Timestamp
//import java.util.Date
import org.xarcher.ea.test.base.SlickBase
import org.xarcher.ea.macros.table

trait MacroRepo extends SlickBase {

  import profile.api._
  
  def articleCreate = dbRun(articleTable.schema.create)
  def insertArticle(article: Article) = dbRun(articleTable += article)
  def insertArticles(articles: List[Article]) = dbRun(articleTable ++= articles)
  
  @table[Article](tableName = "macro_article_table")
  class ArticleTable

  val articleTable = TableQuery[ArticleTable]

}