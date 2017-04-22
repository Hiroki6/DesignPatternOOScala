package peculiar.traits.mixin

class MultiplierIdentity {
  def identity: Int = 1
}

trait DoubledMultiplierIdentity extends MultiplierIdentity {
  override def identity: Int = 2 * super.identity
}

trait TripledMultiplierIdentity extends MultiplierIdentity {
  override def identity: Int = 3 * super.identity
}

class MultifiedIdentity1 extends DoubledMultiplierIdentity with TripledMultiplierIdentity

class MultifiedIdentity2 extends DoubledMultiplierIdentity with TripledMultiplierIdentity {
  override def identity: Int =
    super[DoubledMultiplierIdentity].identity
}

class MultifiedIdentity3 extends DoubledMultiplierIdentity with TripledMultiplierIdentity {
  override def identity: Int =
    super[TripledMultiplierIdentity].identity
}

class MultifiedIdentity4 extends TripledMultiplierIdentity with DoubledMultiplierIdentity

class MultifiedIdentity5 extends TripledMultiplierIdentity with DoubledMultiplierIdentity {
   override def identity: Int =
    super[DoubledMultiplierIdentity].identity
}

class MultifiedIdentity6 extends TripledMultiplierIdentity with DoubledMultiplierIdentity {
   override def identity: Int =
    super[TripledMultiplierIdentity].identity
}

object ModifiedIdentityUser {
  def main(args: Array[String]): Unit = {
    val instance1 = new MultifiedIdentity1
    val instance2 = new MultifiedIdentity2
    val instance3 = new MultifiedIdentity3
    val instance4 = new MultifiedIdentity4
    val instance5 = new MultifiedIdentity5
    val instance6 = new MultifiedIdentity6

    System.out.println(s"Result 1: ${instance1.identity}")
    System.out.println(s"Result 2: ${instance2.identity}")
    System.out.println(s"Result 3: ${instance3.identity}")
    System.out.println(s"Result 4: ${instance4.identity}")
    System.out.println(s"Result 5: ${instance5.identity}")
    System.out.println(s"Result 6: ${instance6.identity}")
  }
}
