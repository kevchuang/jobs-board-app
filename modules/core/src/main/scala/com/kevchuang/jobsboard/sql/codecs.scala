package com.kevchuang.jobsboard.sql

import com.kevchuang.jobsboard.domain.job.*
import skunk.*
import skunk.codec.all.*
import skunk.implicits.*

object codecs:
  object job:
    given salaryRangeCodec: Codec[SalaryRange] =
      (Salary.codec *: Salary.codec *: Currency.codec).to[SalaryRange]

    given jobInfoCodec: Codec[JobInfo] =
      (CompanyName.codec *: JobTitle.codec *: JobDescription.codec *: Url.codec *: RemotePolicy.codec *: Location.codec *: Country.codec *: salaryRangeCodec.opt *: Tag.codec.opt *: Seniority.codec.opt)
        .to[JobInfo]

    given jobCodec: Codec[Job] =
      (JobId.codec *: EpochDate.codec *: Email.codec *: jobInfoCodec *: Active.codec)
        .to[Job]
  end job

end codecs
