package org.xarcher.ea.test.models

import org.xarcher.ea.jpa.macros.JpaGenerate

import profile.api._

@JpaGenerate[Article](tableName = "macro_article_table")
class GlobalArticleTable