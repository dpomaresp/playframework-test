name := """vsware-event-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  javaJdbc,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.41",

  //Testing library's
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalatestplus" %% "play" % "1.4.0-M3" % "test",
  "org.mockito" % "mockito-core" % "1.10.19" % "test",
  "org.easymock" % "easymock" % "3.0" % "test",

  // Testing library for acceptance tests, conflicts occur without explicit httpcomponents imports
  "com.mashape.unirest" % "unirest-java" % "1.4.7" % "test",
  "org.apache.httpcomponents" % "httpcore" % "4.4.3" % "test",
  "org.apache.httpcomponents" % "httpclient" % "4.5" % "test"
)
