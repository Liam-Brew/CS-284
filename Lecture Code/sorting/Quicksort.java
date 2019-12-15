package sorting;

import java.util.Arrays;

public class Quicksort {
	
	private static void swap(int[] a,int i, int j) {
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	private static int partition(int[] a, int left, int right) {
		int pivot = left;
		int up = left;
		int down = right;
		
		do {
			while (up<right && a[up]<=a[pivot]) {up++;}
			while (a[down]>a[pivot]) {down--;}
			if (up<down) {
				swap(a,up,down);
			}
			
		} while (up<down);
		
		swap(a,pivot,down);
		
		return down;
		
	}
	
	private static void sort(int[] a,int left, int right) {
		if (left<right) {
			int pivot=partition(a,left,right);
			sort(a,left,pivot-1);
			sort(a,pivot+1,right);
		}
	}
	
	public static void sort(int[] a) {
		sort(a,0,a.length-1); 
	}
	
	public static void main(String[] arg) {
		int[] a = {44,75,23,43,55,12,64,77,33};
		
		sort(a);
		System.out.println(Arrays.toString(a));
	}	
}
