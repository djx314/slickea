package org.xarcher.ea.macros.jpa

/**
 * Created by djx314 on 15-5-17.
 */

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros
import collection.JavaConversions._

trait JpaJavaModels {

  type JavaAnnotation = java.lang.annotation.Annotation

  val c: Context
  import c.universe._

  case class ClassInfo(
    annotations: List[JavaAnnotation]
  )

  case class AnnotationInfo(
    fullName: String,
    info: List[AnnotationProInfo]
  )

  case class AnnotationProInfo(
    propertyName: String,
    value: Any
  )

  lazy val (productType, annotationParams) = c.macroApplication match {
    case q"new $annotationTpe[$paramTypeTree]().$method(..$methodParams)" =>
      (c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, Nil)
    case q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" =>
      (c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, params)
  }

  lazy val (aa, bb) = c.macroApplication match {
    case q"new $annotationTpe[$paramTypeTree]().$method(..$methodParams)" =>
      //(c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, Nil)
      (paramTypeTree, Nil)
    case q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" =>
      //(c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, params)
      (paramTypeTree, methodParams)
  }

  lazy val columnInfos = {
    val q"""$mods type $name[..$tparams] >: $low <: $high""" = internal.typeDef(productType.typeSymbol)
    //println(productType.declarations.map(_.annotations))
    val classSymbol = productType.typeSymbol.asClass

    //val q"$cmods class $cpname[..$cparams] $ctorMods(...$paramss) extends { ..$earlydefns } with ..$parents { $self => ..$stats }" = productType.typeSymbol.tpe

    //println(low)
    //println(high)
  }

  lazy val typeAnnotations = productType.typeSymbol.annotations

}