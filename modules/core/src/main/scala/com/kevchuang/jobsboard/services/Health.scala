package com.kevchuang.jobsboard.services

import cats.Applicative
import cats.syntax.all.*
import com.kevchuang.jobsboard.domain.health.AppStatus

trait Health[F[_]]:
  def status: F[AppStatus]
end Health

object Health:
  def make[F[_]: Applicative](): Health[F] =
    new Health[F]:
      def status: F[AppStatus] =
        AppStatus().pure[F]
    end new
end Health
