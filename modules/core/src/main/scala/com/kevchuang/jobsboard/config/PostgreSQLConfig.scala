package com.kevchuang.jobsboard.config

import com.comcast.ip4s.{Host, Port}
import io.github.iltotore.iron.constraint.all.*

final case class PostgreSQLConfig(
    host: Host,
    port: Port,
    database: DatabaseName,
    user: Username,
    password: Password,
    max: Int
)
