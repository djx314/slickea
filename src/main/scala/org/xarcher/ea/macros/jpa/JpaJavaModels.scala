package org.xarcher.ea.macros.jpa

/**
 * Created by djx314 on 15-5-17.
 */

import org.xarcher.ea.macros.common.MacroUtils

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros

trait JpaJavaModels extends MacroUtils {

  val c: Context
  import c.universe._

  type JavaAnnotation = java.lang.annotation.Annotation
  type MacroAnnoatation = c.universe.Annotation

  lazy val (productType, annotationParams) = c.macroApplication match {
    case q"new $annotationTpe[$paramTypeTree]().$method(..$methodParams)" =>
      (typeFromParamTree(paramTypeTree), Nil)
    case q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" =>
      (typeFromParamTree(paramTypeTree), params)
  }

  lazy val columnInfos = {

    //val q"""$mods type $name[..$tparams] >: $low <: $high""" = internal.typeDef(productType.typeSymbol)

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


    val infoMap = memGroups.filter{ case (key, data) => {
      val names = data.map(_.name).map{ case TermName(s) => s }
      names.exists(_.startsWith("set")) && names.exists(_.startsWith("get"))
    } }

    val propsMap = infoMap.map {
      case (key, data) => {

        val getMethod = data.find(s => {
          extractTermName(s.name: Name) startsWith "get"
        })

        val returnType = getMethod.get.asMethod.returnType
        val dealedReturnType = modelTypeMap get returnType getOrElse returnType
        (key, data, dealedReturnType)

      }
    }

    for ((key, data, returnType) <- propsMap) yield {
      val annotations = data.flatMap(_.annotations)
      (key, annotations, returnType)
    }

  }

  lazy val typeAnnotations = productType.typeSymbol.annotations

  val modelTypeMap = Map(
    weakTypeOf[java.lang.Byte] -> weakTypeOf[scala.Byte],
    weakTypeOf[java.lang.Short] -> weakTypeOf[scala.Short],
    weakTypeOf[java.lang.Integer] -> weakTypeOf[scala.Int],
    weakTypeOf[java.lang.Long] -> weakTypeOf[scala.Long],
    weakTypeOf[java.lang.Character] -> weakTypeOf[scala.Char],
    weakTypeOf[java.lang.Float] -> weakTypeOf[scala.Float],
    weakTypeOf[java.lang.Double] -> weakTypeOf[scala.Double],
    weakTypeOf[java.lang.Boolean] -> weakTypeOf[scala.Boolean]
  )

}