package main.java.Maze;

/**
 * Implements PairInt for use in maze backtracking. 
 * @author Liam Brew
 * @section CS 284A
 * @pledge	I pledge my honor that I have abided by the Stevens Honor System
 *
 */
public class PairInt {
	// Data fields
	private int x;
	private int y;

	// Constructor
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Methods
	/**
	 * Returns the value of x.
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the value of y.
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the value of x.
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the value of y.
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Determines if the parameter object is equal to this PairInt.
	 */
	public boolean equals(Object p) {
		if (p instanceof PairInt) {
			PairInt compare = (PairInt) p;
			boolean analysis = compare.getX() == this.getX() && compare.getY() == this.getY();
			return analysis;
		}
		return false;
	}

	/**
	 * Returns a string representation of the PairInt.
	 */
	public String toString() {
		String result = "(" + this.getX() + ", " + this.getY() + ")";
		return result;
	}

	/**
	 * Returns a copy of this PairInt.
	 * 
	 * @return PairInt copy.
	 */
	public PairInt copy() {
		PairInt copy = new PairInt(this.getX(), this.getY());
		return copy;
	}
}
