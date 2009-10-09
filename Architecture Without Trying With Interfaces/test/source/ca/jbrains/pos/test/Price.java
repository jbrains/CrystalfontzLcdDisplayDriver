/**
 * 
 */
package ca.jbrains.pos.test;

public class Price {
	private final int lira;

	public Price(int lira) {
		this.lira = lira;
	}

	public static Price lira(int lira) {
		return new Price(lira);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Price) {
			Price that = (Price) other;
			return this.lira == that.lira;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return lira;
	}

	@Override
	public String toString() {
		return "a Price with " + lira + " lira";
	}
}