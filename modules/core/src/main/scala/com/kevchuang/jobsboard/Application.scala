package com.kevchuang.jobsboard

import cats.effect.{IO, IOApp}
import com.kevchuang.jobsboard.config.AppConfig
import com.kevchuang.jobsboard.http.server.HttpServer
import com.kevchuang.jobsboard.modules.{HttpApi, Services}

object Application extends IOApp.Simple:

  override def run: IO[Unit] =
    for
      config  <- AppConfig.load[IO]
      services = Services.make[IO]()
      api      = HttpApi.make[IO](services)
      _ <- HttpServer
             .start[IO](config.httpServer, api.httpApp)
             .useForever
    yield ()

end Application
