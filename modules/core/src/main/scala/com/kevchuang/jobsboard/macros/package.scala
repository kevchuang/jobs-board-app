package com.kevchuang.jobsboard

import cats.Show
import io.github.iltotore.iron.*
import pureconfig.ConfigReader
import pureconfig.error.FailureReason

package object macros:

  private case class ConfigRefinedError(description: String)
      extends FailureReason

  inline given [A, C](using
      inline reader: ConfigReader[A],
      inline constraint: Constraint[A, C],
      inline show: Show[A]
  ): ConfigReader[A :| C] =
    reader
      .emap(value =>
        value
          .refineEither[C]
          .left
          .map(ConfigRefinedError(_))
      )

  inline given [A](using
      mirror: RefinedTypeOps.Mirror[A],
      reader: ConfigReader[mirror.IronType]
  ): ConfigReader[A] =
    reader.asInstanceOf[ConfigReader[A]]
end macros
