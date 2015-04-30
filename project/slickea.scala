import sbt._
import Keys._
import cn.gov.heshan.sbt.CustomSettings
import com.typesafe.sbt.SbtGit._

object slickea extends Build {
  
  val welcomeString = """
welcome to build enuma elish !
       _   _          _                   
      | | (_)        | |                  
 ___  | |  _    ___  | | __   ___    __ _ 
/ __| | | | |  / __| | |/ /  / _ \  / _` |
\__ \ | | | | | (__  |   <  |  __/ | (_| |
|___/ |_| |_|  \___| |_|\_\  \___|  \__,_|
"""
  
  println(welcomeString)
  
  lazy val slickea = Project(

    id = "slickea",

    base = file("."),
    settings = Defaults.coreDefaultSettings

  )

  .settings(CustomSettings.customSettings: _*)
  
  .settings(addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full): _*)

  .enablePlugins(com.typesafe.sbt.GitBranchPrompt)

  .settings(

    name := "slickea",

    scalaVersion := "2.11.6",

    scalacOptions ++= Seq("-feature", "-deprecation"),

    libraryDependencies ++= Seq(

      //project dependencies
      "com.typesafe.slick" %% "slick" % "3.0.0-RC3",
      "org.scala-lang" % "scala-compiler" % scalaVersion.value,
      "org.hibernate" % "hibernate-entitymanager" % "5.0.0.Beta1",

      //test dependencies
      "com.typesafe.play" %% "play-json" % "2.3.8" % "test",
      "org.cvogt" %% "play-json-extensions" % "0.2" % "test",
      "mysql" % "mysql-connector-java" % "5.1.34" % "test",
      "com.github.tminglei" %% "slick-pg" % "0.9.0-beta" % "test",
      "org.joda" % "joda-convert" % "1.7" % "test",
      "com.vividsolutions" % "jts" % "1.13" % "test",
      "org.scalatest" %% "scalatest" % "2.2.4" % "test",
      "com.h2database" % "h2" % "1.4.187" % "test",
      "org.slf4j" % "slf4j-simple" % "1.7.12" % "test"

    ),

    dependencyOverrides ++= {
      Set(
      )
    }

  )

}