package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

public class RingUpSaleTest {
	public static class Display {
		private String text;

		public void setText(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	public static class Sale {
		private final Display display;

		public Sale(Display display) {
			this.display = display;
		}

		public void onBarcode(String barcode) {
			if ("12345".equals(barcode))
				display.setText("TL 795");
			else
				display.setText("TL 500");
		}
	}

	@Test
	public void foundBarcode() throws Exception {
		Display display = new Display();
		Sale sale = new Sale(display);
		sale.onBarcode("12345");
		Assert.assertEquals("TL 795", display.getText());
	}

	@Test
	public void foundAnotherBarcode() throws Exception {
		Display display = new Display();
		Sale sale = new Sale(display);
		sale.onBarcode("23456");
		Assert.assertEquals("TL 500", display.getText());
	}
}
