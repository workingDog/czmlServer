sbtPlugin := true

name := "czmlServer"

organization := "com.kodekutters"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.1",
  "com.typesafe.play" % "play-json_2.11" % "2.5.0-M1",
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "2.0.2",
  "com.typesafe.akka" % "akka-stream-experimental_2.11" % "2.0.2"
)
