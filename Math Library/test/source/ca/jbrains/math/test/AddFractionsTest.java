package ca.jbrains.math.test;

import junit.framework.Assert;

import org.junit.Test;

public class AddFractionsTest {
	public static class Fraction {

		public static Fraction with(int integerValue) {
			// TODO Auto-generated method stub
			return new Fraction();
		}

		public Fraction plus(Fraction zero) {
			// TODO Auto-generated method stub
			return this;
		}

		public int intValue() {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	@Test
	public void zeroPlusZero() throws Exception {
		Fraction zero = Fraction.with(0);
		Fraction sum = zero.plus(zero);
		Assert.assertEquals(0, sum.intValue());
	}
}
