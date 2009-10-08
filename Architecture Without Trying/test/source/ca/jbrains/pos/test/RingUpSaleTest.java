package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

public class RingUpSaleTest {
	public static class Display {
		public String getText() {
			// TODO Auto-generated method stub
			return "TL 795";
		}
	}

	public static class Sale {
		public void onBarcode(String barcode) {
			// TODO Auto-generated method stub

		}
	}

	@Test
	public void foundBarcode() throws Exception {
		Sale sale = new Sale();
		Display display = new Display();
		sale.onBarcode("12345");
		Assert.assertEquals("TL 795", display.getText());
	}
}
