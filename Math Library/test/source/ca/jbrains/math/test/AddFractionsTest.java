package ca.jbrains.math.test;

import junit.framework.Assert;

import org.junit.Test;

public class AddFractionsTest {
	public static class Fraction {

		private final int integerValue;

		public Fraction(int integerValue) {
			this.integerValue = integerValue;
		}

		public static Fraction with(int integerValue) {
			return new Fraction(integerValue);
		}

		public Fraction plus(Fraction zero) {
			// TODO Auto-generated method stub
			return this;
		}

		public int intValue() {
			return integerValue;
		}

	}

	@Test
	public void zeroPlusZero() throws Exception {
		Fraction zero = Fraction.with(0);
		Fraction sum = zero.plus(zero);
		Assert.assertEquals(0, sum.intValue());
	}
}
