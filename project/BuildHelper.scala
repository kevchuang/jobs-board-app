import sbt.Keys.*
import sbt.*

object BuildHelper {
  val scala3 = "3.3.1"

  def organizationSettings: List[Setting[String]] = List(
    organization     := "com.kevchuang",
    organizationName := "kevchuang"
  )

  def standardSettings: List[
    Setting[? >: String & Task[Seq[String]] & Boolean & Seq[TestFramework]]
  ] = List(
    ThisBuild / scalaVersion := scala3,
    scalacOptions            := ScalaSettings.baseSettings,
    Test / parallelExecution := true,
    ThisBuild / fork         := true,
    run / fork               := true,
    testFrameworks += new TestFramework("weaver.framework.CatsEffect")
  )
}
