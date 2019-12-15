import java.util.*;

/*
 * Instructions: 
 * First: Read through the assignment specification, make sure you understand what the assignment is asking for.
 * Second: Test you code.
 */

// Pledge: I pledge my honor that I have abided by the Stevens Honor System.
// Name: Liam Brew

/**
 * HW6 CS284 Fall 2019 Implements a Huffman encoding tree. The included code has
 * been commented for the student's benefit, feel free to read through it.
 */
public class HuffmanTree {

	// ******************** Start of Stub Code ******************** //
	// ************************************************************ //
	/**
	 * Node<E> is an inner class and it is abstract. There will be two kinds of
	 * Node, one for leaves and one for internal nodes.
	 */
	abstract static class Node implements Comparable<Node> {
		/** The frequency of all the items below this node */
		protected int frequency;

		public Node(int freq) {
			this.frequency = freq;
		}

		/** Needed for the Minimum Heap used later in this stub. */
		public int compareTo(Node other) {
			return this.frequency - other.frequency;
		}
	}

	/** Leaves of a Huffman tree contain the data items */
	protected static class LeafNode extends Node {
		// Data Fields
		/** The data in the node */
		protected char data;

		/** Constructor to create a leaf node (i.e. no children) */
		public LeafNode(char data, int freq) {
			super(freq);
			this.data = data;
		}

		/** toString method */
		public String toString() {
			return "[value= " + this.data + ",freq= " + frequency + "]";
		}
	}

	/**
	 * Internal nodes contain no data, just references to left and right subtrees
	 */
	protected static class InternalNode extends Node {
		/** A reference to the left child */
		protected Node left;
		/** A reference to the right child */
		protected Node right;

		/** Constructor to create an internal node */
		public InternalNode(Node leftC, Node rightC) {
			super(leftC.frequency + rightC.frequency);
			left = leftC;
			right = rightC;
		}

		public String toString() {
			return "(freq= " + frequency + ")";
		}
	}

	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable"
	// in the usual sense)
	private static final int codex_size = 256;

	/* Data Fields for Huffman Tree */
	private Node root;

	public HuffmanTree(String s) {
		root = buildHuffmanTree(s);
	}

	/**
	 * Returns the frequencies of all characters in s.
	 * 
	 * @param s
	 * @return
	 */
	public static int[] frequency(String s) {
		int[] freq = new int[codex_size];
		for (char c : s.toCharArray()) {
			freq[c]++;
		}
		return freq;
	}

	/**
	 * Builds the actual Huffman tree for that particular string.
	 * 
	 * @param s
	 * @return
	 */
	private static Node buildHuffmanTree(String s) {
		int[] freq = frequency(s);

		// Create a minimum heap for creating the Huffman Tree
		// Note to students: You probably won't know what this data structure
		// is yet, and that is okay.
		PriorityQueue<Node> min_heap = new PriorityQueue<Node>();

		// Go through and create all the nodes we need
		// as in, all the nodes that actually appear in our string (have a frequency
		// greater then 0)
		for (int i = 0; i < codex_size; i++) {
			if (freq[i] > 0) {
				// Add a new node (for that character) to the min_heap, notice we have to cast
				// our int i into a char.
				min_heap.add(new LeafNode((char) i, freq[i]));
			}
		}

		// Edge case (string was empty)
		if (min_heap.isEmpty()) {
			throw new NullPointerException("Cannot encode an empty String");
		}

		// Now to create the actual Huffman Tree
		// NOTE: this algorithm is a bit beyond what we cover in cs284,
		// you'll see this in depth in cs385

		// Merge smallest subtrees together
		while (min_heap.size() > 1) {
			Node left = min_heap.poll();
			Node right = min_heap.poll();
			Node merged_tree = new InternalNode(left, right);
			min_heap.add(merged_tree);
		}

		// Return our structured Huffman Tree
		return min_heap.poll();
	}

	// ******************** End of Stub Code ******************** //
	// ********************************************************** //

	/**
	 * Returns a bit representation of the inserted boolean list as a string.
	 * 
	 * @param encoding boolean list.
	 * @return bit representation of boolean list as a string.
	 */
	public String bitsToString(Boolean[] encoding) {
		StringBuilder b = new StringBuilder();
		for (Boolean bool : encoding)
			if (bool) {
				b.append("1");
			} else {
				b.append("0");
			}
		return b.toString();
	}

	/**
	 * Returns a string representation of the tree. Leaf nodes provide both the
	 * Character and the frequency. Internal node only contains the frequency.
	 * 
	 * @param current current node.
	 * @param n       indent counter.
	 * @return string representation of the tree.
	 */
	private StringBuilder toString(InternalNode current, int n) {
		StringBuilder b = new StringBuilder();
		StringBuilder indent = new StringBuilder();

		for (int i = 0; i < n; i++)
			indent.append(" ");

		b.append(indent.toString());
		b.append(current.toString() + "\n");

		Node left = current.left;
		Node right = current.right;

		if (left instanceof LeafNode)
			b.append(indent + " " + left.toString() + "\n");
		else
			b.append(toString((InternalNode) left, n + 1));

		if (right instanceof LeafNode)
			b.append(indent + " " + right.toString() + "\n");
		else
			b.append(toString((InternalNode) right, n + 1));

		return b;
	}

	/**
	 * Recursive wrapper method of toString().
	 */
	public String toString() {
		if (root instanceof InternalNode) {
			return toString((InternalNode) root, 0).toString();
		} else {
			return root.toString();
		}
	}

	/**
	 * Decodes the bits and collects the output characters one-by-one and assembles
	 * them into the returned string.
	 * 
	 * @param coding list of booleans to decode.
	 * @return string representation of decoded booleans.
	 */
	public String decode(Boolean[] coding) {
		if(coding == null)
			throw new IllegalArgumentException();
			
		Node curr = root;
		StringBuilder b = new StringBuilder();

		for (Boolean code : coding) {
			if (curr instanceof InternalNode)
				if (code)
					// left side --> true
					curr = ((InternalNode) curr).right;
				else
					// right side
					curr = ((InternalNode) curr).left;

			if (curr instanceof LeafNode) {
				b.append(((LeafNode) curr).data);
				curr = root;
			}
		}

		if (curr != root)
			throw new IllegalArgumentException(curr.toString());

		return b.toString();
	}

	/**
	 * Depth-first search. Attempts to travel down the tree for the farthest amount
	 * of distance. Once this distance has been reached it backtrack.
	 * 
	 * @param curr current node.
	 * @param path list of "turns" needed to get to current node.
	 * @param c    value to look for.
	 * @return boolean determining successful search.
	 */
	private boolean dfs(Node curr, List<Boolean> path, Character c) {
		if (curr instanceof LeafNode) {
			if (((LeafNode) curr).data == c)
				return true;
			return false;
		} else if (dfs(((InternalNode) curr).left, path, c)) {
			path.add(0, false);
			return true;
		} else if (dfs(((InternalNode) curr).right, path, c)) {
			path.add(0, true);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Naive encoding. Takes an input text string, looks up the bit sequence for
	 * each one, and returns an array that concatenates all of the bit sequences.
	 * 
	 * @param inputText input string.
	 * @return array of booleans representing the bit sequence.
	 */
	public Boolean[] encode(String inputText) {
		List<Boolean> encoding = new ArrayList<>();

		for (Character c : inputText.toCharArray()) {
			List<Boolean> temp = new ArrayList<>();
			if (!dfs(root, temp, c)) //not found 
				throw new IllegalArgumentException();
			encoding.addAll(temp);
		}
		return (Boolean[]) encoding.toArray(new Boolean[encoding.size()]);
	}

	/**
	 * More efficient version of encode that removes redundant code.
	 * @param inputText input string.
	 * @return array of booleans representing the bit sequence.
	 */
	public Boolean[] efficientEncode(String inputText) {
		Map<Character, List<Boolean>> h = new HashMap<>();
		List<Boolean> encoding = new ArrayList<>();

		for (Character c : inputText.toCharArray()) {
			if (h.containsKey(c)) {
				encoding.addAll(h.get(c));
			} else {
				List<Boolean> path = new ArrayList<>();
				if (!dfs(root, path, c))
					throw new IllegalArgumentException();
				h.put(c, path);
				encoding.addAll(path);
			}
		}
		return (Boolean[]) encoding.toArray(new Boolean[encoding.size()]);
	}

	public static void main(String[] args) {
		// Code to see if stuff works...
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
		System.out.println(Arrays.deepToString(t.encode("string")));
//		System.out.println(t.toString());
		// Now you can use encode, decode, and toString to interact with your specific
		// Huffman Tree

		System.out.println(t);
	}
}
