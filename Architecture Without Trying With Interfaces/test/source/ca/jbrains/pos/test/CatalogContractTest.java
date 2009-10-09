package ca.jbrains.pos.test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;


public abstract class CatalogContractTest {
	protected abstract Catalog createCatalogWith(final String barcode,
			final Price price);

	@Test
	public void foundBarcode() throws Exception {
		Catalog catalog = createCatalogWith("12345", Price.lira(795));
		Assert.assertEquals(Price.lira(795), catalog.findPrice("12345"));
	}

	protected abstract Catalog createCatalogWithout(String barcode);

	@Test
	public void notFoundBarcode() throws Exception {
		Catalog catalog = createCatalogWithout("12345");
		Assert.assertNull(catalog.findPrice("12345"));
	}

	protected abstract Catalog createCrashTestDummyCatalog();

	@Test
	public void findPriceThrowsException() throws Exception {
		Catalog catalog = createCrashTestDummyCatalog();
		try {
			catalog.findPrice("irrelevant barcode");
			fail("You didn't blow up?!");
		} catch (RuntimeException expected) {
		}
	}

}