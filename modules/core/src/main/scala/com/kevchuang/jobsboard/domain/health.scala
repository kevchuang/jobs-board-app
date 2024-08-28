package com.kevchuang.jobsboard.domain

import io.circe.Codec

object health:
  enum Status:
    case Okay
    case Unreachable
  end Status

  final case class AppStatus() derives Codec.AsObject
end health
