package ca.jbrains.pos.test;

import static org.junit.Assert.*;

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
		private final Map<String, String> pricesByBarcode;

		public Sale(Display display, Map<String, String> pricesByBarcode) {
			this.display = display;
			this.pricesByBarcode = pricesByBarcode;
		}

		public void onBarcode(String barcode) {
			if (pricesByBarcode.containsKey(barcode))
				display.setText(pricesByBarcode.get(barcode));
			else
				display.setText("No product with barcode " + barcode);
		}
	}

	@Test
	public void foundBarcode() throws Exception {
		Display display = new Display();
		Sale sale = new Sale(display, new HashMap<String, String>() {
			{
				put("12345", "TL 795");
				put("23456", "TL 500");
				// 99999 is not here
			}
		});
		sale.onBarcode("12345");
		Assert.assertEquals("TL 795", display.getText());
	}

	@Test
	public void foundAnotherBarcode() throws Exception {
		Display display = new Display();
		Sale sale = new Sale(display, new HashMap<String, String>() {
			{
				put("12345", "TL 795");
				put("23456", "TL 500");
				// 99999 is not here
			}
		});
		sale.onBarcode("23456");
		Assert.assertEquals("TL 500", display.getText());
	}

	@Test
	public void didNotFindBarcode() throws Exception {
		Display display = new Display();
		Sale sale = new Sale(display, new HashMap<String, String>() {
			{
				put("12345", "TL 795");
				put("23456", "TL 500");
				// 99999 is not here
			}
		});
		sale.onBarcode("99999");
		Assert.assertEquals("No product with barcode 99999", display.getText());
	}
}
