package org.xarcher.ea.jpa.macros

import scala.reflect.macros.blackbox.Context
import scala.reflect._
import scala.language.experimental.macros
import scala.reflect.runtime.universe._
/**
 * Created by djx314 on 2015/5/5.
 */

trait GenerateColunm {

  val c: Context
  import c.universe._

  case class TableModels(
    propertyName: String,
    propertyType: Type,
    columnDefName: String,
    columnName: String,
    extPro: List[String] = Nil
  )

  lazy val colunmsInfos = extractColunmInfo

  def extractColunmInfo = {

    val q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" = c.macroApplication

    val productType11 = c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe

    productType11.decls.collect {
      case param: TermSymbol if param.isCaseAccessor && (param.isVal || param.isVar) => {
      //case param if param.isMethod && param.asMethod.isCaseAccessor => {
        val columnNamesList = for {
          extr <- param.annotations if extr.tree.tpe <:< c.weakTypeOf[javax.persistence.Column]
          q"name = ${Literal(Constant(str: String))}" <- extr.tree.children.tail
        } yield str

        val columnName = columnNamesList.headOption.getOrElse(param.name.toString).trim

        TableModels(
          propertyName = param.name.toString.trim,
          propertyType = param.typeSignature,
          columnDefName = columnName,
          columnName = columnName,
          extPro = Nil
        )
      }
    }

  }

  def genColunms = {
    colunmsInfos.map(s => {
      q"""
         def `${TermName(s.columnDefName)}` = column[${s.propertyType}](${s.columnName})
        """
    })
  }

  protected def productType = {
    val typeTree =  c.macroApplication match {
      case  q"new $annotationTpe[$paramTypeTree]().$method(..$methodParams)" => {
        paramTypeTree
      }
      case  q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" => {
        paramTypeTree
      }
    }

    c.typecheck(typeTree.duplicate, c.TYPEmode).tpe
  }

  def hlistConcat[T: Liftable](elems: Iterable[T]) = {
    val HNil = q"_root_.slick.collection.heterogeneous.HNil": Tree
    elems.toList.reverse.foldLeft(HNil) { (list, c) =>
      q"`$c` :: $list"
    }
  }

  def genHListMapping = {

    val hlist = hlistConcat(colunmsInfos.map(s => TermName(s.columnDefName)))

    val columnElems = (0 until colunmsInfos.size).map(i => q"x($i)")
    val productHList = hlistConcat(colunmsInfos.map(n =>q"x.${TermName(n.propertyName)}"))
    val toProduct = q"{case x => new $productType(..$columnElems)}"
    val fromProduct = q"{x: $productType => Option($productHList)}"
    q"def * = ($hlist).shaped <> ($toProduct, $fromProduct)"

  }

}