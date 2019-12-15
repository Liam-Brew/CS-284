package Homework.Treaps;

import Treaps.Treap;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;

import org.junit.Test;

/**
 * @author Liam Brew
 * @section CS 284A
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
public class TreapTest {

	private final char[] keys;

	private long seed = 450999099L;

	public TreapTest() {
		keys = new char[] { 'a', 'x', 'b', 'y', 'n', 'k', 'c', 'j', 'i', 'h', 'z', 'w', 'm', 'd', 'q', 'p', 't', 'u' };
	}

	/**
	 * Tests the add() function.
	 */
	@Test
	public void testAdd() {
		Treap<Character> t = new Treap<>(seed);

		for (int i = 0; i < keys.length; i++) {
			assertTrue(t.add(keys[i]));
			assertTrue(t.find(keys[i]));
		}

		for (int i = keys.length - 1; i >= 0; i--) {
			// keys[i] should exist
			assertTrue(t.find(keys[i]));

			// keys[i] should not be added (already exists)
			assertFalse(t.add(keys[i]));
		}

		// These values were never added and should not be found.
		assertFalse(t.find('e'));
		assertFalse(t.find('g'));
		assertFalse(t.find('l'));

		// Tests the example provided in the instruction set.
		String expected = " (key=1, priority=84)\n" + "  null\n" + "  (key=5, priority=83)\n"
				+ "   (key=2, priority=31)\n" + "    null\n" + "    (key=4, priority=19)\n"
				+ "     (key=3, priority=12)\n" + "      null\n" + "      null\n" + "     null\n"
				+ "   (key=6, priority=70)\n" + "    null\n" + "    (key=7, priority=26)\n" + "     null\n"
				+ "     null\n";

		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);

		assertEquals(testTree.toString(), expected);
		
		assertTrue(testTree.find(4));
		assertTrue(testTree.find(2));
		assertTrue(testTree.find(6));
		assertTrue(testTree.find(1));
		assertTrue(testTree.find(3));
		assertTrue(testTree.find(5));
		assertTrue(testTree.find(7));
		
		assertFalse(testTree.find(98));
	}

	/**
	 * Tests the delete() and toString() function.
	 */
	@Test
	public void testDelete() {
		Treap<Character> t = new Treap<>(seed);

		for (int i = 0; i < keys.length; i++) {
			assertTrue(t.add(keys[i]));
			assertTrue(t.find(keys[i]));
		}

		// Removes the values from the treap
		for (int i = keys.length - 1; i >= 0; i--) {
			assertTrue("Error deleting: " + keys[i], t.delete(keys[i]));
			assertFalse("Not deleted: " + keys[i], t.find(keys[i]));
		}

		// The string representation of the treap should be empty here as all of its
		// nodes have been deleted.
		String noVal = " null\n";
		assertTrue(t.toString().equals(noVal));
	}

	/**
	 * Tests the priority generation and indexing.
	 */
	@Test
	public void testPriorities() {

		Treap<Character> t = new Treap<>();

		Random priorities = new Random(seed);
		
		HashSet<Integer> used = new HashSet<>();

		for (int i = 0; i < keys.length; i++) {
			int priority = priorities.nextInt(20);
			while (used.contains(priority))
				priority = priorities.nextInt(20);

			used.add(priority);
			
			assertTrue(t.add(keys[i], priority));
			assertEquals(false, t.find('e'));
		}

		assertFalse("Key was added with invalid priority.", t.find('e'));
		assertTrue("Key was added with invalid priority.", t.add('e', priorities.nextInt(100)));
	}
}