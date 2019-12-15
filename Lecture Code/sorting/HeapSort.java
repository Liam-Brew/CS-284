package sorting;

import java.util.Arrays;

public class HeapSort {

		public static <T extends Comparable <T>> void sort(T[] table) {
			buildHeap(table);  // build maxHeap
			shrinkHeap(table); // transform heap into a sorted array.
		}
		
		
		
		private static <T> void swap(T[] table, int i, int j) {
			T temp = table[i];
			table[i]=table[j];
			table[j]=temp;
			return;
		}
		
		private static <T extends Comparable<T> >void buildHeap(T[] table) {
			int index=0;
			while (index<table.length) {
				int child = index;
				int parent = (index-1)/2;
			
				while (parent>=0 && table[parent].compareTo(table[child])<0) {
					swap(table, parent,child);
					child=parent;
					parent=(parent-1)/2;
				}
				index++;
			}
			return;
		}
		
		

		private static <T extends Comparable<T>>void shrinkHeap(T[] table) {
			int index = table.length;
			while (index>0) {
				swap(table,0,index-1);
				int p=0;
				int left= 2*p+1;
				int right = 2*p+2;

				while (left<index-1) {
					int maxChild=left;
					if (right<index-1&& table[right].compareTo(table[left])>0) {
						maxChild=right;
					}
					// minChild holds the index of the smallest of the two children.
					if (table[p].compareTo(table[maxChild])>0) {
						break;
					}
					swap(table,p,maxChild);	
					p=maxChild;
					left=maxChild*2 +1;
					right=maxChild*2+2;

				}
				index--;

			}
			
		}
		
		
		public static void main(String[] args) {
			
			Integer[] t = {74,
					66,
					89,
					6,
					39,
					29,
					76,
					32,
					18,
					28,
					37,
					26,
					20};
		   sort(t);
		   System.out.println(Arrays.toString(t));
		
		}
}
