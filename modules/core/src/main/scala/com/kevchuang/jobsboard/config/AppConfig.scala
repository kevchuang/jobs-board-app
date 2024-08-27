package com.kevchuang.jobsboard.config

import cats.effect.Sync
import pureconfig.generic.derivation.default.*
import pureconfig.module.catseffect.syntax.*
import pureconfig.{ConfigReader, ConfigSource}

final case class AppConfig(
    httpServer: HttpServerConfig
) derives ConfigReader

object AppConfig:
  def load[F[_]: Sync]: F[AppConfig] =
    ConfigSource
      .resources("application.conf")
      .loadF[F, AppConfig]()
end AppConfig
