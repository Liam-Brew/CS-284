public class QueueSLL<E> {

	public static class Node<F> {
		// Data fields
		private F data;
		private Node<F> next;
		
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}
		public Node(F data) {
			super();
			this.data = data;
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
	private Node<E> rear;
	private Node<E> front;
	private int size;
	
	// Constructor
	QueueSLL() {
		super();
		rear=null;
		front=null;
		size=0;
	}
	
	boolean offer(E item) {
		if (front==null) {  // queue is empty
			rear=new Node<E>(item);
			front=rear;
			size=1;
		} else { // queue is empty
			rear.next= new Node<E>(item);
			rear = rear.next;
			size++;
		}
		return true;
	}

	E peek() {
		if (front==null) {
			throw new IllegalStateException();
		}
		return front.data;
		
	}

	E poll() {
		if (front==null) { // queue is empty
			throw new IllegalStateException();
		}
		E temp = front.data;
		front = front.next;
		if (size==1) { // singleton queue
			rear=null;
		}
		size--;
		return temp;
		
		
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public static void main(String[] args) {
		QueueSLL<Integer> q = new QueueSLL<>();
		
		for (int i=0; i<10; i++) {
			q.offer(i);
		}
		
		System.out.println(q.poll());
		System.out.println(q.poll());
	}
}
