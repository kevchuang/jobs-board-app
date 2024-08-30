package com.kevchuang.jobsboard

import cats.effect.std.Supervisor
import cats.effect.{IO, IOApp}
import cats.syntax.all.*
import com.kevchuang.jobsboard.config.AppConfig
import com.kevchuang.jobsboard.http.server.HttpServer
import com.kevchuang.jobsboard.modules.{HttpApi, Services}
import com.kevchuang.jobsboard.resources.AppResources

object Application extends IOApp.Simple:

  override def run: IO[Unit] =
    for
      config <- AppConfig.load[IO]
      _ <- Supervisor[IO].use: _ =>
             AppResources
               .make[IO](config)
               .evalMap: resources =>
                 val services = Services.make[IO](resources.postgres)
                 val api      = HttpApi.make[IO](services)

                 (config.httpServer, api.httpApp).pure[IO]
               .flatMap:
                 HttpServer.start[IO](_, _)
               .useForever
    yield ()

end Application
