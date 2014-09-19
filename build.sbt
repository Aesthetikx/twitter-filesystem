name := "twitter-filesystem"

version := "0.0.1"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "0.3.16",
  "org.yaml" % "snakeyaml" % "1.14",
  "org.tukaani" % "xz" % "1.5",
  "commons-io" % "commons-io" % "2.4"
)
