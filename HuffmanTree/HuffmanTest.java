package HuffmanTree;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

//Pledge: I pledge my honor that I have abided by the Stevens Honor System.
//Name: Liam Brew

public class HuffmanTest {

	@Test
	public void stringTest() {
		// Given test
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s);

		Arrays.deepToString(t.efficientEncode("string"));

		String result = "(freq= 30)\n (freq= 13)\n  (freq= 6)\n   [value= t,freq= 3]\n   [value= n,freq= 3]\n  (freq= 7)\n   [value= e,freq= 3]\n   (freq= 4)\n    (freq= 2)\n     [value= s,freq= 1]\n     [value= a,freq= 1]\n    (freq= 2)\n     [value= m,freq= 1]\n     [value= d,freq= 1]\n (freq= 17)\n  (freq= 8)\n   (freq= 4)\n    (freq= 2)\n     [value= u,freq= 1]\n     [value= w,freq= 1]\n    (freq= 2)\n     [value= c,freq= 1]\n     [value= i,freq= 1]\n   [value= o,freq= 4]\n  (freq= 9)\n   (freq= 4)\n    (freq= 2)\n     [value= S,freq= 1]\n     [value= y,freq= 1]\n    (freq= 2)\n     [value= r,freq= 1]\n     [value= g,freq= 1]\n   [value=  ,freq= 5]\n";

		assertEquals(result, t.toString());

		// New test
		String x = "lets test this tree";
		HuffmanTree y = new HuffmanTree(x);

		Arrays.deepToString(y.efficientEncode("tree"));

		String answer = "(freq= 19)\n" + " (freq= 8)\n" + "  [value= e,freq= 4]\n" + "  (freq= 4)\n" + "   (freq= 2)\n"
				+ "    [value= h,freq= 1]\n" + "    [value= i,freq= 1]\n" + "   (freq= 2)\n"
				+ "    [value= l,freq= 1]\n" + "    [value= r,freq= 1]\n" + " (freq= 11)\n" + "  [value= t,freq= 5]\n"
				+ "  (freq= 6)\n" + "   [value= s,freq= 3]\n" + "   [value=  ,freq= 3]\n";

		assertEquals(answer, y.toString());
	}

	@Test
	public void btsTest() {
		// Given test
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s);

		String result = "101";
		Boolean[] bool1 = { true, false, true };
		assertEquals(result, t.bitsToString(bool1));

		// New test
		String x = "string";
		HuffmanTree y = new HuffmanTree(x);

		String answer = "010";
		Boolean[] bool2 = { false, true, false };
		assertEquals(answer, y.bitsToString(bool2));
	}

	@Test
	public void encodeTest() {
		// Given test
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s);

		String result = "[false, true, true, false, false, false, false, false, true, true, false, true, false, true, false, false, true, true, false, false, true, true, true, false, true, true]";

		assertEquals(result, Arrays.deepToString(t.encode("string")));

		// New test
		String x = "lets build a tree";
		HuffmanTree y = new HuffmanTree(x);

		String answer = "[true, false, false, true, false, true, false, true, true, true, true, true, true]";
		assertEquals(answer, Arrays.deepToString(y.encode("tree")));

		// Exception
		try {
			y.efficientEncode("free");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void efficientEncodeTest() {
		// Same tests as the encoding test as the two functions should accomplish the
		// same thing.

		// Given test
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s);

		String result = "[false, true, true, false, false, false, false, false, true, true, false, true, false, true, false, false, true, true, false, false, true, true, true, false, true, true]";

		assertEquals(result, Arrays.deepToString(t.efficientEncode("string")));

		// New test
		String x = "lets build a tree";
		HuffmanTree y = new HuffmanTree(x);

		String answer = "[true, false, false, true, false, true, false, true, true, true, true, true, true]";
		assertEquals(answer, Arrays.deepToString(y.efficientEncode("tree")));

		// Exception
		try {
			y.efficientEncode("free");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void decodeTest() {
		// Given test
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s);

		Boolean[] bool1 = { false, true, true, false, false, false, false, false, true, true, false, true, false, true,
				false, false, true, true, false, false, true, true, true, false, true, true };

		assertEquals("string", t.decode(bool1));

		// New test
		String x = "lets build a tree";
		HuffmanTree y = new HuffmanTree(x);

		Boolean[] bool2 = { true, false, false, true, false, true, false, true, true, true, true, true, true };

		assertEquals("tree", y.decode(bool2));

		// Exception
		try {
			Boolean[] bool3 = { true, true, true, true, true };
			y.decode(bool3);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
}
