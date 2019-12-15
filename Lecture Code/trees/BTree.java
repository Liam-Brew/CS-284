package trees;

import lists.SLL;

public class BTree<E> {
	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> left;
		private Node<F> right;
		
		// Constructors
		public Node(F data, Node<F> left, Node<F> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		public Node(F data) {
			super();
			this.data = data;
		}
		
		// Methods
		public F getData() {
			return data;
		}
		public void setData(F data) {
			this.data = data;
		}
		public Node<F> getLeft() {
			return left;
		}
		public void setLeft(Node<F> left) {
			this.left = left;
		}
		public Node<F> getRight() {
			return right;
		}
		public void setRight(Node<F> right) {
			this.right = right;
		}
		
		
	}
	// Data fields
	private Node<E> root;
	private int size;
	
	// Constructor
	BTree() {
		root=null;
		size=0;
	}
	
	BTree(E item) {
		root = new Node<E>(item);
		size=1;
	}
	
	BTree(E item, BTree<E> lt, BTree<E> rt) {
		root = new Node<E>(item,lt.root,rt.root);
		size = lt.size+rt.size+1;
	}
	
	private int height(Node<E> current) {
		if (current==null) {
			return 0;
		} else {
			return 1+Math.max(height(current.left), height(current.right));
		}
	}
	public int height() {
		return height(root);
	}
	
	private SLL<E> preorder(Node<E> current) {
		SLL<E> result = new SLL<E>();
		if (current==null) {
			return result;
		} else {
			result.addFirst(current.data);
			result.append(preorder(current.left));
			result.append(preorder(current.right));
			return result;
		}
		
	}
	public SLL<E> preorder() {
		return preorder(root);
	}
	
	private Node<E> mirror(Node<E> current) {
		if (current==null) {
			return null;
		} else {
			Node<E> ml = mirror(current.left);
			Node<E> mr = mirror(current.right);
			current.right=ml;
			current.left=mr;
			return current;
			
			
			
		}
	}
	public void mirror() {
		root=mirror(root);
	}
	
	private StringBuilder toString(Node<E> current, int n) {
		StringBuilder b = new StringBuilder();
		for (int i=0; i<n; i++) {
			b.append("-");
		}
		if (current==null) {
			return b.append("null\n");
		} else {
			b.append(current.data+"\n");
			b.append(toString(current.left,n+1));
			b.append(toString(current.right,n+1));
			return b;
		}
	}
	public String toString() {
		return toString(root,1).toString();
		
	}
	
	public static void main(String[] args) {
		BTree<Integer> t0 = new BTree<>(15);
		BTree<Integer> t10 = new BTree<>(74,new BTree<>(),new BTree<>(80));
		BTree<Integer> t1 =  new BTree<>(89,t10,new BTree<>(102));
		BTree<Integer> t = new BTree<>(27,t0,t1);
		
		System.out.println(t);
//		System.out.println(t.height());
//		
//		System.out.println(t.preorder());
		
		t.mirror();
		System.out.println(t);
	}
	

}
