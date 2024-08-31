package com.kevchuang.jobsboard.services

import cats.MonadThrow
import cats.effect.kernel.Resource
import cats.effect.std.UUIDGen
import cats.syntax.all.*
import com.kevchuang.jobsboard.domain.job.*
import com.kevchuang.jobsboard.sql.codecs.job.given
import skunk.*
import skunk.implicits.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.skunk.{*, given}

trait Jobs[F[_]]:
  def create(jobInfo: JobInfo): F[JobId]
end Jobs

object Jobs:
  def make[F[_]: UUIDGen: MonadThrow](
      postgres: Resource[F, Session[F]]
  ): Jobs[F] =
    import JobsSQL.*
    new Jobs[F]:
      def create(jobInfo: JobInfo): F[JobId] =
        postgres.use: session =>
          for
            preparedCommand <- session.prepare(insertJob)
            jobId           <- UUIDGen.randomUUID[F].map(JobId(_))
            _ <- preparedCommand
                   .execute(
                     Job(
                       id = jobId,
                       date = EpochDate(1L),
                       ownerEmail = Email("kchuang.pro@gmail.com"),
                       jobInfo = jobInfo,
                       active = Active(true)
                     )
                   )
          yield jobId
end Jobs

object JobsSQL:
  val insertJob: Command[Job] =
    sql"INSERT INTO jobs VALUES ($jobCodec)".command
end JobsSQL
