package ca.jbrains.pos.test;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

public class RingUpSaleTest extends MockObjectTestCase {
	public static class Price {
		public static Price lira(int lira) {
			return new Price();
		}
	}

	public static class Sale {
		private final Catalog catalog;
		private final Display display;

		public Sale(Catalog catalog, Display display) {
			this.catalog = catalog;
			this.display = display;
		}

		public void onBarcode(String barcode) {
			Price price = catalog.findPrice(barcode);
			if (price == null)
				display.displayNoPriceMessage(barcode);
			else
				display.displayPrice(price);
		}
	}

	public interface Catalog {
		Price findPrice(String barcode);
	}

	public interface Display {
		void displayPrice(Price price);

		void displayNoPriceMessage(String barcode);
	}

	public void testFoundBarcode() throws Exception {
		int irrelevantAmount = -1;

		Mock mockCatalog = mock(Catalog.class);
		Price price = Price.lira(irrelevantAmount);
		mockCatalog.stubs().method("findPrice").with(ANYTHING).will(
				returnValue(price));
		Catalog catalog = (Catalog) mockCatalog.proxy();

		Mock mockDisplay = mock(Display.class);
		mockDisplay.expects(once()).method("displayPrice").with(same(price));
		Display display = (Display) mockDisplay.proxy();

		new Sale(catalog, display).onBarcode("irrelevant barcode");
	}

	public void testNotFoundBarcode() throws Exception {
		String barcode = "12345";

		Mock mockCatalog = mock(Catalog.class);
		mockCatalog.stubs().method("findPrice").with(ANYTHING).will(
				returnValue(null));
		Catalog catalog = (Catalog) mockCatalog.proxy();

		Mock mockDisplay = mock(Display.class);
		mockDisplay.expects(once()).method("displayNoPriceMessage").with(
				eq(barcode));
		Display display = (Display) mockDisplay.proxy();

		new Sale(catalog, display).onBarcode(barcode);

	}
}
