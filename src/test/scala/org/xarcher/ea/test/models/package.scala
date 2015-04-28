package org.xarcher.ea.test

import scala.language.existentials
import slick.collection.heterogeneous._
import scala.language.reflectiveCalls

package object models {

  val profile = slick.driver.H2Driver
  import profile.api._
  
  val userTable = {
    
    case class User(
      id: Option[Long],
      userName: String,
      userAge: Int
    )
    
    class UserTable(tag: Tag) extends Table[User](tag, "User") {
      
      def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
      def userName = column[String]("userName")
      def userAge = column[Int]("userAge")
      
      def * =
      (
        id ::
        userName ::
        userAge ::
        HNil
      ).shaped <> (
        { case x => User(
          x(0),
          x(1),
          x(2)
        )}, ({ x: User =>
          Option((
            x.id ::
            x.userName ::
            x.userAge ::
            HNil
          ))
        })
      )
    }
    
    TableQuery[UserTable]
    
  }
  
  println(userTable.shaped)

  //def getTable[T](table: TableQuery[T]) = table#TableElementType
  //type UserTable = userTable.type#TableElementType
  
  //userTable.filter((s: UserTable) => s.id > 2.toLong)
  
}