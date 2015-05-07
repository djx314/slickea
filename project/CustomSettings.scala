package cn.gov.heshan.sbt

import sbt._
import Keys._
//import com.typesafe.sbteclipse.plugin.EclipsePlugin.EclipseKeys

object CustomSettings {
  
  def customSettings = extAlias ++ resolversSettings
  
  def resolversSettings =
    (resolvers ++= Seq(
      "mavenRepoJX" at "http://repo1.maven.org/maven2/",
      "bintray/non" at "http://dl.bintray.com/non/maven",
      Resolver.url("typesafe-ivy", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
    ))
    .++(externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false))
  
  def extAliasInfo = List(
    Option("xeclipse" -> "eclipse with-source=true skip-parents=false"),
    Option("c" -> ";simTest/clean;slickea/clean;slickea/test:compile"),
    Option("t" -> ";simTest/clean;slickea/clean;slickea/test"),
    Option("fc" -> ";fullTest/clean;simTest/clean;slickea/clean;fullTest/test:compile"),
    Option("ft" -> ";fullTest/clean;simTest/clean;slickea/clean;fullTest/test"),
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
  
}
