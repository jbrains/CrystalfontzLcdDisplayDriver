package ca.jbrains.math.test;

import static org.junit.Assert.*;

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
}
