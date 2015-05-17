package org.xarcher.ea.test.models

import javax.persistence.Column

import org.xarcher.ea.macros.jpa.JpaJavaGenerate
;

/**
 * Created by djx314 on 15-5-17.
 */

/*@JpaJavaGenerate[Servant]
class ScalaSimple*/

class aa {

  @Column(name = "aabbcc")
  private var aabb = 2

  def aabb_= { aabb = 3 }

}