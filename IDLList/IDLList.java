package main.java.IDLList;

import java.util.ArrayList;

/**
 * Class that handles basic methods of double linked lists.
 * @author Liam Brew
 * @section CS 284 A
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 * @param <E>
 */
public class IDLList<E> {
	// Inner Classes

	/**
	 * @author Liam Brew Class representing the node of a double linked list.
	 *         Contains data fields for stored data, next node and previous node.
	 *
	 * @param <E>
	 */
	public static class Node<E> {
		// Data fields
		private E data;
		private Node<E> next;
		private Node<E> prev;

		// Constructors
		/**
		 * Constructor that creates a node holding elem.
		 * 
		 * @param elem
		 */
		public Node(E elem) {
			this.data = elem;
			this.prev = null;
			this.next = null;
		}

		/**
		 * Constructor that creates a node holding elem, with a previous node of prev
		 * and a next node of next.
		 * 
		 * @param elem
		 * @param prev
		 * @param next
		 */
		public Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	// Data Fields
	private Node<E> head;
	private Node<E> tail;

	private int size;

	private ArrayList<Node<E>> indices;

	// Constructors

	/**
	 * Creates an empty double-linked list.
	 */
	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<>();
	}

	// Methods

	/**
	 * Adds elem at position index. Uses the index array for fast access.
	 * 
	 * @param index The location at which to add the data.
	 * @param elem  The data to be added.
	 * @return Always true (for purposes of this assignment).
	 */
	public boolean add(int index, E elem) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) { // Adds to start of list
			add(elem);
		} else {
			Node<E> current = indices.get(index);
			Node<E> newCurrent = new Node<>(elem, current, current.prev);

			current.prev.next = newCurrent;
			current.prev = newCurrent;

			size++;
			indices.add(index, newCurrent);
		}
		return true;
	}

	/**
	 * Adds elem at the head of the DLL.
	 * 
	 * @param elem The data to be added.
	 * @return Always true (for purposes of this assignment).
	 */
	public boolean add(E elem) {
		if (head == null) { // Empty list
			head = new Node<E>(elem);
			tail = head;
		} else if (head == tail) { // Singleton list
			head = new Node<E>(elem, tail, null);
			tail.prev = head;
		} else {
			head = new Node<E>(elem, head, null);
			head.next.prev = head;
		}

		indices.add(0, head);
		size++;
		return true;
	}

	/**
	 * Adds elem as the new last element of the list (as the tail).
	 * 
	 * @param elem The data to be added
	 * @return Always true (for purposes of this assignment).
	 */
	public boolean append(E elem) {
		Node<E> node = new Node<E>(elem, tail, null);
		if (head == null) {// Empty list
			head = new Node<E>(elem);
			tail = head;
			size++;
			return indices.add(head);
		}
		if (head != null) {
			tail.next = node;
		} else {
			head = node;
		}
		tail = node;
		indices.add(size - 1, node);
		size++;
		return true;
	}

	/**
	 * Returns the object at position index from the head.
	 * 
	 * @param index The location to search.
	 * @return The data of the node located at that index.
	 */
	public E get(int index) {
		// Illegal arguments (empty list, negative index or too large index)
		if (head == null || index < 0 || index > size - 1) {
			throw new IllegalArgumentException();
		} else {
			// Get head
			if (index == 0) {
				return head.data;
			} else {
				// Iterate to index
				Node<E> current = head;
				int i = 0;
				while (i < index) {
					current = current.next;
					i++;
				}
				return current.data;
			}
		}
	}

	/**
	 * Returns the object at the head.
	 * 
	 * @return Data of head.
	 */
	public E getHead() {
		// Empty list
		if (size == 0)
			throw new IllegalArgumentException("Empty list!");
		return head.data;
	}

	/**
	 * Returns the object at the tail.
	 * 
	 * @return Data of tail.
	 */
	public E getLast() {
		// Empty list
		if (size == 0)
			throw new IllegalArgumentException("Empty list!");
		return tail.data;
	}

	/**
	 * Returns the size of the list.
	 * 
	 * @return List size.
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes and returns the element at the head.
	 * 
	 * @return Data of head.
	 */
	public E remove() {
		// Empty list
		if (head == null)
			throw new IllegalStateException("Empty list!");

		// List contains 1 element...
		if (size == 1) {
			// ...removes only element of the list
			head = tail = null;
			indices.clear();
			size--;
			return null;
		}

		E headData = head.data;
		head = head.next;
		head.prev = null;
		size--;
		indices.remove(0);
		return headData;
	}

	/**
	 * Removes and returns the element at the tail,
	 * 
	 * @return Data of Tail
	 */
	public E removeLast() {
		// Empty list
		if (head == null)
			throw new IllegalStateException("Empty list!");

		// List contains 1 element...
		if (size == 1) {
			// ...removes only element of the list
			Node<E> current = tail;
			head = tail = null;
			indices.clear();
			size = 0;
			return current.data;
		}

		Node<E> current = tail;
		tail = tail.prev;
		tail.next = null;
		indices.remove(size - 1);
		size--;
		return current.data;
	}

	/**
	 * Removes and returns the element at the given index.
	 * 
	 * @param index The index at which removal occurs.
	 * @return The data of the node located at that index.
	 */
	public E removeAt(int index) {
		// Empty list
		if (head == null)
			throw new IllegalStateException("Empty list!");

		// Index out of bounds
		if (index > size - 1)
			throw new IllegalStateException("Index out of bounds!");

		// Remove first
		if (index == 0)
			return this.remove();

		// Remove Last
		if (index == size - 1)
			return this.removeLast();

		// Iterates to find the index
		Node<E> current = head;
		int i = 0;
		while (i < index) {
			current = current.next;
			i++;
		}

		E data = current.data;
		current.prev.next = current.next;
		size--;
		indices.remove(index);
		return data;
	}

	/**
	 * Removes the first occurrence of the element. Returns a boolean indicating if
	 * that element was found
	 * 
	 * @param elem The data to be removed.
	 * @return Boolean representing if the data was located.
	 */
	public boolean remove(E elem) {
		// Empty list... not found
		if (head == null)
			return false;

		// Iterates through the list
		Node<E> current = head;
		int i = 0;
		while (i < size) {
			if (current.data == elem) {
				this.removeAt(i);
				return true;
			}
			current = current.next;
			i++;
		}
		return false;
	}

	/**
	 * Presents a string representation of the list.
	 *
	 */
	public String toString() {
		Node<E> current = head;
		String s = "";
		while (current != null) {
			s = s + current.data + ",";
			current = current.next;
		}
		return s;
	}
}