/**
 * 
 */
package ca.jbrains.pos.test;


public class Sale {
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