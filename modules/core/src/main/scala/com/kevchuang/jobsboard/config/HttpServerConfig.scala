package com.kevchuang.jobsboard.config

import com.comcast.ip4s.{Host, Port}

final case class HttpServerConfig(
    host: Host,
    port: Port
)
