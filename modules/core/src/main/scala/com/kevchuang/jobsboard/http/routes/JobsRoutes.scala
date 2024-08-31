package com.kevchuang.jobsboard.http.routes
import cats.effect.Async
import sttp.tapir.server.ServerEndpoint

final class JobsRoutes[F[_]: Async]() extends HttpRoute[F]:
  private[routes] val prefixPath = "jobs"

  // POST /jobs/create

  def endpoints: List[ServerEndpoint[Any, F]] = ???

end JobsRoutes
