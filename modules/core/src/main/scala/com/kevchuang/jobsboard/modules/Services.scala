package com.kevchuang.jobsboard.modules

import cats.effect.{Resource, Temporal}
import com.kevchuang.jobsboard.services.Health
import skunk.Session

sealed abstract class Services[F[_]] private (
    val health: Health[F]
)

object Services:
  def make[F[_]: Temporal](postgres: Resource[F, Session[F]]): Services[F] =
    new Services[F](
      health = Health.make[F](postgres)
    ) {}
end Services
