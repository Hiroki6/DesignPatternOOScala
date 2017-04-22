package peculiar.traits.mixin

import org.scalatest.{Matchers, FlatSpec}

class DoubledMuitplierIdentityTest extends FlatSpec with Matchers {

  class DoubledMultiplierIdentityClass extends DoubledMultiplierIdentity

  val instance = new DoubledMultiplierIdentityClass

  "identity" should "return 2 * 1" in {
    instance.identity should equal(2)
  }
}
