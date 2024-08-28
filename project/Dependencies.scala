import sbt.*

object Dependencies {
  object V {
    val catsCoreVersion   = "2.12.0"
    val catsEffectVersion = "3.5.4"
    val circeVersion      = "0.14.9"
    val http4sVersion     = "0.23.27"
    val ip4sVersion       = "3.5.0"
    val ironVersion       = "2.6.0"
    val kittenVersion     = "3.3.0"
    val log4CatsVersion   = "2.6.0"
    val pureConfigVersion = "0.17.7"
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

    val http4s: List[ModuleID] = List(
      "org.http4s" %% "http4s-dsl"          % V.http4sVersion,
      "org.http4s" %% "http4s-ember-server" % V.http4sVersion,
      "org.http4s" %% "http4s-ember-client" % V.http4sVersion,
      "org.http4s" %% "http4s-circe"        % V.http4sVersion
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

    val pureConfig: List[ModuleID] = List(
      "com.github.pureconfig" %% "pureconfig-core"        % V.pureConfigVersion,
      "com.github.pureconfig" %% "pureconfig-cats-effect" % V.pureConfigVersion
    )

    val tapir: List[ModuleID] = List(
      "com.softwaremill.sttp.tapir" %% "tapir-core"          % V.tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe"    % V.tapirVersion,
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
