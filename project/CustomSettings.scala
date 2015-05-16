package cn.gov.heshan.sbt

import sbt._
import Keys._
//import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseKeys

object CustomSettings {
  
  def customSettings = extAlias ++ resolversSettings ++ scalaSettings

  def scalaSettings = (scalaVersion := "2.11.6")
    .++(scalacOptions ++= Seq("-feature", "-deprecation"))
    .++(addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full))

  def resolversSettings =
    (resolvers ++= Seq(
      "mavenRepoJX" at "http://repo1.maven.org/maven2/",
      "bintray/non" at "http://dl.bintray.com/non/maven",
      Resolver.url("typesafe-ivy", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
    ))
    .++(externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false))
  
  def extAliasInfo = List(
    Option("xeclipse" -> "eclipse with-source=true skip-parents=false"),
    Option("sc" -> ";simpleTest/clean;model4Test/clean;slickea/clean;simpleTest/test:compile"),
    Option("st" -> ";simpleTest/clean;model4Test/clean;slickea/clean;simpleTest/test"),
    Option("fc" -> ";fullTest/clean;model4Test/clean;slickea/clean;fullTest/test:compile"),
    Option("ft" -> ";fullTest/clean;model4Test/clean;slickea/clean;fullTest/test"),
    if (OSName.isWindows)
      Option(windowsGitInitCommandMap)
    else if (OSName.isLinux)
      Option(linuxGitInitCommandMap)
    else None
  )
  
  def extAlias = extAliasInfo.collect { case Some(s) => s }
    .foldLeft(Nil: List[Def.Setting[_]]){ (s, t) => s ++ addCommandAlias(t._1, t._2) }
  
  //git init command
  val windowsGitInitCommandMap = "windowsGitInit" ->
    """|;
        |git config --global i18n.commitencoding utf-8;
        |git config --global i18n.logoutputencoding gbk;
        |git config --global core.autocrlf true;
        |git config core.editor \"extras/npp.6.5.1/startNote.bat\"
      """.stripMargin

  val linuxGitInitCommandMap = "linuxGitInit" ->
    """|;
        |git config --global i18n.commitencoding utf-8;
        |git config --global i18n.logoutputencoding utf-8;
        |git config --global core.autocrlf true;
        |git config core.editor gedit
      """.stripMargin

  def testDependencies = Seq(
    "com.typesafe.play" %% "play-json" % "2.3.8" % "test",
    "org.cvogt" %% "play-json-extensions" % "0.2" % "test",
    "mysql" % "mysql-connector-java" % "5.1.34" % "test",
    "com.github.tminglei" %% "slick-pg" % "0.9.0-beta" % "test",
    "org.joda" % "joda-convert" % "1.7" % "test",
    "com.vividsolutions" % "jts" % "1.13" % "test",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "com.h2database" % "h2" % "1.4.187" % "test",
    "org.slf4j" % "slf4j-simple" % "1.7.12" % "test"
  )

  def jpaDependencies = Seq(
    "org.hibernate" % "hibernate-entitymanager" % "5.0.0.Beta1"
  )

}
