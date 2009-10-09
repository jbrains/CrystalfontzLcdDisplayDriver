package ca.jbrains.pos.test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import ca.jbrains.pos.test.RingUpSaleTest.Catalog;

public class InMemoryCatalogTest {
	public static class InMemoryCatalog implements Catalog {
		private final Map<String, Price> pricesByBarcode;

		public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
			this.pricesByBarcode = pricesByBarcode;
		}

		public static InMemoryCatalog with(Map<String, Price> pricesByBarcode) {
			return new InMemoryCatalog(pricesByBarcode);
		}

		@Override
		public Price findPrice(String barcode) {
			return pricesByBarcode.get(barcode);
		}
	}

	@Test
	public void foundBarcode() throws Exception {
		Catalog catalog = InMemoryCatalog.with(new HashMap<String, Price>() {
			{
				put("12345", Price.lira(795));
			}
		});
		Assert.assertEquals(Price.lira(795), catalog.findPrice("12345"));
	}

	@Test
	public void notFoundBarcode() throws Exception {
		Catalog catalog = InMemoryCatalog.with(Collections.EMPTY_MAP);
		Assert.assertNull(catalog.findPrice("12345"));
	}

	@Test
	public void findPriceThrowsException() throws Exception {
		Catalog catalog = InMemoryCatalog.with(null);
		try {
			catalog.findPrice("irrelevant barcode");
			fail("You didn't blow up?!");
		} catch (RuntimeException expected) {
		}
	}
}
