package org.xarcher.ea.macros.jpa

import org.xarcher.ea.macros.common.MacroUtils

import scala.reflect.macros.blackbox.Context
import scala.reflect._
import scala.language.experimental.macros
import scala.reflect.runtime.universe._
/**
 * Created by djx314 on 2015/5/5.
 */

trait JpaTableModels extends MacroUtils {

  val c: Context
  import c.universe._

  case class TableModels(
    propertyName: String,
    propertyType: Type,
    columnDefName: String,
    columnName: String,
    extPro: List[Tree] = Nil
  )

  lazy val (productType, annotationParams) = c.macroApplication match {
    case  q"new $annotationTpe[$paramTypeTree]().$method(..$methodParams)" =>
      (typeFromParamTree(paramTypeTree), Nil)
    case  q"new $annotationTpe[$paramTypeTree](..$params).$method(..$methodParams)" =>
      (typeFromParamTree(paramTypeTree), params)
  }

  lazy val columnInfos = {

    productType.decls.collect {
      case param: TermSymbol if param.isCaseAccessor && (param.isVal || param.isVar) => {

        val columnNamesList = for {
          extr <- param.annotations if extr.tree.tpe <:< c.weakTypeOf[javax.persistence.Column]
          q"name = ${Literal(Constant(str: String))}" <- extr.tree.children.tail
        } yield str

        val columnName = columnNamesList.headOption.getOrElse(param.name.toString).trim
        val proName = param.name.toString.trim

        val primaryKey = param.annotations.collectFirst{ case s if s.tree.tpe <:< c.weakTypeOf[javax.persistence.Id] => {
          q"O.PrimaryKey": Tree
        } }

        val autoInc = if (primaryKey.isDefined) Option(q"O.AutoInc": Tree) else None

        val extParams = List(primaryKey, autoInc).collect { case Some(s) => s }

        TableModels(
          propertyName = proName,
          propertyType = param.typeSignature,
          columnDefName = proName,
          columnName = columnName,
          extPro = extParams
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