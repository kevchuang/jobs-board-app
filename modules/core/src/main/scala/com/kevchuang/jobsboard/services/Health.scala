package com.kevchuang.jobsboard.services

import cats.effect.implicits.*
import cats.effect.{Resource, Temporal}
import cats.syntax.all.*
import com.kevchuang.jobsboard.domain.health.*
import skunk.*
import skunk.codec.all.int4
import skunk.implicits.*

import scala.concurrent.duration.*

trait Health[F[_]]:
  def status: F[AppStatus]
end Health

object Health:
  def make[F[_]: Temporal](postgres: Resource[F, Session[F]]): Health[F] =
    new Health[F]:
      val query: Query[Void, Int] =
        sql"SELECT pid FROM pg_stat_activity".query(int4)

      def status: F[AppStatus] =
        postgres
          .use(_.execute(query))
          .map(_.nonEmpty)
          .timeout(1.second)
          .map(boolToStatus)
          .map: status =>
            AppStatus(PostgresStatus(status))

    end new
end Health
