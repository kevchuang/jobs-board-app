package com.kevchuang.jobsboard.modules

import cats.effect.Async
import com.kevchuang.jobsboard.http.routes.HealthRoutes
import org.http4s.server.Router
import org.http4s.{HttpApp, HttpRoutes}

sealed abstract class HttpApi[F[_]: Async] private (
    services: Services[F]
):
  private val healthRoutes: HealthRoutes[F] = HealthRoutes[F](services.health)

  private val routes: HttpRoutes[F] =
    healthRoutes.routes

  val httpApp: HttpApp[F] = Router[F]("/" -> routes).orNotFound
end HttpApi

object HttpApi:
  def make[F[_]: Async](services: Services[F]): HttpApi[F] =
    new HttpApi[F](services) {}
end HttpApi
