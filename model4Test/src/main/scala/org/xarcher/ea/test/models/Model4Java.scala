package org.xarcher.ea.test.models

import org.xarcher.ea.macros.jpa.JpaJavaGenerate

/**
 * Created by djx314 on 15-5-17.
 */

class aa {

  @javax.persistence.Column(name = "aabbcc")
  private var aabb = 2

  def ccdd_= { aabb = 3 }

  val eeff = 2333

}

@JpaJavaGenerate[Servant]
class ScalaServant