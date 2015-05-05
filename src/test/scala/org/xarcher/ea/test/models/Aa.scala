package org.xarcher.ea.test.models

import org.xarcher.ea.macros.JpaGenerate
import scala.language.higherKinds

trait Aa {

  val profile: slick.driver.JdbcProfile
  import profile.api._

  @JpaGenerate[Simpleaaa](tableName = "dshreger")
  class Miaode

}