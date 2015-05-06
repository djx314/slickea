package org.xarcher.ea.jpa.macros

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros
import scala.annotation.StaticAnnotation

class JpaGenerate[T](tableName: String = "") extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro JpaTableMacroImpl.impl
}

class JpaTableMacroImpl(override val c: Context) extends GenerateColunm {

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

  protected def genColunms =
    columnInfos.map(s => {
      q"""
         def `${TermName(s.columnDefName)}` = column[${s.propertyType}](${s.columnName})
        """
    })

  protected def genHListMapping = {

    val hlist = hlistConcat(columnInfos.map(s => TermName(s.columnDefName)))

    val columnElems = (0 until columnInfos.size).map(i => q"x($i)")
    val toProduct = q"{ case x => new $productType(..$columnElems) }"

    val productHList = hlistConcat(columnInfos.map(n =>q"x.${TermName(n.propertyName)}"))

    val fromProduct = q"{ x: $productType => Option($productHList) }"

    q"def * = ($hlist).shaped <> ($toProduct, $fromProduct)"

  }

}