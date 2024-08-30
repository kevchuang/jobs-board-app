package com.kevchuang.jobsboard.http.routes

import cats.effect.IO
import cats.syntax.all.*
import com.kevchuang.jobsboard.domain.health
import com.kevchuang.jobsboard.domain.health.Status.*
import com.kevchuang.jobsboard.domain.health.{AppStatus, PostgresStatus}
import com.kevchuang.jobsboard.services.Health
import com.kevchuang.jobsboard.suite.HttpSuite
import org.http4s.Method.GET
import org.http4s.Status as HttpStatus
import org.http4s.client.dsl.io.*
import org.http4s.syntax.literals.*

object HealthRoutesSuite extends HttpSuite:

  def failureStatus: TestHealth =
    new TestHealth:
      override def status: IO[AppStatus] =
        AppStatus(PostgresStatus(Unreachable)).pure[IO]
    end new

  test("GET health status succeed"):
    val healthRoutes = new HealthRoutes[IO](TestHealth())
    val request      = GET(uri"/health")

    expectHttpBodyAndStatus(healthRoutes.routes, request)(
      AppStatus(PostgresStatus(Okay)),
      HttpStatus.Ok
    )

  test("Get health status failure"):
    val healthRoutes = new HealthRoutes[IO](failureStatus)
    val request      = GET(uri"/health")

    expectHttpBodyAndStatus(healthRoutes.routes, request)(
      AppStatus(PostgresStatus(Unreachable)),
      HttpStatus.Ok
    )

end HealthRoutesSuite

class TestHealth extends Health[IO]:
  override def status: IO[health.AppStatus] =
    AppStatus(PostgresStatus(Okay)).pure[IO]
end TestHealth
