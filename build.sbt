organization := "in.norbor"
name := "play-rest"
version := "beta"

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"
scalacOptions := Seq("-feature", "-deprecation", "-unchecked")

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc
    exclude("com.h2database", "h2")
    exclude("com.jolbox", "bonecp")
  , ehcache, guice
  , "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.5"
  , "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
  , "net.codingwell" %% "scala-guice" % "4.2.1"
  , "joda-time" % "joda-time" % "2.9.9"
)

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.194" % Test
  , "com.jolbox" % "bonecp" % "0.8.0.RELEASE" % Test
  , "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.1" % Test
  , "com.jarunj.xml" % "xml-compare" % "0.7.2" % Test
)

import com.typesafe.sbt.packager.universal.ZipHelper

packageBin in Universal := {
  val originalFileName = (packageBin in Universal).value
  val (base, ext) = originalFileName.baseAndExt
  val newFileName = file(originalFileName.getParent) / (base + "_dist." + ext)
  val extractedFiles = IO.unzip(originalFileName, file(originalFileName.getParent))
  val mappings: Set[(File, String)] = extractedFiles.map(f => (f, f.getAbsolutePath.substring(originalFileName.getParent.length + base.length + 2)))
  val binFiles = mappings.filter { case (file, path) => path.startsWith("bin/") }
  for (f <- binFiles) f._1.setExecutable(true)
  ZipHelper.zip(mappings, newFileName)
  IO.move(newFileName, originalFileName)
  IO.delete(file(originalFileName.getParent + "/" + originalFileName.base))
  originalFileName
}
