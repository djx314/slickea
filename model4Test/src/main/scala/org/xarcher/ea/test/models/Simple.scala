package org.xarcher.ea.test.models

import java.sql.Timestamp
import java.util.Date
import javax.persistence.{Table, Entity, Column, Id}
import scala.annotation.meta.field

@Entity
@Table(name = "it_s_so_simple")
case class Simple(
  @(Id@field)
  id: Option[Long],
  @(Column@field)(name = "namenimei")
  name: String,
  @(Column@field)(name = "bbbbbbvcegehrth")
  nick: Option[String]
)

@Entity
case class Article(
  @(Id@field)
  id: Option[Long],
  account: Option[Long],
  @(Column@field)(name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
  userType: String,
  @(Column@field)(name = "article_id")
  ArticleID: Option[Long] = Option(2333.toLong),
  ChannelID: Long = 6666,
  ClassID: Long = 9527,
  Title: String = "sdgfgsr",
  TitleIntact: Option[String] = Option("泠鸢yousa"),
  Subheading: Option[String] = Option("小缘"),
  Author: String = "音频怪物",
  CopyFrom: String = "夏目友人帿",
  Inputer: String = "fate stay night",
  LinkUrl: Option[String] = Option("archer"),
  Editor: String = "grehghtrytjy",
  Keyword: String = "grehtjyukjuy",
  Hits: Int = 9527,
  CommentCount: Int = 2333,
  UpdateTime: Timestamp = new Timestamp(new Date().getTime),
  CreateTime: Timestamp = new Timestamp(new Date().getTime),
  OnTop: Boolean = true,
  Elite: Boolean = false,
  Status: Int = 9527,
  Content: String = "435ghjtyjty",
  IncludePic: Int = 2333,
  DefaultPicUrl: Option[String] = Option("fate zero"),
  UploadFiles: Option[String] = Option("魔法少女小缘"),
  InfoPoint: Int = 6666,
  PaginationType: Int = 2333,
  Deleted: Boolean = false,
  SkinID: Int = 2333,
  TemplateID: Int = 2333,
  Stars: Int = 9527,
  TitleFontColor: Option[String] = Option("英莉莿"),
  TitleFontType: Int = 2333,
  MaxCharPerPage: Int = 9527,
  ShowCommentLink: Boolean = true,
  Receive: Boolean = false,
  ReceiveUser: Option[String] = Option("桐人"),
  Received: Option[String] = Option("能干的妹妿"),
  AutoReceiveTime: Int = 6666,
  ReceiveType: Int = 2333,
  Intro: Option[String] = Option("阿斯娿"),
  PresentExp: Int = 9527,
  Copymoney: String = "喵的",
  IsPayed: Boolean = false,
  Beneficiary: Option[String] = Option("小缘"),
  PayDate: Option[Timestamp] = Option(new Timestamp(new Date().getTime)),
  VoteID: Int = 2333,
  InfoPurview: Int = 2333,
  arrGroupID: Option[String] = Option("泠鸢yousa"),
  ChargeType: Int = 2333,
  PitchTime: Int = 2333,
  ReadTimes: Int = 2333,
  DividePercent: Int = 2333,
  BlogID: Int = 2333,
  NewsID: Option[Long] = Option(2333.toLong)
)