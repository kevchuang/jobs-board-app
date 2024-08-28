package com.kevchuang.jobsboard.modules

import cats.Applicative
import com.kevchuang.jobsboard.services.Health

sealed abstract class Services[F[_]] private (
    val health: Health[F]
)

object Services:
  def make[F[_]: Applicative](): Services[F] =
    new Services[F](
      health = Health.make[F]()
    ) {}
end Services
