package com.kevchuang.jobsboard

import com.comcast.ip4s.{Host, Port}
import pureconfig.ConfigReader
import pureconfig.error.CannotConvert

package object config:
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
