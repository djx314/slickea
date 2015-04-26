import sbt._
import Keys._
import cn.gov.heshan.sbt.CustomSettings
import com.typesafe.sbt.SbtGit._

object slickea extends Build {
  
  lazy val slickea = Project(

    id = "slickea",

    base = file("."),
    settings = Defaults.coreDefaultSettings

  )

  .settings(CustomSettings.customSettings: _*)

  .settings(

    name := "slickea",

    scalaVersion := "2.11.6",

    scalacOptions ++= Seq("-feature", "-deprecation"),

    libraryDependencies ++= Seq(

      //project dependencies
      "com.typesafe.slick" %% "slick" % "3.0.0-RC3",
      "org.scala-lang" % "scala-compiler" % scalaVersion.value,

      //test dependencies
      "com.typesafe.play" %% "play-json" % "2.3.8" % "test",
      "org.cvogt" %% "play-json-extensions" % "0.2" % "test",
      "mysql" % "mysql-connector-java" % "5.1.34" % "test",
      "com.github.tminglei" %% "slick-pg" % "0.9.0-beta" % "test",
      "org.joda" % "joda-convert" % "1.7" % "test",
      "com.vividsolutions" % "jts" % "1.13" % "test"

    ),

    dependencyOverrides ++= {
      Set(
      )
    }

  )

}
