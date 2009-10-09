package ca.jbrains.pos.test;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public class InMemoryCatalogTest extends CatalogContractTest {
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

	@Override
	protected Catalog createCatalogWith(final String barcode, final Price price) {
		return InMemoryCatalog.with(new HashMap<String, Price>() {
			{
				put(barcode, price);
			}
		});
	}

	@Override
	protected Catalog createCatalogWithout(String barcode) {
		return InMemoryCatalog.with(Collections.EMPTY_MAP);
	}

	@Override
	protected Catalog createCrashTestDummyCatalog() {
		return InMemoryCatalog.with(null);
	}
}
