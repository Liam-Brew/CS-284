package main.java.BinaryNumber;

/**
 * Class representing operations of binary numbers. 
 * @author Liam Brew
 * @section CS 284 A
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
public class BinaryNumber {

	private int[] data;
    private int length;
    
	// Constructors

	/**
	 * Constructs BinaryNumber object of length length composed entirely of 0s.
	 * @param length Length of the BinaryNumber (how many 0s).
	 * @throws Exception Length must be a positive integer.
	 */
	BinaryNumber(int length) throws Exception {
		if (length < 0 || length == 0) {
			throw new Exception("The binary number's length needs to be a positive integer.");
		}
		int[] placeholder = new int[length];
		this.length = length;
		this.data = placeholder;
	}

	/**
	 * Constructs BinaryNumber object from the input string.
	 * @param str The value of the BinaryNumber.
	 * @throws Exception String must be only binary characters.
	 */
	BinaryNumber(String str) throws Exception {
		int l = str.length();
		int[] holder = new int[l];
		for (int i = 0; i < l; i++) {
			char c = str.charAt(i);
			int n = Character.getNumericValue(c);
			if ((n != 0 && n != 1) || (l == 0 || l < 0)) {
				throw new Exception(
						"The string must contain only binary characters and be of a length of a positive integer.");
			}
			holder[i] = n;
		}
		// Updates length
		this.length = l;

		// Copies holder array to data array
		this.data = holder;
	}

	// Operations
	/**
	 * This method updates the length of the BinaryNumber.
	 * @return Length of BinaryNumber.
	 */
	public int getLength() {
		this.length = this.data.length;
		return this.length;
	}

	/**
	 * This method returns the value located at a specific index of the BinaryNumber.
	 * @param index The index to search.
	 * @return The value at the index.
	 */
	public int getDigit(int index) {
		try {
			return this.data[index];
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException("Error: index " + index + " is out of bounds.");
		}
	}

	/**
	 * This method returns the integer array representing the BinaryNumber.
	 * @return BinaryNumber array.
	 */
	public int[] getInnerArray() {
		return this.data;
	}

	/**
	 * This method calculates the bitwise or of two BinaryNumbers.
	 * @param bn1 BinaryNumber 1.
	 * @param bn2 BinaryNumber 2.
	 * @return Bitwise or of bn1 and bn2.
	 * @throws Exception Both BinaryNumbers must be of the same length.
	 */
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) throws Exception {
		if (bn1.getLength() != bn2.getLength()) {
			throw new Exception("The lengths of both binary numbers must be equal.");
		}
		int[] bwor = new int[bn1.getLength()];
		for (int i = 0; i < bn1.getLength(); i++) {
			if (bn1.getDigit(i) == 1 || bn2.getDigit(i) == 1) {
				bwor[i] = 1;
			} else {
				bwor[i] = 0;
			}
		}
		return bwor;
	}

	/**
	 * This method calculates the bitwise and of two BinaryNumbers.
	 * @param bn1 BinaryNumber 1.
	 * @param bn2 BinaryNumber 2.
	 * @return Bitwise and of bn1 and bn2.
	 * @throws Exception Both BinaryNumbers must be of the same length.
	 */
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) throws Exception {
		if (bn1.getLength() != bn2.getLength()) {
			throw new Exception("The lengths of both binary numbers must be equal.");
		}
		int[] bwand = new int[bn1.getLength()];
		for (int i = 0; i < bn1.getLength(); i++) {
			if (bn1.getDigit(i) == 1 && bn2.getDigit(i) == 1) {
				bwand[i] = 1;
			} else {
				bwand[i] = 0;
			}
		}
		return bwand;
	}

	/**
	 * This method shifts all digits in a BinaryNumber left or right depending on the direction.
	 * @param direction -1 indicates a left shift; 1 indicates a right shift.
	 * @param amount The amount of digits to be shifted.
	 * @throws Exception The amount must be a positive integer.
	 * @throws Exception The direction must be either -1 or 1.
	 */
	public void bitShift(int direction, int amount) throws Exception {
		// Addition length.
		int la = this.getLength() + amount;

		// Subtraction length.
		int ls = this.getLength() - amount;

		if (amount <= 0) {
			throw new Exception("The amount must be a positive integer.");
		}
		if (direction == -1) {
			// Left shift.
			int[] copy = new int[la];
			for (int i = 0; i < this.getLength(); i++) {
				// Fills with old data.
				copy[i] = this.getDigit(i);
			}
			for (int i = this.getLength(); i < la; i++) {
				// Adds 0s.
				copy[i] = 0;
			}
			this.data = copy;

		} else if (direction == 1) {
			// Right shift.
			int[] copy = new int[ls];
			for (int i = 0; i < ls; i++) {
				// Copies only to new length.
				copy[i] = this.getDigit(i);
			}
			this.data = copy;
		} else {
			// Neither.
			throw new Exception("Direction must be either -1 or 1");
		}
	}

	/**
	 * This method adds two binary numbers. 
	 * @param aBinaryNumber The second BinaryNumber to be added.
	 */
	public void add(BinaryNumber aBinaryNumber) {
		if (this.length > aBinaryNumber.getLength()) {
			// This is longer.
			int l = this.length - aBinaryNumber.getLength();
			aBinaryNumber.prepend(l);
		} else if (this.length < aBinaryNumber.getLength()) {
			// This is shorter.
			int l = aBinaryNumber.getLength() - this.length;
			this.prepend(l);
		}
		int[] binaryAdd = new int[this.length];
		// Carry counter.
		int carry = 0;
		for (int i = this.length - 1; i > -1; i--) {
			// Adder counter.
			int adder = this.data[i] + aBinaryNumber.getDigit(i) + carry;
			if (adder == 0) {
				// Both numbers and the carry are 0.
				binaryAdd[i] = 0;
				carry = 0;
			} else if (adder == 1) {
				// There's one 1.
				binaryAdd[i] = 1;
				carry = 0;
			} else if (adder == 2) {
				// There are two ones.
				binaryAdd[i] = 0;
				carry = 1;
			} else if (adder == 3) {
				// Both numbers are 1 and the carry is 1.
				binaryAdd[i] = 1;
				carry = 1;
			}

		}
		this.data = binaryAdd;
		this.length = binaryAdd.length;

		if (carry == 1) {
			this.prepend(1);
			this.data[0] = 1;
			this.length += 1;
		}

	}

	/**
	 *This method returns the BinaryNumber as a string.
	 */
	public String toString() {
		String str = new String();
		for (int i = 0; i < this.getLength(); i++) {
			str += this.getDigit(i);
		}
		return str;
	}

	
	/**
	 * This method transforms a BinaryNumber to its decimal notation. I really wish I discovered
	 * this built-in method before I spent so much time messing with loops :*(. 
	 * @return The decimal notation of the BinaryNumber
	 */
	public int toDecimal() {
		int decimal = Integer.parseInt(this.toString(), 2);
		return decimal;
	}

	/**
	 * This method prepends the amount of 0s to the BinaryNumber.
	 * @param amount How many 0s to prepend.
	 */
	public void prepend(int amount) {
		int l = this.getLength() + amount;
		int[] copy = new int[l];

		// Copies existing array over.
		for (int k = amount; k < l; k++) {
			// Picks up after the added 0s.
			copy[k] = this.getDigit(k - amount);

		}
		this.data = copy;
		this.length += amount;
	}

}