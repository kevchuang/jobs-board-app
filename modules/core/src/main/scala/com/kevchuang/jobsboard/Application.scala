package com.kevchuang.jobsboard

import cats.effect.{IO, IOApp}
import com.kevchuang.jobsboard.config.AppConfig

object Application extends IOApp.Simple:

  override def run: IO[Unit] =
    for _ <- AppConfig.load[IO]
    yield ()

end Application
