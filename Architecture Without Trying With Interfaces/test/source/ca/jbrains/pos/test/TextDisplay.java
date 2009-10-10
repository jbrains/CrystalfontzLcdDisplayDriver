package ca.jbrains.pos.test;

import java.io.PrintWriter;
import java.io.Writer;

public class TextDisplay implements Display {
	private PrintWriter out;

	public TextDisplay(Writer canvas) {
		this.out = new PrintWriter(canvas, true);
	}

	public void displayGeneralErrorMessage() {
		out.println("*** ERROR ***");
	}

	@Override
	public void displayNoPriceMessage(String barcode) {
		out.println("No product " + barcode);
	}

	@Override
	public void displayPrice(Price price) {
		out.println(price.getLira() + " TL");
	}
}
