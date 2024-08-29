package com.kevchuang.jobsboard

import com.comcast.ip4s.{Host, Port}
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import pureconfig.ConfigReader
import pureconfig.error.CannotConvert

package object config:
  opaque type DatabaseName = String :| Not[Blank]
  object DatabaseName extends RefinedTypeOps[String, Not[Blank], DatabaseName]

  opaque type Password = String :| Not[Blank]
  object Password extends RefinedTypeOps[String, Not[Blank], Password]

  opaque type Username = String :| Not[Blank]
  object Username extends RefinedTypeOps[String, Not[Blank], Username]

  // config reader
  given ConfigReader[Host] =
    ConfigReader[String].emap: host =>
      Host
        .fromString(host)
        .toRight(
          CannotConvert(
            value = host,
            toType = Host.getClass.toString,
            because = s"Invalid host string: $host"
          )
        )

  given ConfigReader[Port] =
    ConfigReader[Int].emap: port =>
      Port
        .fromInt(port)
        .toRight(
          CannotConvert(
            value = port.toString,
            toType = Port.getClass.toString,
            because = s"Invalid port number: $port"
          )
        )
end config
