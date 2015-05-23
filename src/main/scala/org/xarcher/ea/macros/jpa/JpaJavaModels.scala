package org.xarcher.ea.macros.jpa

/**
 * Created by djx314 on 15-5-17.
 */

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros
import collection.JavaConversions._

trait JpaJavaModels {

  val c: Context
  import c.universe._

  type JavaAnnotation = java.lang.annotation.Annotation
  type MacroAnnoatation = c.universe.Annotation

  println(c.macroApplication)

  lazy val (productType, annotationParams) = c.macroApplication match {
    case q"new $annotationTpe[$paramTypeTree]().$method(..$methodParams)" =>
      (c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, Nil)
    case q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" =>
      (c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, params)
  }

  lazy val columnInfos = {

    //val q"""$mods type $name[..$tparams] >: $low <: $high""" = internal.typeDef(productType.typeSymbol)
    def extractTermName(methodSymbol: Name) = {
      val TermName(s) = methodSymbol
      s
    }

    def methodNameDeal(name: String): String = {
      if (name startsWith "get") {
        val notGetName = name.replace("get", "")
        notGetName.zipWithIndex.map {
          case (s, 0) => s.toLower
          case (s, _) => s
        }.mkString
      } else if (name startsWith "set") {
        val notSetname = name.replace("set", "")
        notSetname.zipWithIndex.map {
          case (s, 0) => s.toLower
          case (s, _) => s
        }.mkString
      } else {
        throw new Exception(s"Method name: $name is not a getter or setter method.")
      }
    }

    val members = productType.members.toList

    val memGroups = members.groupBy{

      case methodSymbol: MethodSymbol if {
        val methodName = extractTermName(methodSymbol.name)
        (methodName startsWith "set") || (methodName startsWith "get")
      } => methodNameDeal(extractTermName(methodSymbol.name))

      case methodSymbol: MethodSymbol => extractTermName(methodSymbol.name)

      case termSymbol: TermSymbol => extractTermName(termSymbol.name)

    }


    val propsMap = memGroups.filter{ case (key, data) => {
      val names = data.map(_.name).map{ case TermName(s) => s }
      names.exists(_.startsWith("set")) || names.exists(_.startsWith("get"))
    } }

    for ((key, data) <- propsMap) yield {
      val annotations = data.flatMap(_.annotations)
      (key, annotations)
    }

  }

  lazy val typeAnnotations = productType.typeSymbol.annotations

}