lazy val root = (project in file("."))
  .settings(
    name := "Test",
    scalaVersion := "2.12.4",
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.5" % "test"
  )

