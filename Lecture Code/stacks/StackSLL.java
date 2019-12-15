public class StackSLL<E> implements StackInt<E> {
	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;
		
		public Node(F data) {
			super();
			this.data = data;
		}
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}
		public F getData() {
			return data;
		}
		public void setData(F data) {
			this.data = data;
		}
		public Node<F> getNext() {
			return next;
		}
		public void setNext(Node<F> next) {
			this.next = next;
		}

	
	}

	// Data fields
	private Node<E> top;
	private int size=0;
	
	public void push(E item) {
		top  = new Node<E>(item,top);
		size++;
		
	}
	public E pop() {
		if (top==null) {
			throw new IllegalStateException();
		}
		E temp = top.data;
		top= top.next;
		size--;
		return temp;
	}
	
	public E peek() {
		if (top==null) {
			throw new IllegalStateException();
		}
		return top.data;
	}
	
	public boolean isEmpty() {
		return top==null;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		Node<E> current=top;
		StringBuilder s = new StringBuilder();
		
		while (current!=null) {
			s.append(current.data + ",");
			current = current.next;
		}
		
		return s.toString();
	}
	
	public static void main(String[] args) {
		StackSLL<Integer> s = new StackSLL<>();
		
		for (int i=0; i<10; i++) {
			s.push(i);
		}
		
		System.out.println(s);
		System.out.println(s.pop());
		System.out.println(s);
		

	}

}
