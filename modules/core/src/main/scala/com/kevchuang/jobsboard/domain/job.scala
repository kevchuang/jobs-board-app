package com.kevchuang.jobsboard.domain

import cats.Show
import cats.derived.*
import cats.syntax.all.*
import skunk.Codec
import skunk.codec.all.*
import skunk.data.{Arr, Type}
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.skunk.*

import java.util.UUID

object job:
  opaque type Active = Boolean :| Pure
  object Active extends RefinedTypeOps[Boolean, Pure, Active]:
    given codec: Codec[Active] = bool.refined[Pure]

  opaque type CompanyName = String :| Not[Blank]
  object CompanyName extends RefinedTypeOps[String, Not[Blank], CompanyName]:
    given codec: Codec[CompanyName] = varchar.refined[Not[Blank]]

  opaque type Country = String :| Not[Blank]
  object Country extends RefinedTypeOps[String, Not[Blank], Country]:
    given codec: Codec[Country] = varchar.refined[Not[Blank]]

  opaque type Currency = String :| FixedLength[3]
  object Currency extends RefinedTypeOps[String, FixedLength[3], Currency]:
    given codec: Codec[Currency] = varchar.refined[FixedLength[3]]

  opaque type Email = String :| Not[Blank]
  object Email extends RefinedTypeOps[String, Not[Blank], Email]:
    given codec: Codec[Email] = varchar.refined[Not[Blank]]

  opaque type EpochDate = Long :| Positive
  object EpochDate extends RefinedTypeOps[Long, Positive, EpochDate]:
    given codec: Codec[EpochDate] = int8.refined[Positive]

  opaque type JobDescription = String :| Not[Blank]
  object JobDescription
      extends RefinedTypeOps[String, Not[Blank], JobDescription]:
    given codec: Codec[JobDescription] = varchar.refined[Not[Blank]]

  opaque type JobId = UUID :| Pure
  object JobId extends RefinedTypeOps[UUID, Pure, JobId]:
    given codec: Codec[JobId] = uuid.refined[Pure]

  opaque type JobTitle = String :| Not[Blank]
  object JobTitle extends RefinedTypeOps[String, Not[Blank], JobTitle]:
    given codec: Codec[JobTitle] = varchar.refined[Not[Blank]]

  opaque type Location = String :| Not[Blank]
  object Location extends RefinedTypeOps[String, Not[Blank], Location]:
    given codec: Codec[Location] = varchar.refined[Not[Blank]]

  opaque type Salary = Int :| Positive
  object Salary extends RefinedTypeOps[Int, Positive, Salary]:
    given codec: Codec[Salary] = int4.refined[Positive]

  opaque type Tag <: String = String :| Not[Blank]
  object Tag extends RefinedTypeOps[String, Not[Blank], Tag]:
    given codec: Codec[List[Tag]] =
      _varchar
        .eimap(tags =>
          tags.toList
            .map(_.refineEither[Not[Blank]])
            .sequence
        )(tags => Arr(tags*))

  opaque type Url = String :| ValidURL
  object Url extends RefinedTypeOps[String, ValidURL, Url]:
    given codec: Codec[Url] = varchar.refined[ValidURL]

  enum RemotePolicy derives Show:
    case Full
    case Hybrid
    case OnSite
  end RemotePolicy

  object RemotePolicy:
    given codec: Codec[RemotePolicy] =
      `enum`[RemotePolicy](
        _.toString,
        RemotePolicy.valueOf(_).some,
        Type("RemotePolicy")
      )
  end RemotePolicy

  enum Seniority:
    case Junior
    case Confirmed
    case Senior
  end Seniority

  object Seniority:
    given codec: Codec[Seniority] =
      `enum`[Seniority](
        _.toString,
        Seniority.valueOf(_).some,
        Type("Seniority")
      )
  end Seniority

  final case class Job(
      id: JobId,
      date: EpochDate,
      ownerEmail: Email,
      jobInfo: JobInfo,
      active: Active
  )

  final case class JobInfo(
      company: CompanyName,
      title: JobTitle,
      description: JobDescription,
      externalUrl: Url,
      remotePolicy: RemotePolicy,
      location: Location,
      country: Country,
      salaryRange: Option[SalaryRange],
      tags: Option[List[Tag]],
      seniority: Option[Seniority]
  )

  final case class SalaryRange(
      low: Salary,
      high: Salary,
      currency: Currency
  )

end job
