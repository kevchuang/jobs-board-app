import BuildHelper.*
import Dependencies.Libraries

lazy val root = (project in file("."))
  .settings(organizationSettings)
  .settings(standardSettings)
  .aggregate(core, tests)

lazy val core = (project in file("modules/core"))
  .settings(organizationSettings)
  .settings(standardSettings)
  .settings(
    libraryDependencies ++=
      Libraries.cats ++
        Libraries.circe ++
        Libraries.iron ++
        Libraries.kittens ++
        Libraries.log4cats ++
        Libraries.skunk ++
        Libraries.pureConfig ++
        Libraries.tapir
  )

lazy val tests = (project in file("modules/tests"))
  .settings(organizationSettings)
  .settings(standardSettings)
  .settings(
    libraryDependencies ++=
      Libraries.weaver
  )
  .dependsOn(core)
