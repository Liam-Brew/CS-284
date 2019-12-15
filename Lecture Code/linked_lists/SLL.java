
/**
 * Linked list based lists
 * @author eduardobonelli
 *
 * @param <E>
 */
public class SLL<E> {
	public static class Pair<E,F> {
		private E x;
		private F y;
		
		public Pair(E x, F y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		public E getX() {
			return x;
		}
		public void setX(E x) {
			this.x = x;
		}
		public F getY() {
			return y;
		}
		public void setY(F y) {
			this.y = y;
		}	
	}

	public static class Node<F> {
		// Data fields
		private F data;
		private Node<F> next;
		
		Node(F data) {
			this.data=data;
			this.next=null;
		}
		
		Node(F data, Node<F> next) {
			this.data=data;
			this.next=next;
		}
		
	}
	// Data fields
	private Node<E> head;
	private int size;
	
	SLL() {
		head=null;
		size=0;
	}
	
	public void addFirst(E item) {
		head = new Node<E>(item,head);
		size++;
	}
	
	public void addLast(E item) {
		if (head==null) { // list is empty
			this.addFirst(item);
		} else {          // list is not empty
			Node<E> current=head;
			
			while (current.next!=null) {
				current=current.next;
			}
			
			current.next = new Node<E>(item);
			size++;
			
		}
	}
	
	public E removeLast() {
		if (head==null) {  // list is empty
			throw new IllegalStateException();
		}
		if (head.next==null) { // singleton list
			E temp = head.data;
			head=null;
			size--;
			return temp;
		}
		// the list has two or more elements
		Node<E> current=head;
		
		while (current.next.next!=null) {
			current=current.next;
		}
		
		E temp = current.next.data;
		current.next = null;
		size--;
		return temp;
	}
	
	public E removeFirst() {
		if (head==null) {
			throw new IllegalStateException();
		}
		E temp = head.data;
		head = head.next;
		size--;
		return temp;
	}
	
	private void addAfter(Node<E> n, E item) {
		n.next = new Node<>(item,n.next);
		size++;
	}
	
	/**
	 * Retrieves the i-th node of the linked-list
	 * @param i Should be greater than or equal to 0 and less than the length of the list
	 * @return
	 */
	private Node<E> getNode(int i) {
		Node<E> current = head;
		
		for (int j=0; j<i; j++) {
			current = current.next;
		}
		
		return current;
	}
	
	public E get(int i) {
		if (i<0 || i>size) {
			throw new IllegalArgumentException(); 
		}
		return getNode(i).data;
	}
	
	public void add(int i, E item) {
		if (i<0 || i>size) {
			throw new IllegalArgumentException(); 
		}
		if (i==0) {
			addFirst(item);
		} else {
			addAfter(getNode(i-1),item);
		}
	}
	
	// Shallow clone
	// O(n)
	public SLL<E> clone() {
		SLL<E> result = new SLL<E>();
		result.size = size;
		Node<E> current = head;
		Node<E> last = new Node<E>(null);
		Node<E> dummy = last;
		
		while (current!=null) {
			last.next = new Node<E>(current.data);	
			last = last.next;
			current = current.next;
		}
		
		result.head = dummy.next;
		return result;	
	}
	
	// O(n^2)
	
	public SLL<E> clone2() { 
		SLL<E> result = new SLL<E>();
		result.size = size;
		Node<E> last = new Node<E>(null);
		Node<E> dummy = last;
		int i=0;
		
		while (i<size) {
			last.next = new Node<E>(get(i));	
			last = last.next;
			i++;
		}
		
		result.head = dummy.next;
		return result;	
	}
	
	
	public boolean isEmpty() {
		return head==null;
	}
	
	public SLL<E> take(int n) {
		if (n>=size) {   // clone entire list
			return clone();
		} 
		if (n<=0) {      // return an empty list
			return new SLL<E>();
		}
		// n>0 and n<size
		// Look at the code for clone
		SLL<E> result = new SLL<E>();
		Node<E> last = new Node<E>(null);
		Node<E> dummy = last;
		Node<E> current = head;
		for (int i=0; i<n; i++) {	
			last.next=new Node<E>(current.data);
			last=last.next;
			current=current.next;
		}
		
		result.head = dummy.next;
		result.size = n;
		return result;		
	}
	
	public void drop(int n) {
		if (n<=0) { // nothing to drop
			return;
		}
		if (n>=size) { // drop all items in list
			head=null;
			size=0;
			return;
		}
		// n>0 && n<size
		for (int i=0; i<n; i++) {
			removeFirst();
		}
		
	}
	
	public void remove_nulls() {
		while (head!=null && head.data==null) {
			removeFirst();
		}
		if (head==null) {
			return;
		}
		// List is non-empty and does not start with a null item
		Node<E> current=head;
		while (current.next!=null) {
			if (current.next.data==null) {
				current.next=current.next.next;
				size--;
			} else {
				current = current.next;
			}
		}
		
	}
	
	public Pair<SLL<E>,SLL<E>> splitAt(int n) {
		SLL<E> li = clone();
		li.drop(n);
		return new Pair<SLL<E>,SLL<E>>(take(n),li);
	}
	
	// O(n^2)
	public SLL<Pair<E,E>> zip(SLL<E> l2) {
		SLL<Pair<E,E>> result = new SLL<>();
		Node<E> current1 = head;
		Node<E> current2 = l2.head;
		
		
		while (current1!=null && current2!=null) {
		    result.addLast(new Pair<E,E>(current1.data,current2.data));
		    current1=current1.next;
		    current2=current2.next;
		}		
		return result;

	}
	
	
	public String toString() {
		Node<E> current=head;
		StringBuilder s = new StringBuilder();
		
		s.append("[");
		while (current!=null) {
			s.append(current.data==null?"null,":(current.data.toString() + ","));
			current = current.next;
		}
		s.append("]");
		return s.toString();
		
	}
	public static void main(String[] args) {
		SLL<Integer> li = new SLL<>();
	
		for (int i=0; i<10; i++) {
			li.addFirst(i);
		}
		
		System.out.println(li);
		li.addFirst(null);
		li.addFirst(null);
		li.addFirst(null);
		li.addFirst(12);
		System.out.println(li);
		li.remove_nulls();
		System.out.println(li);
		
		Pair<SLL<Integer>,SLL<Integer>> pls = li.splitAt(4);
		System.out.println(pls.getX());
		System.out.println(pls.getY());
		
		
//		li.addLast(20);
//		System.out.println(li);
//		li.removeLast();
//		System.out.println(li);
//		
//		System.out.println(li.getNode(0).data);
//		System.out.println(li.getNode(li.size-1).data);
//		
//		SLL<Integer> clone_li = li.clone();
//		System.out.println(li);
//		System.out.println(clone_li);
//
//		System.out.println(li.take(5));
//		System.out.println(li.take(0));
//		System.out.println(li.take(1000));

//		li.drop(0);
//		System.out.println(li);
//		li.drop(2);
//		System.out.println(li);
//		li.drop(100);
//		System.out.println(li);

	}
}
