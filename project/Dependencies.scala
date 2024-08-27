import sbt.*

object Dependencies {
  object V {
    val catsCoreVersion   = "2.12.0"
    val catsEffectVersion = "3.5.4"
    val circeVersion      = "0.14.9"
    val ironVersion       = "2.6.0"
    val kittenVersion     = "3.3.0"
    val log4CatsVersion   = "2.6.0"
    val tapirVersion      = "1.11.1"
    val skunkVersion      = "0.6.3"
    val weaverVersion     = "0.8.4"
  }

  object Libraries {
    val cats: List[ModuleID] = List(
      "org.typelevel" %% "cats-core"   % V.catsCoreVersion,
      "org.typelevel" %% "cats-effect" % V.catsEffectVersion
    )

    val circe: List[ModuleID] = List(
      "io.circe" %% "circe-core"    % V.circeVersion,
      "io.circe" %% "circe-generic" % V.circeVersion,
      "io.circe" %% "circe-parser"  % V.circeVersion
    )

    val iron: List[ModuleID] = List(
      "io.github.iltotore" %% "iron"       % V.ironVersion,
      "io.github.iltotore" %% "iron-cats"  % V.ironVersion,
      "io.github.iltotore" %% "iron-circe" % V.ironVersion
    )

    val kittens: List[ModuleID] = List(
      "org.typelevel" %% "kittens" % V.kittenVersion
    )

    val log4cats: List[ModuleID] = List(
      "org.typelevel" %% "log4cats-slf4j" % V.log4CatsVersion
    )

    val tapir: List[ModuleID] = List(
      "com.softwaremill.sttp.tapir" %% "tapir-core"          % V.tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % V.tapirVersion
    )

    val skunk: List[ModuleID] = List(
      "org.tpolecat" %% "skunk-core"  % V.skunkVersion,
      "org.tpolecat" %% "skunk-circe" % V.skunkVersion
    )

    val weaver: List[ModuleID] = List(
      "com.disneystreaming" %% "weaver-cats"       % V.weaverVersion,
      "com.disneystreaming" %% "weaver-scalacheck" % V.weaverVersion
    )
  }
}
