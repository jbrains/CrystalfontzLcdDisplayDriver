package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

import ca.jbrains.math.test.AddFractionsTest.Fraction;

public class FractionEqualsTest {
	@Test
	public void integersAreEqual() throws Exception {
		Assert.assertEquals(Fraction.with(2), Fraction.with(2));
		Assert.assertEquals(Fraction.with(12), Fraction.with(12));
	}

	@Test
	public void integersAreNotEqual() throws Exception {
		Assert.assertFalse(Fraction.with(2).equals(Fraction.with(12)));
	}

	@Test
	public void fractionsAreEqual() throws Exception {
		Assert.assertEquals(Fraction.with(2, 5), Fraction.with(2, 5));
		Assert.assertEquals(Fraction.with(1, 6), Fraction.with(1, 6));
	}

	@Test
	public void fractionsAreNotEqual() throws Exception {
		Assert.assertFalse(Fraction.with(2, 5).equals(Fraction.with(2, 3)));
		Assert.assertFalse(Fraction.with(12, 5).equals(Fraction.with(2, 3)));
	}
}
