package com.kevchuang.jobsboard.suite

import cats.effect.IO
import cats.implicits.*
import io.circe.Encoder
import io.circe.syntax.*
import org.http4s.circe.*
import org.http4s.{Status as HttpStatus, *}
import weaver.scalacheck.Checkers
import weaver.{Expectations, SimpleIOSuite}

import scala.util.control.NoStackTrace

trait HttpSuite extends SimpleIOSuite with Checkers:

  case object DummyError extends NoStackTrace

  def expectHttpBodyAndStatus[A: Encoder](
      routes: HttpRoutes[IO],
      req: Request[IO]
  )(
      expectedBody: A,
      expectedStatus: HttpStatus
  ): IO[Expectations] =
    routes
      .run(req)
      .value
      .flatMap:
        case Some(resp) =>
          resp.asJson.map: json =>
            expect.same(resp.status, expectedStatus) |+|
              expect.same(
                json.dropNullValues,
                expectedBody.asJson.dropNullValues
              )
        case None => IO.pure(failure("route not found"))

  def expectHttpStatus(routes: HttpRoutes[IO], req: Request[IO])(
      expectedStatus: HttpStatus
  ): IO[Expectations] =
    routes
      .run(req)
      .value
      .map:
        case Some(resp) => expect.same(resp.status, expectedStatus)
        case None       => failure("route not found")

  def expectHttpFailure(
      routes: HttpRoutes[IO],
      req: Request[IO]
  ): IO[Expectations] =
    routes
      .run(req)
      .value
      .attempt
      .map:
        case Left(_)  => success
        case Right(_) => failure("expected a failure")

end HttpSuite
