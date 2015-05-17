package org.xarcher.ea.macros.jpa

/**
 * Created by djx314 on 15-5-17.
 */

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros

trait JpaJavaModels {

  val c: Context
  import c.universe._

  lazy val (productType, annotationParams) = c.macroApplication match {
    case  q"new $annotationTpe[$paramTypeTree]().$method(..$methodParams)" =>
      (c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, Nil)
    case  q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" =>
      (c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, params)
  }

  lazy val columnInfos = {

    productType.decls.collect {
      case param: TermSymbol => {
        println(s"param.isAccessor:${param.isAccessor}")
        println(s"param.isMethod:${param.isMethod}")
        println(s"param.fullName:${param.fullName}")
        println(s"param.isGetter:${param.isGetter}")
        println(s"param.isSetter:${param.isSetter}")
        //println(param.isJava)
        println(param.annotations)

      }
    }

    //println(productType.members.mkString("\n"))

  }

}