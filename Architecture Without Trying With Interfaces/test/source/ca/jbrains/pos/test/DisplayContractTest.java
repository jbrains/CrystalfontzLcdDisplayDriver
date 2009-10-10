package ca.jbrains.pos.test;

import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;


public abstract class DisplayContractTest {
	protected StringWriter canvas;
	private Display display;

	protected abstract void assertTextDisplayed(String text);

	protected abstract Display createDisplayWith(StringWriter canvas);
	
	@Before
	public void setUp() throws Exception {
		canvas = new StringWriter();
		display = createDisplayWith(canvas);
	}

	@Test
	public void displayPrice() throws Exception {
		display.displayPrice(Price.lira(100));
		assertTextDisplayed("100 TL");
	}

	@Test
	public void displayNoPriceMessage() throws Exception {
		display.displayNoPriceMessage("23456");
		assertTextDisplayed("No product 23456");
	}

	@Test
	public void displayGeneralErrorMessage() throws Exception {
		display.displayGeneralErrorMessage();
		assertTextDisplayed("*** ERROR ***");
	}
}
