import java.util.Arrays;

public class MyList<E> {
	// Class constants
	private static final int INITIAL_CAPACITY=10;

	// Data fields
	private E[] data;
	private int free;
	
	// Constructor
	MyList() {
		data = (E[]) new Object[INITIAL_CAPACITY];
		free=0;
	}
	
	// Methods
	
	private void resize() {
		data = Arrays.copyOf(data, data.length*2);
	}
	
	public void add(E item) {
		if (free==data.length) {
			resize();
		}
		data[free]=item;
		free++;
	}
	
	public void add(E item, int index) {
		if (index<0 || index>free) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (free==data.length) {
			resize();
		}
		
		for (int i=free; i>index; i--) {
			data[i] = data[i-1];
		}
		// code missing?
		data[index] = item;
		free++;
	}
	
	public E get(int index) {
		if (index<0 || index>free) {
			throw new ArrayIndexOutOfBoundsException();
		} 
		return data[index];
	}
	public void remove(E item) {
		
		int index = find(item);
		
		if (index==free) {
			throw new IllegalArgumentException();
		} else {
			
			for (int i=index; i<free-1; i++) {
				data[i] = data[i+1];
			}
			free--;
		}
	}

	public int find(E item) {
		int i=0;
		while (i<free && !item.equals(data[i])) {
			i++;
		}
		return i;
	}

	public boolean member(E item) {
		int i=0;
		while (i<free && !item.equals(data[i])) {
			i++;
		}	
		return i<free;
	}

	public int size() {
		return free;
	}
	
	public String toString2() {
//		return Arrays.toString(data);
		String s="[";
		
		if (free==0) {
			s = s + "]";
		} else {
			for (int i=0; i<free-1; i++) {
				s = s +  data[i].toString() + "," ;
			}
			s = s + data[free-1].toString() + "]";
		}
		return s;
	}
	
	public String toString() {
		return Arrays.toString(data);
	}
	
	public static void main(String[] args) {
		MyList<Integer> li = new MyList<Integer>();
		
		for (int i=0; i<20; i++) {
			li.add(i);
		}

		System.out.println(li);
		System.out.println(li.member(0));
		System.out.println(li.member(7));
		System.out.println(li.member(19));
		System.out.println(li.member(20));
		li.remove(3);
		System.out.println(li);


//		MyList<String> ls = new MyList<String>();
//		ls.add("h");
//		ls.add("e");
//		ls.add("l");
//		ls.add("o");
//		ls.add("l",2);

//		MyList<Double> ld = new MyList<Double>();
//

//		System.out.println(ls);

//		System.out.println(ld);

	}
}
