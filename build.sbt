name := "AkkaStream_FlightDelay"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  // "com.typesafe.akka" % "akka-actor_2.11" % "2.4.8",
  "com.typesafe.akka" % "akka-stream_2.11" % "2.5.0",
  "joda-time" % "joda-time" % "2.9.4")

lazy val AllLibraryDependencies =
  Seq(
    "com.typesafe.akka" % "akka-actor_2.11" % "2.5.0",
    "com.typesafe.akka" % "akka-http_2.11" % "3.0.0-RC1",
    "com.typesafe.akka" % "akka-http-core_2.11" % "3.0.0-RC1",
    "com.typesafe.akka" % "akka-http-spray-json_2.11" % "3.0.0-RC1"



  )

// https://mvnrepository.com/artifact/com.typesafe.scala-logging/scala-logging_2.11
libraryDependencies += "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.5.0"

libraryDependencies += "com.github.fomkin" %% "korolev-server-blaze" % "0.3.0"

// https://mvnrepository.com/artifact/com.intel.analytics.bigdl/bigdl
libraryDependencies += "com.intel.analytics.bigdl" % "bigdl" % "0.1.0"

// https://mvnrepository.com/artifact/ml.dmlc.mxnet/mxnet-full_2.11-osx-x86_64-cpu
//libraryDependencies += "ml.dmlc.mxnet" % "mxnet-full_2.11-osx-x86_64-cpu" % "0.9.3a"

// https://mvnrepository.com/artifact/ml.dmlc.mxnet/mxnet-core_2.10
//libraryDependencies += "ml.dmlc.mxnet" % "mxnet-core_2.10" % "0.1.1"

// https://mvnrepository.com/artifact/ml.dmlc.mxnet/mxnet-core_2.11
//libraryDependencies += "ml.dmlc.mxnet" % "mxnet-core_2.11" % "0.9.3a"



// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http_2.11
//libraryDependencies += "com.typesafe.akka" % "akka-http_2.11" % "10.0.5"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http-core_2.11
//libraryDependencies += "com.typesafe.akka" % "akka-http-core_2.11" % "10.0.5"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http-spray-json_2.11
//libraryDependencies += "com.typesafe.akka" % "akka-http-spray-json_2.11" % "10.0.5"


// https://mvnrepository.com/artifact/com.typesafe.akka/akka-slf4j_2.11
libraryDependencies += "com.typesafe.akka" % "akka-slf4j_2.11" % "2.5.0"


// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http-spray-json-experimental_2.11
//libraryDependencies += "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "2.4.11.1"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-remote_2.11
libraryDependencies += "com.typesafe.akka" % "akka-remote_2.11" % "2.5.0"

        