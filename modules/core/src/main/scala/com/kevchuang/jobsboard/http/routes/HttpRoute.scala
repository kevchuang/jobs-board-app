package com.kevchuang.jobsboard.http.routes

import cats.effect.Async
import org.http4s.HttpRoutes
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.http4s.Http4sServerInterpreter

trait HttpRoute[F[_]: Async]:
  def endpoints: List[ServerEndpoint[Any, F]]
  def routes: HttpRoutes[F] = Http4sServerInterpreter[F]().toRoutes(endpoints)
end HttpRoute
