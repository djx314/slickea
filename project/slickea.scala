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
  .enablePlugins(com.typesafe.sbt.GitBranchPrompt)

  .settings(
    name := "slickea",

    libraryDependencies ++= Seq(
      //project dependencies
      "com.typesafe.slick" %% "slick" % "3.0.0-RC3",
      "org.scala-lang" % "scala-compiler" % scalaVersion.value
    ),
    libraryDependencies ++= CustomSettings.jpaDependencies,

    dependencyOverrides ++= {
      Set(
      )
    }
  )

  lazy val model4Test = Project(
    id = "model4Test",
    base = file("./model4Test"),
    settings = Defaults.coreDefaultSettings
  )
  .settings(CustomSettings.customSettings: _*)
  .settings(
    name := "model4Test",
    libraryDependencies ++= CustomSettings.jpaDependencies
  )

  lazy val slickeaSimpleTest = Project(
    id = "simpleTest",
    base = file("./simpleTest"),
    settings = Defaults.coreDefaultSettings
  )
  .settings(
    name := "simpleTest",
    libraryDependencies ++= CustomSettings.testDependencies
  )
  .settings(CustomSettings.customSettings: _*) dependsOn slickea dependsOn model4Test

  lazy val slickeaFullTest = Project(
    id = "fullTest",
    base = file("./fullTest"),
    settings = Defaults.coreDefaultSettings
  )
  .settings(
    name := "fullTest",
    libraryDependencies ++= CustomSettings.testDependencies
  )
  .settings(CustomSettings.customSettings: _*) dependsOn slickea dependsOn model4Test

}