package org.xarcher.ea.test.models

import javax.persistence.{Column, Id}
import scala.annotation.meta.field

case class Simple(
  @(Id@field)
  @(Column@field)(name = "id")
  id: Option[Long],
  @(Column@field)(name = "namenimei")
  name: String,
  @(Column@field)(name = "bbbbbbvcegehrth")
  nick: Option[String]
)