package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

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
			Map<String, String> pricesByBarcode = new HashMap<String, String>() {
				{
					put("12345", "TL 795");
					put("23456", "TL 500");
				}
			};

			display.setText(pricesByBarcode.get(barcode));
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
