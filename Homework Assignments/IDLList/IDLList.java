

import java.util.ArrayList;

/**
 * Class that handles basic methods of double linked lists.
 * 
 * @author Liam Brew
 * @section CS 284 A
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 * @param <E>
 */
public class IDLList<E> {
	// Inner Classes

	/**
	 * Contains data fields for stored data, next node and previous node.
	 * 
	 * @author Liam Brew Class representing the node of a double linked list.
	 * @param <E>
	 */
	private class Node<F> {

        private F data;
        private Node<F> next;
        private Node<F> prev;

        /**
         * Creates a node with no next or previous pointers.
         * 
         * @param data
         */
        public Node(F data) {
            this.data = data;
            next = null;
            prev = null;
        }

        /**
         * Creates a node with next and previous pointers.
         * 
         * @param data
         * @param next
         * @param prev
         */
        public Node(F data, Node<F> next, Node<F> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
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

		if (index == 0) { //Head
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
	 * Adds elem as the new last element of the list (as the tail).
	 * 
	 * @param elem The data to be added
	 * @return Always true (for purposes of this assignment).
	 */
	public boolean append(E elem) {
		if (head == null) { // Empty list
            head = new Node<E>(elem);
            tail = head;
            size++;
            return indices.add(head);
        }

        if (head == tail) { // Singleton list
            tail = new Node<E>(elem, null, head);
            head.next = tail;
            size++;
            return indices.add(tail);
		}
		
        tail.next = new Node<E>(elem, null, tail);
        tail = tail.next;
        size++;
        return indices.add(tail);
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
		if (head == null)
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
		if (head == null) { //Empty list
            throw new IllegalStateException();
        }

        if (head == tail) { //Singleton List
            Node<E> temp = head;
            head = null;
            tail = null;
            size--;
            indices.clear();
            return temp.data;
        }

        Node<E> temp = head;
        head = head.next;
        indices.remove(0);
        size--;
        return temp.data;
	}

	/**
	 * Removes and returns the element at the tail,
	 * 
	 * @return Data of Tail
	 */
	public E removeLast() {
		if (head == null) { //Empty list
            throw new IllegalStateException();
        }

        if (head == tail) { //Singleton list
            Node<E> temp = tail;
            head = null;
            tail = null;
            size = 0;
            indices.clear();
            return temp.data;
        }

        Node<E> temp = tail;
        tail = tail.prev;
        tail.next = null;
        indices.remove(size - 1);
        size--;
        return temp.data;
	}

	/**
	 * Removes and returns the element at the given index.
	 * 
	 * @param index The index at which removal occurs.
	 * @return The data of the node located at that index.
	 */
	public E removeAt(int index) {
		if (index < 0 || index > size) { //Illegal index
            throw new IllegalStateException("Illegal Index!");
        }

        if (index == 0) { // Head
            return remove();
        }

        if (index == size - 1) { // Tail
            return removeLast();
        }

        Node<E> current = indices.remove(index);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return current.data;
	}

	/**
	 * Removes the first occurrence of the element. Returns a boolean indicating if
	 * that element was found
	 * 
	 * @param elem The data to be removed.
	 * @return Boolean representing if the data was located.
	 */
	public boolean remove(E elem) {
		if (elem.equals(head.data)) { // remove the first element
			remove();
			return true;
		}

		if (elem.equals(tail.data)) { // remove the last element
			removeLast();
			return true;
		}

		Node<E> current = head;
		int index = 0;
		while (current != null) {
			if (current.data.equals(elem)) {
				current.prev.next = current.next;
				current.next.prev = current.prev;
				indices.remove(index);
				size--;
				return true;
			}
			current = current.next;
			index++;
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