package org.xarcher.ea.test.models

import slick.collection.heterogeneous._
import java.sql.Timestamp
import java.util.Date

trait Repo {

  val profile: slick.driver.JdbcProfile
  import profile.api._
  
  val db: Database
  
  val articleTable = TableQuery[ArticleTable]

  class ArticleTable(tag: Tag) extends Table[Article](tag, "article") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def account = column[Option[Long]]("account")
    def userType = column[String]("user_type")
    def ArticleID = column[Option[Long]]("ArticleID")
    def ChannelID = column[Long]("ChannelID")
    def ClassID = column[Long]("ClassID")
    def Title = column[String]("Title")
    def TitleIntact = column[Option[String]]("TitleIntact")
    def Subheading = column[Option[String]]("Subheading")
    def Author = column[String]("Author")
    def CopyFrom = column[String]("CopyFrom")
    def Inputer = column[String]("Inputer")
    def LinkUrl = column[Option[String]]("LinkUrl")
    def Editor = column[String]("Editor")
    def Keyword = column[String]("Keyword")
    def Hits = column[Int]("Hits")
    def CommentCount = column[Int]("CommentCount")
    def UpdateTime = column[Timestamp]("UpdateTime")
    def CreateTime = column[Timestamp]("CreateTime")
    def OnTop = column[Boolean]("OnTop")
    def Elite = column[Boolean]("Elite")
    def Status = column[Int]("Status")
    def Content = column[String]("Content")
    def IncludePic = column[Int]("IncludePic")
    def DefaultPicUrl = column[Option[String]]("DefaultPicUrl")
    def UploadFiles = column[Option[String]]("UploadFiles")
    def InfoPoint = column[Int]("InfoPoint")
    def PaginationType = column[Int]("PaginationType")
    def Deleted = column[Boolean]("Deleted")
    def SkinID = column[Int]("SkinID")
    def TemplateID = column[Int]("TemplateID")
    def Stars = column[Int]("Stars")
    def TitleFontColor = column[Option[String]]("TitleFontColor")
    def TitleFontType = column[Int]("TitleFontType")
    def MaxCharPerPage = column[Int]("MaxCharPerPage")
    def ShowCommentLink = column[Boolean]("ShowCommentLink")
    def Receive = column[Boolean]("Receive")
    def ReceiveUser = column[Option[String]]("ReceiveUser")
    def Received = column[Option[String]]("Received")
    def AutoReceiveTime = column[Int]("AutoReceiveTime")
    def ReceiveType = column[Int]("ReceiveType")
    def Intro = column[Option[String]]("Intro")
    def PresentExp = column[Int]("PresentExp")
    def Copymoney = column[String]("Copymoney")
    def IsPayed = column[Boolean]("IsPayed")
    def Beneficiary = column[Option[String]]("Beneficiary")
    def PayDate = column[Option[Timestamp]]("PayDate")
    def VoteID = column[Int]("VoteID")
    def InfoPurview = column[Int]("InfoPurview")
    def arrGroupID = column[Option[String]]("arrGroupID")
    def ChargeType = column[Int]("ChargeType")
    def PitchTime = column[Int]("PitchTime")
    def ReadTimes = column[Int]("ReadTimes")
    def DividePercent = column[Int]("DividePercent")
    def BlogID = column[Int]("BlogID")
    def NewsID = column[Option[Long]]("NewsID")
   
    def * =
      (
        id ::
        account ::
        userType ::
        ArticleID ::
        ChannelID ::
        ClassID ::
        Title ::
        TitleIntact ::
        Subheading ::
        Author ::
        CopyFrom ::
        Inputer ::
        LinkUrl ::
        Editor ::
        Keyword ::
        Hits ::
        CommentCount ::
        UpdateTime ::
        CreateTime ::
        OnTop ::
        Elite ::
        Status ::
        Content ::
        IncludePic ::
        DefaultPicUrl ::
        UploadFiles ::
        InfoPoint ::
        PaginationType ::
        Deleted ::
        SkinID ::
        TemplateID ::
        Stars ::
        TitleFontColor ::
        TitleFontType ::
        MaxCharPerPage ::
        ShowCommentLink ::
        Receive ::
        ReceiveUser ::
        Received ::
        AutoReceiveTime ::
        ReceiveType ::
        Intro ::
        PresentExp ::
        Copymoney ::
        IsPayed ::
        Beneficiary ::
        PayDate ::
        VoteID ::
        InfoPurview ::
        arrGroupID ::
        ChargeType ::
        PitchTime ::
        ReadTimes ::
        DividePercent ::
        BlogID ::
        NewsID ::
        HNil
      ).shaped <> (
        { case x => Article(
          x(0),
          x(1),
          x(2),
          x(3),
          x(4),
          x(5),
          x(6),
          x(7),
          x(8),
          x(9),
          x(10),
          x(11),
          x(12),
          x(13),
          x(14),
          x(15),
          x(16),
          x(17),
          x(18),
          x(19),
          x(20),
          x(21),
          x(22),
          x(23),
          x(24),
          x(25),
          x(26),
          x(27),
          x(28),
          x(29),
          x(30),
          x(31),
          x(32),
          x(33),
          x(34),
          x(35),
          x(36),
          x(37),
          x(38),
          x(39),
          x(40),
          x(41),
          x(42),
          x(43),
          x(44),
          x(45),
          x(46),
          x(47),
          x(48),
          x(49),
          x(50),
          x(51),
          x(52),
          x(53),
          x(54),
          x(55)
        )}, ({ x: Article =>
          Option((
            x.id ::
            x.account ::
            x.userType ::
            x.ArticleID ::
            x.ChannelID ::
            x.ClassID ::
            x.Title ::
            x.TitleIntact ::
            x.Subheading ::
            x.Author ::
            x.CopyFrom ::
            x.Inputer ::
            x.LinkUrl ::
            x.Editor ::
            x.Keyword ::
            x.Hits ::
            x.CommentCount ::
            x.UpdateTime ::
            x.CreateTime ::
            x.OnTop ::
            x.Elite ::
            x.Status ::
            x.Content ::
            x.IncludePic ::
            x.DefaultPicUrl ::
            x.UploadFiles ::
            x.InfoPoint ::
            x.PaginationType ::
            x.Deleted ::
            x.SkinID ::
            x.TemplateID ::
            x.Stars ::
            x.TitleFontColor ::
            x.TitleFontType ::
            x.MaxCharPerPage ::
            x.ShowCommentLink ::
            x.Receive ::
            x.ReceiveUser ::
            x.Received ::
            x.AutoReceiveTime ::
            x.ReceiveType ::
            x.Intro ::
            x.PresentExp ::
            x.Copymoney ::
            x.IsPayed ::
            x.Beneficiary ::
            x.PayDate ::
            x.VoteID ::
            x.InfoPurview ::
            x.arrGroupID ::
            x.ChargeType ::
            x.PitchTime ::
            x.ReadTimes ::
            x.DividePercent ::
            x.BlogID ::
            x.NewsID ::
            HNil
          ))
        })
      )
  }
  
}