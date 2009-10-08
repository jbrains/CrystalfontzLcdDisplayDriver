package ca.jbrains.math.test;

import junit.framework.Assert;

import org.junit.Test;

public class AddFractionsTest {
	public static class Fraction {
		private final int numerator;
		private final int denominator;

		public Fraction(int numerator, int denominator) {
			this.numerator = numerator;
			this.denominator = denominator;
		}
		
		public static Fraction with(int numerator, int denominator) {
			return new Fraction(numerator, denominator);
		}

		public static Fraction with(int integerValue) {
			return new Fraction(integerValue, 1);
		}

		public Fraction plus(Fraction that) {
			return Fraction.with(this.numerator + that.numerator);
		}

		public int intValue() {
			return numerator;
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Fraction) {
				Fraction that = (Fraction) other;
				return this.numerator == that.numerator
						&& this.denominator == that.denominator;
			}
			return false;
		}

	}

	@Test
	public void zeroPlusZero() throws Exception {
		Fraction zero = Fraction.with(0);
		Fraction sum = zero.plus(zero);
		Assert.assertEquals(0, sum.intValue());
	}

	@Test
	public void threePlusZero() throws Exception {
		Fraction three = Fraction.with(3);
		Fraction zero = Fraction.with(0);
		Fraction sum = three.plus(zero);
		Assert.assertEquals(3, sum.intValue());
	}

	@Test
	public void threePlusFive() throws Exception {
		Fraction three = Fraction.with(3);
		Fraction five = Fraction.with(5);
		Fraction sum = three.plus(five);
		Assert.assertEquals(8, sum.intValue());
	}
}
