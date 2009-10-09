package ca.jbrains.pos.test;

import org.jmock.Mock;
import org.jmock.MockObjectTestCase;
import org.jmock.core.stub.ReturnStub;

public class RingUpSaleTest extends MockObjectTestCase {
	private Mock mockCatalog;
	private Catalog catalog;
	private Mock mockDisplay;
	private Display display;
	private Sale sale;

	public static class Sale {
		private final Catalog catalog;
		private final Display display;

		public Sale(Catalog catalog, Display display) {
			this.catalog = catalog;
			this.display = display;
		}

		public void onBarcode(String barcode) {
			try {
				Price price = catalog.findPrice(barcode);
				if (price == null)
					display.displayNoPriceMessage(barcode);
				else
					display.displayPrice(price);
			} catch (RuntimeException rethrown) {
				display.displayGeneralErrorMessage();
				throw rethrown;
			}
		}
	}

	public interface Catalog {
		Price findPrice(String barcode);
	}

	public interface Display {
		void displayPrice(Price price);

		void displayNoPriceMessage(String barcode);
		
		void displayGeneralErrorMessage();
	}

	@Override
	protected void setUp() throws Exception {
		mockCatalog = mock(Catalog.class);
		catalog = (Catalog) mockCatalog.proxy();

		mockDisplay = mock(Display.class);
		display = (Display) mockDisplay.proxy();

		sale = new Sale(catalog, display);
	}

	public void testFoundBarcode() throws Exception {
		int irrelevantAmount = -1;

		Price price = Price.lira(irrelevantAmount);
		mockCatalog.stubs().method("findPrice").with(ANYTHING).will(
				returnValue(price));

		mockDisplay.expects(once()).method("displayPrice").with(same(price));

		sale.onBarcode("irrelevant barcode");
	}

	public void testNotFoundBarcode() throws Exception {
		String barcode = "12345";

		mockCatalog.stubs().method("findPrice").with(ANYTHING).will(
				returnValue(null));

		mockDisplay.expects(once()).method("displayNoPriceMessage").with(
				eq(barcode));

		sale.onBarcode(barcode);
	}

	public void testAskCatalogToLookUpCorrectBarcode() throws Exception {
		String barcode = "12345";

		mockCatalog.expects(once()).method("findPrice").with(eq(barcode)).will(
				returnValue(Price.lira(-762)));

		mockDisplay.setDefaultStub(new ReturnStub(null));

		sale.onBarcode(barcode);
	}

	public void testWhatIfTheCatalogBlowsUp() throws Exception {
		RuntimeException thrown = new RuntimeException("KABOOM!");
		mockCatalog.stubs().method("findPrice").with(ANYTHING).will(
				throwException(thrown));

		mockDisplay.expects(once()).method("displayGeneralErrorMessage").withNoArguments();
		
		try {
			sale.onBarcode("irrelevant barcode");
			fail("You didn't rethrow the exception?!");
		} catch (RuntimeException expected) {
			assertSame(thrown, expected);
		}
		
	}
}
