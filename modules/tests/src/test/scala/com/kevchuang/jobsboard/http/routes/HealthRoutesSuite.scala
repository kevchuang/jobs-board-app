package com.kevchuang.jobsboard.http.routes

import cats.effect.IO
import com.kevchuang.jobsboard.services.Health
import com.kevchuang.jobsboard.suite.HttpSuite
import org.http4s.Method.GET
import org.http4s.Status as HttpStatus
import org.http4s.client.dsl.io.*
import org.http4s.syntax.literals.*

object HealthRoutesSuite extends HttpSuite:

  test("GET health status succeed") {
    val healthRoutes = new HealthRoutes[IO](Health.make[IO]())
    val request      = GET(uri"/health")
    expectHttpStatus(healthRoutes.routes, request)(HttpStatus.Ok)
  }
end HealthRoutesSuite
