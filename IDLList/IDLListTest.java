package main.java.IDLList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for IDLList.
 */
public class IDLListTest {

	@Test
	void testAddIntE() {
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		dll.add(3, 5);
		assertEquals(5, dll.get(3));

		dll.add(0, 4);
		assertEquals(4, dll.get(0));

		// An exception should be thrown here
		try {
			dll.add(20, 0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, 1);
		}
	}

	@Test
	void testAddE() {
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		dll.add(1);
		assertEquals(1, dll.get(0));
		assertTrue(dll.add(1));

		dll.add(2);
		assertEquals(2, dll.get(0));
		assertTrue(dll.add(1));

		dll.add(3);
		assertEquals(3, dll.get(0));
		assertTrue(dll.add(1));

	}

	@Test
	void testAppend() {
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		dll.append(10);
		assertEquals(10, dll.get(dll.size() - 1));
		assertTrue(dll.append(10));

		dll.append(15);
		assertEquals(15, dll.get(dll.size() - 1));
		assertTrue(dll.append(15));

	}

	@Test
	void testGet() {
		// The success of the previous tests implies that get() works
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		assertEquals(9, dll.get(0));

		assertEquals(0, dll.get(dll.size() - 1));
	}

	@Test
	void testGetHead() {
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		assertEquals(dll.get(0), dll.getHead());

		IDLList<Integer> dll2 = new IDLList<>();

		for (int i = 10; i > -1; i--) {
			dll2.add(i);
		}

		assertEquals(dll2.get(0), dll2.getHead());
	}

	@Test
	void testGetLast() {
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		assertEquals(dll.get(dll.size() - 1), dll.getLast());

		IDLList<Integer> dll2 = new IDLList<>();

		for (int i = 10; i > -1; i--) {
			dll2.add(i);
		}

		assertEquals(dll2.get(dll2.size() - 1), dll2.getLast());
	}

	@Test
	void testSize() {
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		assertEquals(dll.size(), dll.size());

		dll.add(200);

		assertEquals(dll.size(), dll.size());
	}

	@Test
	void testRemove() {
		IDLList<Integer> dll = new IDLList<>();

		// An exception should be thrown here
		try {
			dll.remove();
			fail();
		} catch (IllegalStateException e) {
			assertEquals(1, 1);
		}

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		assertEquals(dll.get(0), dll.remove());

		dll.remove();
		assertEquals(dll.get(0), dll.remove());

	}

	@Test
	void testRemoveLast() {
		IDLList<Integer> dll = new IDLList<>();

		// An exception should be thrown here
		try {
			dll.removeLast();
			fail();
		} catch (IllegalStateException e) {
			assertEquals(1, 1);
		}

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		assertEquals(dll.get(dll.size() - 1), dll.removeLast());

		dll.removeLast();
		assertEquals(dll.get(dll.size() - 1), dll.removeLast());
	}

	@Test
	void testRemoveAt() {
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		// An exception should be thrown here
		try {
			dll.removeAt(100);
			fail();
		} catch (IllegalStateException e) {
			assertEquals(1, 1);
		}

		assertEquals(dll.get(0), dll.removeAt(0));

		assertEquals(dll.get(dll.size() - 1), dll.removeAt(dll.size() - 1));

		assertEquals(dll.get(5), dll.removeAt(5));
	}

	@Test
	void testRemoveE() {
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		assertFalse(dll.remove(100));

		assertTrue(dll.remove(0));

		dll.remove(0);
		assertNotEquals(dll.get(dll.size() - 1), 0);

		dll.append(9);
		dll.append(9);
		assertEquals(dll.get(dll.size() - 1), 9);
	}

	@Test
	void testToString() {
		IDLList<Integer> dll = new IDLList<>();

		for (int i = 0; i < 10; i++) {
			dll.add(i);
		}

		assertEquals("9,8,7,6,5,4,3,2,1,0,", dll.toString());
	}
}