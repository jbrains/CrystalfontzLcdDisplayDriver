package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import ca.jbrains.pos.test.RingUpSaleTest.Catalog;

public class InMemoryCatalogTest {
	public static class InMemoryCatalog implements Catalog {
		public static InMemoryCatalog with(Map<String, Price> pricesByBarcode) {
			return new InMemoryCatalog();
		}

		@Override
		public Price findPrice(String barcode) {
			return Price.lira(795);
		}
	}

	@Test
	public void foundBarcode() throws Exception {
		Catalog catalog = InMemoryCatalog
				.with(new HashMap<String, Price>() {
					{
						put("12345", Price.lira(795));
					}
				});
		Assert.assertEquals(Price.lira(795), catalog.findPrice("12345"));
	}
}
