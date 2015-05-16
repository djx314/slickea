package org.xarcher.ea.test.models

import org.xarcher.ea.macros.jpa.JpaTableGenerate

import profile.api._

@JpaTableGenerate[Article](tableName = "macro_article_table")
class GlobalArticleTable