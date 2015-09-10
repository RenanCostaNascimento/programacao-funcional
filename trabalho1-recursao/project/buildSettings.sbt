resolvers += "Spray Repository" at "http://repo.spray.io"

libraryDependencies += "net.databinder" %% "dispatch-http" % "0.8.10"

libraryDependencies += "org.scalastyle" %% "scalastyle" % "0.3.2"

libraryDependencies += "io.spray" %% "spray-json" % "1.3.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1"

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.1"

scalacOptions ++= Seq("-deprecation", "-feature")

