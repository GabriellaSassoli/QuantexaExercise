name := "QuantexaExercise"

version := "0.1"

scalaVersion := "2.13.8"

val ScalaLogVersion   = "3.9.2"
val scalaTestVersion  = "3.0.8"


libraryDependencies ++= Seq(
"com.typesafe.scala-logging"   %% "scala-logging"        % ScalaLogVersion,
  "org.scalatest"                %% "scalatest"            % scalaTestVersion % Test,
)
