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

    val colunmParams = columnInfos map { case (key, annotations, returnType) => genColumnQ(key, annotations, returnType) }
    val typeAnnotationsTree = typeAnnotations map (_.tree)

    val caseM =
      q"""@..$typeAnnotationsTree case class  $tpname(..$colunmParams)"""

    //println(caseM)
    caseM

  }

  def genColumnQ(key: String, annotations: List[MacroAnnoatation], returnType: Type) = {

    val annoTree = annotations.map(s => {
      val tree = s.tree
      val annatationType = typeFromParamTree(tree)
      q"""new ($annatationType@_root_.scala.annotation.meta.field())(..${tree.children.tail})"""
    })

    q"""@..$annoTree val `${TermName(key)}`: $returnType"""

  }

}