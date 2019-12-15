package trees;

import trees.BTree.Node;
/**
 * Binary Search Tree. 
 * Elements are required to be non-null.
 * @author eduardobonelli
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> extends BTree<E> {
	
	
	BST() {
		root=null;
		size=0;
	}

	BST(E item) {
		root=new Node<E>(item);
		size=1;
	}

	BST(E item, BST<E> left, BST<E> right) {
		root = new Node<E>(item,left.root,right.root);
		size = 1 + left.size+right.size;
	}
	
	private boolean find(E key, Node<E> current) {
		if (current==null) {
			return false;
		}
		// Current is non-null
		int i = current.data.compareTo(key);
		if (i==0) {
			return true;
		} else {
			if (i<0) {
				return find(key,current.right); 
			} else {
				return find(key,current.left);
			}
		}
	}
	
	public boolean find(E key) {
		if (root==null) {
			return false;
		}
		return find(key,root);
	}
	
	private Node<E> add(E key, Node<E> current) {
		if (current==null) {
			size++;
			return new Node<E>(key);
		}
		int i = current.data.compareTo(key);
		
		if (i==0) {
			throw new IllegalArgumentException("Duplicate Key!");
		} 
		if (i<0) {
			  current.right = add(key,current.right);
			  return current;
		} else {
				current.left = add(key,current.left);
				return current;
		}
		
	
	}
	
	public void add(E item) {
		root = add(item,root);
	}

	private E max(Node<E> current) {
		if (current.right==null) {
			return current.data;
		} else {
			return max(current.right);
		}
		
	}
	public E max() {
		if (root==null) {
			throw new IllegalStateException();
		}
		return max(root);
	}

	
	public static void main(String[] args) {
		BST<Integer> t0 = new BST<>(15);
		BST<Integer> t10 = new BST<>(74,new BST<>(),new BST<>(80));
		BST<Integer> t1 =  new BST<>(89,t10,new BST<>(102));
		BST<Integer> t = new BST<>(27,t0,t1);
		
		System.out.println(t);
//		System.out.println(t.height());
//		
//		System.out.println(t.preorder());
		
		System.out.println(t.find(80));
		System.out.println(t.find(100));
		t.add(85);
		System.out.println(t);
	}

}
