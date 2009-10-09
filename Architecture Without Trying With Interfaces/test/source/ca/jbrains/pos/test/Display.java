/**
 * 
 */
package ca.jbrains.pos.test;

public interface Display {
	void displayPrice(Price price);

	void displayNoPriceMessage(String barcode);
	
	void displayGeneralErrorMessage();
}