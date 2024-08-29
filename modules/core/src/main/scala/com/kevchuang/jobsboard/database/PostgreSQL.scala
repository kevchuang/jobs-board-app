package com.kevchuang.jobsboard.database

import cats.effect.*
import cats.effect.std.Console
import cats.syntax.all.*
import com.kevchuang.jobsboard.config.PostgreSQLConfig
import fs2.io.net.Network
import io.github.iltotore.iron.*
import natchez.Trace.Implicits.noop
import skunk.*
import skunk.codec.text.*
import skunk.implicits.*

object PostgreSQL:
  private def checkConnection[F[_]: Concurrent](
      postgres: Resource[F, Session[F]]
  ): F[Unit] =
    postgres
      .use: session =>
        session
          .unique(sql"select version();".query(text))
          .void

  def make[F[_]: Concurrent: Console: Network: Temporal](
      config: PostgreSQLConfig
  ): SessionPool[F] =
    Session
      .pooled[F](
        host = config.host.show,
        port = config.port.value,
        user = config.user.value,
        password = Some(config.password.value),
        database = config.database.value,
        max = config.max
      )
      .evalTap(checkConnection)

end PostgreSQL
