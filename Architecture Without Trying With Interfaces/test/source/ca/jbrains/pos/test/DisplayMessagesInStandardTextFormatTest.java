package ca.jbrains.pos.test;

import java.io.StringWriter;

import org.junit.Assert;

public class DisplayMessagesInStandardTextFormatTest extends
		DisplayContractTest {

	@Override
	protected Display createDisplayWith(StringWriter canvas) {
		return new TextDisplay(canvas);
	}

	@Override
	protected void assertTextDisplayed(String text) {
		Assert.assertEquals(text + System.getProperty("line.separator"), canvas
				.toString());
	}
}
