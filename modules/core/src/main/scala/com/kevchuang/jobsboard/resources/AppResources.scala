package com.kevchuang.jobsboard.resources

import cats.effect.*
import cats.effect.std.Console
import com.kevchuang.jobsboard.config.AppConfig
import com.kevchuang.jobsboard.database.PostgreSQL
import fs2.io.net.Network
import skunk.Session

abstract sealed class AppResources[F[_]] private (
    val postgres: Resource[F, Session[F]]
)

object AppResources:
  def make[F[_]: Concurrent: Console: Network: Temporal](
      config: AppConfig
  ): Resource[F, AppResources[F]] =
    PostgreSQL
      .make[F](config.postgres)
      .map(new AppResources[F](_) {})

end AppResources
