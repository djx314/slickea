package org.xarcher.ea.test.models

import org.xarcher.ea.test.base.SlickBase
import org.xarcher.ea.jpa.macros.JpaGenerate

trait MacroRepo extends SlickBase {

  import profile.api._
  
  def articleCreate = dbRun(articleTable.schema.create)
  def insertArticle(article: Article) = dbRun(articleTable += article)
  def insertArticles(articles: List[Article]) = dbRun(articleTable ++= articles)
  
  @JpaGenerate[Article](tableName = "macro_article_table")
  class ArticleTable

  val articleTable = TableQuery[ArticleTable]

}