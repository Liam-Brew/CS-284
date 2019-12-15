package heaps;

import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {
	// Data fields
	ArrayList<E> data;
	int free;
	
	Heap(int size) {
		data = new ArrayList<E>(size);
		free = 0;
	}
	
	public E top() {
		if (free==0) {
			throw new IllegalStateException();
		}
		return data.get(0);	
	}
	
	private void swap(int p, int q) {
		E temp = data.get(p);
		data.set(p, data.get(q));
		data.set(q, temp);
	}
	
	// Precondition: data has enough space to hold item
	public void add(E item) {
		data.add(free, item);
		int p = free;
		int parent = (free-1)/2;
		
		while (parent>=0 && data.get(parent).compareTo(item)>0) {
			swap(parent,p);
			p=parent;
			parent=(parent-1)/2;
		}
		
		
		free++;
		return;
	}
	
	public E remove() {
		if (free==0) { // heap is empty
			throw new IllegalStateException();
		}
		E temp = data.get(0);
		data.set(0, data.get(free-1));
		data.remove(free-1);
		int p=0;
		int left= 2*p+1;
		int right = 2*p+2;
		free--;

		while (left<free) {
			int minChild=left;
			if (right<free && data.get(right).compareTo(data.get(left))<0) {
				minChild=right;
			}
			// minChild holds the index of the smallest of the two children.
			if (data.get(p).compareTo(data.get(minChild))<0) {
				break;
			}
			swap(p,minChild);	
			p=minChild;
			left=minChild*2 +1;
			right=minChild*2+2;
		    
		}
				
		return temp;
	}
	
	public String toString() {
		return data.toString();
	}
	
	public static void main(String[] args) {
		Heap<Integer> h = new Heap<>(100);
		int[] l = {35, 27,14,48,12,9,64};
		for (int i:l) {
			h.add(i);
		}
		
		System.out.println(h);
			h.remove();
			h.remove();
			h.remove();
			h.remove();
			h.remove();
			h.remove();
			h.remove();

		System.out.println(h);
		
	}
}
