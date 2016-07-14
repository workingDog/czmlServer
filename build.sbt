sbtPlugin := true

name := "czmlServer"

organization := "com.github.workingDog"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.github.workingDog" %% "scalaczml" % "0.2",
  "com.typesafe.play" % "play-json_2.11" % "2.5.4",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-stream_2.11" % "2.4.8",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5"
)
