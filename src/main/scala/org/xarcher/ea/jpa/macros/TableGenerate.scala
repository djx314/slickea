package org.xarcher.ea.jpa.macros

import scala.reflect.macros.blackbox.Context
import scala.reflect._
import scala.language.experimental.macros
import scala.annotation.StaticAnnotation

class JpaGenerate[T](tableName: String = "") extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro TableMacroImpl.impl
}

class TableMacroImpl(override val c: Context) extends GenerateColunm {

  import c.universe._

  def impl(annottees: c.Expr[Any]*): c.Expr[Any] = annottees.map(_.tree) match {
    case (classDecl: ClassDef) :: Nil => {
      c.Expr(genCode(classDecl))
    }
    case decl => {
      c.abort(c.enclosingPosition, "Underlying class must not be top-level and without companion")
    }
  }

  private def genCode(classDef: ClassDef) = {
    val q"$mods class $tpname[..$tparams] $ctorMods(...$paramss) extends { ..$earlydefns } with ..$parents { $self => ..$stats }" = classDef
    val tableName = Literal(Constant(getTableName))
    val mapping = genHListMapping
    val tableQ = q"""
      $mods class $tpname(tag: Tag) extends Table[${productType}](tag, $tableName) {
        ..$stats
        ..$genColunms
        $mapping
      }
      """
    tableQ
  }

  private def getTableName: String = {

    val params =  c.macroApplication match {
      case  q"new $annotationTpe[$paramTypeTree]().$method(..$methodParams)" =>
        Nil
      case  q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" =>
        params
    }

    def decodeAnnotateParam(param: Tree) = {
      val q"$fname = $fv" = param
      fname.toString -> fv
    }
    params.map(decodeAnnotateParam(_)).collectFirst {
      case ("tableName", Literal(Constant(name: String))) if name != "" => {
        name
      }
    }.getOrElse(snakify(productType.typeSymbol.name.decodedName.toString))
  }

  private def snakify(name: String) = {
    "([a-z\\d])([A-Z])".r.replaceAllIn(name, "$1_$2").toLowerCase
  }

}