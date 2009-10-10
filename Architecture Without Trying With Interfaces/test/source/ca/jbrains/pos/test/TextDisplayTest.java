package ca.jbrains.pos.test;

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

public class TextDisplayTest extends DisplayContractTest {
	@Test
	public void displayPriceDisplaysStandardTextFormat() throws Exception {
		StringWriter canvas = new StringWriter();
		TextDisplay lcdDisplay = new TextDisplay(canvas);
		lcdDisplay.displayPrice(Price.lira(100));
		Assert.assertEquals("100 TL" + System.getProperty("line.separator"), canvas.toString());
	}
	
	@Test
	public void displayNoPriceMessageDisplaysStandardTextFormat() throws Exception {
		StringWriter canvas = new StringWriter();
		TextDisplay lcdDisplay = new TextDisplay(canvas);
		lcdDisplay.displayNoPriceMessage("23456");
		Assert.assertEquals("No product 23456" + System.getProperty("line.separator"), canvas.toString());
	}
	
	@Test
	public void displayGeneralErrorMessageDisplaysStandardTextFormat() throws Exception {
		StringWriter canvas = new StringWriter();
		TextDisplay lcdDisplay = new TextDisplay(canvas);
		lcdDisplay.displayGeneralErrorMessage();
		Assert.assertEquals("*** ERROR ***" + System.getProperty("line.separator"), canvas.toString());
	}
}
