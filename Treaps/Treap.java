package Treaps;

import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

/**
 * @author Liam Brew
 * @section CS 284A
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
public class Treap<E extends Comparable<E>> {
	private static class Node<E> {
		// Data fields
		public E data; // key for search
		public int priority; // random heap priority
		public Node<E> left;
		public Node<E> right;

		// Constructor
		/**
		 * Generates a node with the passed data and priorty value. Left and right
		 * children are null.
		 * 
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority) {
			if (data == null)
				throw new IllegalArgumentException("Data can't be null!");
			this.data = data;
			this.priority = priority;
			left = right = null;
		}

		// Methods
		/**
		 * Performs a right rotation, returning the reference to the root of the result.
		 * 
		 * @return Root of the result.
		 */
		public Node<E> rotateRight() {
			if (left == null)
				return this;
			Node<E> pivot = left;
			left = left.right;
			pivot.right = this;
			return pivot;
		}

		/**
		 * Performs a left rotation, returning the reference to the root of the result.
		 * 
		 * @return Root of the result.
		 */
		public Node<E> rotateLeft() {
			if (right == null)
				return this;
			Node<E> pivot = right;
			right = right.left;
			pivot.left = this;
			return pivot;
		}

		/**
		 * Puts the contents of the node into a string with the format (data, priority).
		 */
		public String toString() {
			return "(key=" + data.toString() + ", priority=" + priority + ")";
		}
	}

	// Data fields
	private Random priorityGenerator;
	private Node<E> root;
	private int size;
	private HashSet<Integer> priorities;

	// Constructors
	/**
	 * Creates an empty tree with a random priorityGenerator.
	 */
	public Treap() {
		priorityGenerator = new Random();
		size = 0;
		priorities = new HashSet<Integer>();
	}

	/**
	 * Creates an empty tree and instantiates the priorityGenerator using seed.
	 * 
	 * @param seed
	 */
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		size = 0;
		priorities = new HashSet<Integer>();
	}

	// Methods
	/**
	 * Inserts the given element into the key by creating a new node.
	 * 
	 * @param key
	 * @param priority
	 * @return
	 */
	public boolean add(E key, int priority) {
		if (priorities.contains(priority))
			throw new IllegalStateException("Priority is repeated!");
		priorities.add(priority);
		if (root == null) {
			root = new Node<E>(key, priority);
			size++;
			return true;
		} else {
			Node<E> temp = new Node<E>(key, priority);
			Node<E> current = root;

			Stack<Node<E>> path = new Stack<Node<E>>();

			while (current != null) {
				if (current.data.compareTo(key) == 0)
					return false; // node already exists
				if (current.data.compareTo(key) > 0) {
					path.push(current);
					if (current.left == null) {
						current.left = temp;
						reheap(temp, path);
						return true;
					} else
						current = current.left;
				} else {
					path.push(current);
					if (current.right == null) {
						current.right = temp;
						reheap(temp, path);
						return true;
					} else {
						current = current.right;
					}
				}
			}
			return false;
		}
	}

	/**
	 * Wrapper function for add().
	 * 
	 * @param key Data to be added.
	 * @return True if a node with the key was added; False if there already exists
	 *         a node.
	 */
	public boolean add(E key) {
		int prio = priorityGenerator.nextInt();
		while (priorities.contains(prio))
			prio = priorityGenerator.nextInt();
		return add(key, prio);
	}

	/**
	 * Reheaps the tree using the rotation function of its nodes.
	 * 
	 * @param current The current Node.
	 * @param path    The list of all nodes between the root and the current node.
	 */
	private void reheap(Node<E> current, Stack<Node<E>> path) {
		while (!path.isEmpty()) {
			Node<E> parent = path.pop();
			if (parent.priority < current.priority) { // max heap
				if (parent.data.compareTo(current.data) > 0)
					current = parent.rotateRight();
				else
					current = parent.rotateLeft();
				if (!path.isEmpty())
					if (path.peek().left == parent)
						path.peek().left = current;
					else
						path.peek().right = current;
				else
					this.root = current;
			} else
				break;
		}
	}

	/**
	 * Deletes the node within the treap..
	 * 
	 * @param key Data to delete.
	 * @param n   Current node.
	 * @return Boolean value indicating if the node was found and deleted.
	 */
	private Node<E> delete(E key, Node<E> current) {
		if (current == null) {
			return current;
		} else {
			if (current.data.compareTo(key) < 0) {
				current.right = delete(key, current.right);
			} else {
				if (current.data.compareTo(key) > 0) {
					current.left = delete(key, current.left);
				} else {
					if (current.right == null) {
						current = current.left;
					} else if (current.left == null) {
						current = current.right;
					} else {
						if (current.right.priority < current.left.priority) {
							current = current.rotateRight();
							current.right = delete(key, current.right);
						} else {
							current = current.rotateLeft();
							current.left = delete(key, current.left);
						}
					}
				}
			}
		}
		return current;
	}

	/**
	 * Wrapper function for delete().
	 * 
	 * @param key data to delete.
	 * @return boolean indicating if deletion was successful.
	 */
	public boolean delete(E key) {
		if (root == null) {
			return false;
		} else if(!find(key)) {
			return false;
		} else {
			root = delete(key, root);
			return true;
		}
	}

	/**
	 * Searches the treap for the given key.
	 * 
	 * @param current Current node.
	 * @param key     Data to search for.
	 * @return Boolean indicating if the given data is present within the treap.
	 */
	private boolean find(Node<E> current, E key) {
		if (current == null) {
			return false;
		} else {
			int compare = current.data.compareTo(key);
			if (compare == 0) {
				return true;
			} else {
				return find(current.right, key) || find(current.left, key);
			}
		}
	}

	/**
	 * Wrapper function for find().
	 * 
	 * @param key Data to look for.
	 * @return Boolean indicating if the given data is present within the treap.
	 */
	public boolean find(E key) {
		return find(root, key);
	}

	/**
	 * Generates a preorder traversal of the treap in String form.
	 * 
	 * @param current Current node.
	 * @param n       Counter of branches.
	 * @return Preorder traversal in string form.
	 */
	private StringBuilder toString(Node<E> current, int n) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < n; i++) {
			b.append(" ");
		}
		if (current == null)
			return b.append("null\n");
		else {
			b.append(current.toString() + "\n");
			b.append(toString(current.left, n + 1));
			b.append(toString(current.right, n + 1));
			return b;
		}
	}

	/**
	 * Wrapper function of toString().
	 */
	public String toString() {
		return toString(root, 1).toString();
	}

	public static void main(String[] args) {
		Treap testTree = new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		
		System.out.println(testTree.toString());
		
		testTree.delete(1);
		
//		testTree.add("B");
//		testTree.add("A");
//		testTree.add("Z");
//		testTree.add("C");
//		testTree.add("D");

		System.out.println(testTree.toString());
	}
}
