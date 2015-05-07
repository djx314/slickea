package org.xarcher.ea.test.models

import java.sql.Timestamp
import java.util.Date
import javax.persistence.{Entity, Column, Id}
import scala.annotation.meta.field

case class Simple(
  @(Id@field)
  @(Column@field)(name = "id")
  id: Option[Long],
  @(Column@field)(name = "namenimei")
  name: String,
  @(Column@field)(name = "bbbbbbvcegehrth")
  nick: Option[String]
)

@Entity
case class Article(
  @(Id@field)
  @(Column@field)(name = "id")
  id: Option[Long],
  @(Column@field)(name = "account")
  account: Option[Long],
  @(Column@field)(name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
  userType: String,
  @(Column@field)
  ArticleID: Option[Long] = Option(2333.toLong),
  @(Column@field)
  ChannelID: Long = 6666,
  @(Column@field)
  ClassID: Long = 9527,
  @(Column@field)
  Title: String = "sdgfgsr",
  @(Column@field)
  TitleIntact: Option[String] = Option("泠鸢yousa"),
  @(Column@field)
  Subheading: Option[String] = Option("小缘"),
  @(Column@field)
  Author: String = "音频怪物",
  @(Column@field)
  CopyFrom: String = "夏目友人帿",
  @(Column@field)
  Inputer: String = "fate stay night",
  @(Column@field)
  LinkUrl: Option[String] = Option("archer"),
  @(Column@field)
  Editor: String = "grehghtrytjy",
  @(Column@field)
  Keyword: String = "grehtjyukjuy",
  @(Column@field)
  Hits: Int = 9527,
  @(Column@field)
  CommentCount: Int = 2333,
  @(Column@field)
  UpdateTime: Timestamp = new Timestamp(new Date().getTime),
  @(Column@field)
  CreateTime: Timestamp = new Timestamp(new Date().getTime),
  @(Column@field)
  OnTop: Boolean = true,
  @(Column@field)
  Elite: Boolean = false,
  @(Column@field)
  Status: Int = 9527,
  @(Column@field)
  Content: String = "435ghjtyjty",
  @(Column@field)
  IncludePic: Int = 2333,
  @(Column@field)
  DefaultPicUrl: Option[String] = Option("fate zero"),
  @(Column@field)
  UploadFiles: Option[String] = Option("魔法少女小缘"),
  @(Column@field)
  InfoPoint: Int = 6666,
  @(Column@field)
  PaginationType: Int = 2333,
  @(Column@field)
  Deleted: Boolean = false,
  @(Column@field)
  SkinID: Int = 2333,
  @(Column@field)
  TemplateID: Int = 2333,
  @(Column@field)
  Stars: Int = 9527,
  @(Column@field)
  TitleFontColor: Option[String] = Option("英莉莿"),
  @(Column@field)
  TitleFontType: Int = 2333,
  @(Column@field)
  MaxCharPerPage: Int = 9527,
  @(Column@field)
  ShowCommentLink: Boolean = true,
  @(Column@field)
  Receive: Boolean = false,
  @(Column@field)
  ReceiveUser: Option[String] = Option("桐人"),
  @(Column@field)
  Received: Option[String] = Option("能干的妹妿"),
  @(Column@field)
  AutoReceiveTime: Int = 6666,
  @(Column@field)
  ReceiveType: Int = 2333,
  @(Column@field)
  Intro: Option[String] = Option("阿斯娿"),
  @(Column@field)
  PresentExp: Int = 9527,
  @(Column@field)
  Copymoney: String = "喵的",
  @(Column@field)
  IsPayed: Boolean = false,
  @(Column@field)
  Beneficiary: Option[String] = Option("小缘"),
  @(Column@field)
  PayDate: Option[Timestamp] = Option(new Timestamp(new Date().getTime)),
  @(Column@field)
  VoteID: Int = 2333,
  @(Column@field)
  InfoPurview: Int = 2333,
  @(Column@field)
  arrGroupID: Option[String] = Option("泠鸢yousa"),
  @(Column@field)
  ChargeType: Int = 2333,
  @(Column@field)
  PitchTime: Int = 2333,
  @(Column@field)
  ReadTimes: Int = 2333,
  @(Column@field)
  DividePercent: Int = 2333,
  @(Column@field)
  BlogID: Int = 2333,
  @(Column@field)
  NewsID: Option[Long] = Option(2333.toLong)
)