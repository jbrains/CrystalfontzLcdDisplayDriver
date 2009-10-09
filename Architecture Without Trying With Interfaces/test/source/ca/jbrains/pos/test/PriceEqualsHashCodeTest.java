package ca.jbrains.pos.test;

import junitx.extensions.EqualsHashCodeTestCase;

public class PriceEqualsHashCodeTest extends EqualsHashCodeTestCase {
	public PriceEqualsHashCodeTest(String name) {
		super(name);
	}

	@Override
	protected Object createInstance() throws Exception {
		return Price.lira(795);
	}

	@Override
	protected Object createNotEqualInstance() throws Exception {
		return Price.lira(794);
	}
}
