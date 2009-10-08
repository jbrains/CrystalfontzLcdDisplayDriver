package ca.jbrains.math.test;

import static org.junit.Assert.*;
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

		public Fraction plus(Fraction that) {
			return Fraction.with(this.integerValue + that.integerValue);
		}

		public int intValue() {
			return integerValue;
		}
		
		@Override
		public boolean equals(Object other) {
			if (other instanceof Fraction) {
				Fraction that = (Fraction) other;
				return this.integerValue == that.integerValue;
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
