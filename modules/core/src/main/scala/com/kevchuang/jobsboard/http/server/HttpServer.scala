package com.kevchuang.jobsboard.http.server

import cats.effect.{Async, Resource}
import com.kevchuang.jobsboard.config.HttpServerConfig
import fs2.io.net.Network
import org.http4s.HttpApp
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server

object HttpServer:
  def start[F[_]: Async: Network](
      config: HttpServerConfig,
      httpApp: HttpApp[F]
  ): Resource[F, Server] =
    EmberServerBuilder
      .default[F]
      .withHost(config.host)
      .withPort(config.port)
      .withHttpApp(httpApp)
      .build
end HttpServer
