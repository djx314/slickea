package org.xarcher.ea.test.models

import org.xarcher.ea.macros.jpa.JpaGenerate

import profile.api._

@JpaGenerate[Article](tableName = "macro_article_table")
class GlobalArticleTable