package com.myinvestor

import org.scalatest._

/**
  * Base class for all unit test scenarios.
  */
abstract class UnitTestSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors {

  override def convertToLegacyEqualizer[T](left: T): LegacyEqualizer[T] = ???

  override def convertToLegacyCheckingEqualizer[T](left: T): LegacyCheckingEqualizer[T] = ???
}
