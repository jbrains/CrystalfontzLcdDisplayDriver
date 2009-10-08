package ca.jbrains.math.test;

import junitx.extensions.EqualsHashCodeTestCase;
import ca.jbrains.math.test.AddFractionsTest.Fraction;

public class FractionEqualsTest extends EqualsHashCodeTestCase {
	public FractionEqualsTest(String name) {
		super(name);
	}

	@Override
	protected Object createInstance() throws Exception {
		return Fraction.with(2, 5);
	}

	@Override
	protected Object createNotEqualInstance() throws Exception {
		return Fraction.with(2, 3);
	}
}
