package org.xarcher.ea.macros.jpa

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros
import scala.annotation.StaticAnnotation

/**
 * Created by djx314 on 15-5-16.
 */
class JpaJavaGenerate[T]extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro JpaJavaMacroImpl.impl
}

class JpaJavaMacroImpl(override val c: Context) extends JpaJavaModels {

  import c.universe._

  def impl(annottees: c.Expr[Any]*): c.Expr[Any] = annottees.map(_.tree) match {
    case (classDecl: ClassDef) :: Nil => {
      c.Expr(genCode(classDecl))
    }
    case decl => {
      c.abort(c.enclosingPosition, "Underlying class must not be top-level and without companion")
    }
  }

  protected def genCode(classDef: ClassDef) = {

    val q"$mods class $tpname[..$tparams] $ctorMods(...$paramss) extends { ..$earlydefns } with ..$parents { $self => ..$stats }" = classDef

    val colunmParams = columnInfos.map { case (key, annotations) => genColumnQ(key, annotations) }
    val typeAnnotationsTree = typeAnnotations.map(_.tree)

    val caseM =
      q"""@..$typeAnnotationsTree case class  $tpname(..$colunmParams)"""

    //println(caseM)
    caseM

  }

  def genColumnQ(key: String, annotations: List[MacroAnnoatation]) = {
    //val annotationsTree = annotations.map(s => q"""@${s.tree} @_root_.scala.annotation.meta.field""")
    val annoTree = annotations.map(s => {
      println(s.javaArgs)
      val tree = s.tree
      //val bb = q"""@(javax.persistence.Column@_root_.scala.annotation.meta.field)"""
      //println(bb)
    })
    val aa = q"""@(javax.persistence.Column@_root_.scala.annotation.meta.field)(name = "greger") val `${TermName(key)}`: String"""
    //val aa = q"""@..${annotations.map(s => { q"""(${s.tpe.typeSymbol.fullName}: @_root_.scala.annotation.meta.field)""" })} val `${TermName(key)}`: String"""
    println(aa)
    aa
  }

}