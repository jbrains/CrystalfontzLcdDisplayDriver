package ca.jbrains.math.test;

import static org.junit.Assert.*;
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
			if (this.denominator == that.denominator) {
				return Fraction.with(this.numerator + that.numerator,
						this.denominator);
			} else {
				return Fraction.with(this.numerator * that.denominator
						+ this.denominator * that.numerator, this.denominator
						* that.denominator);
			}
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

		@Override
		public String toString() {
			return String.valueOf(numerator) + "/"
					+ String.valueOf(denominator);
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

	@Test
	public void sameDenominatorButNotIntegers() throws Exception {
		Fraction sum = Fraction.with(1, 5).plus(Fraction.with(2, 5));
		Assert.assertEquals(Fraction.with(3, 5), sum);
	}

	@Test
	public void differentDenominators() throws Exception {
		Assert.assertEquals(Fraction.with(13, 21), Fraction.with(1, 3).plus(
				Fraction.with(2, 7)));
	}

	@Test
	public void resultIsImproperFraction() throws Exception {
		assertEquals(Fraction.with(4, 3), Fraction.with(2, 3).plus(
				Fraction.with(2, 3)));
	}
}
