name := "wechat-bg"

version := "2.6.0-SNAPSHOT"

scalaVersion := "2.12.7"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += evolutions

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.13"

libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.2"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test

ThisBuild / scalacOptions ++= List("-encoding", "utf8", "-deprecation", "-feature", "-unchecked", "-Xfatal-warnings")
ThisBuild / javacOptions ++= List("-Xlint:unchecked", "-Xlint:deprecation", "-Werror")
