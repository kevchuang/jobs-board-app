package com.kevchuang.jobsboard.http.routes

import cats.effect.Async
import cats.syntax.all.*
import com.kevchuang.jobsboard.domain.health.AppStatus
import com.kevchuang.jobsboard.services.Health
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import sttp.tapir.server.ServerEndpoint

final class HealthRoutes[F[_]: Async](health: Health[F]) extends HttpRoute[F]:
  private[routes] val prefixPath = "health"

  private val getStatusEndpoint: ServerEndpoint[Any, F] =
    endpoint.get
      .in(prefixPath)
      .out(jsonBody[AppStatus])
      .serverLogicSuccess(_ => health.status)

  def endpoints: List[ServerEndpoint[Any, F]] = List(
    getStatusEndpoint
  )
end HealthRoutes
