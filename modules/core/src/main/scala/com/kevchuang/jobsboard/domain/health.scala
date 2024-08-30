package com.kevchuang.jobsboard.domain

import io.circe.Codec

object health:
  enum Status derives Codec.AsObject:
    case Okay
    case Unreachable
  end Status

  def boolToStatus(boolean: Boolean): Status =
    if boolean then Status.Okay else Status.Unreachable

  final case class PostgresStatus(status: Status) derives Codec.AsObject
  final case class AppStatus(postgres: PostgresStatus) derives Codec.AsObject
end health
