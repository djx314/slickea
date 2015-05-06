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

  lazy val (productType, annotationParams) = c.macroApplication match {
    case  q"new $annotationTpe[$paramTypeTree]().$method(..$methodParams)" =>
      (c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, Nil)
    case  q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" =>
      (c.typecheck(paramTypeTree.duplicate, c.TYPEmode).tpe, params)
  }

  lazy val columnInfos = {

    productType.decls.collect {
      case param: TermSymbol if param.isCaseAccessor && (param.isVal || param.isVar) => {

        param.annotations.collectFirst{ case extr if extr.tree.tpe <:< c.weakTypeOf[javax.persistence.Column] => extr }
          .getOrElse(c.abort(c.enclosingPosition, "Every case accessor must be annotationed by javax.persistence.Column"))

        val columnNamesList = for {
          extr <- param.annotations if extr.tree.tpe <:< c.weakTypeOf[javax.persistence.Column]
          q"name = ${Literal(Constant(str: String))}" <- extr.tree.children.tail
        } yield str

        val columnName = columnNamesList.headOption.getOrElse(param.name.toString).trim
        val proName = param.name.toString.trim

        TableModels(
          propertyName = proName,
          propertyType = param.typeSignature,
          columnDefName = proName,
          columnName = columnName,
          extPro = Nil
        )

      }
    }

  }

  protected def hlistConcat[T: Liftable](elems: Iterable[T]) = {
    val HNil = q"_root_.slick.collection.heterogeneous.HNil": Tree
    elems.toList.reverse.foldLeft(HNil) { (list, c) =>
      q" `$c` :: $list "
    }
  }

  lazy val getTableName: String = {

    val paramTableName = for {
      q"$fname = $fv" <- annotationParams if fname.toString.trim == "tableName"
      Literal(Constant(tbName: String)) <- fv if tbName != ""
    } yield tbName

    paramTableName.headOption.getOrElse(productType.typeSymbol.name.decodedName.toString)

  }

}