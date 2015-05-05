package org.xarcher.ea.test.models

import javax.persistence.{Column, Id}
import org.xarcher.ea.macros.JpaGenerate

/**
 * Created by djx314 on 2015/5/5.
 */

case class Simpleaaa(
  @Id
  @Column(name = "id")
  id: Option[Long],
  @Column(name = "namenimei")
  name: String,
  @Column(name = "miaode")
  nick: Option[String]
)