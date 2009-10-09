package ca.jbrains.pos.test;

import java.text.NumberFormat;
import java.util.Locale;

import ca.jbrains.pos.client.LcdDisplayClient;

public class LcdDisplay implements Display {
	private LcdDisplayClient client;

	public LcdDisplay() {
		client = new LcdDisplayClient();
	}

	public void displayGeneralErrorMessage() {
		client.display("*** ERROR ***");
	}

	@Override
	public void displayNoPriceMessage(String barcode) {
		client.display("No product " + barcode);
	}

	@Override
	public void displayPrice(Price price) {
		client.display(price.getLira() + " TL");
	}
}
