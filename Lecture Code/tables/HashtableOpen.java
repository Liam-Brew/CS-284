package tables;

import java.util.Arrays;
import java.util.Random;

public class HashtableOpen<K,V> {

	private Entry<K, V>[] table;
	private static final int START_CAPACITY = 101;
	private double LOAD_THRESHOLD = 0.75; private int numKeys;
	private int numDeletes;
	private final Entry<K, V> DELETED =
			new Entry<K, V>(null, null);
	
	public static class Entry<K, V>  {
		private K key; private V value;
		
		public Entry(K key, V value) {
			this.key = key; this.value = value;
		}
		public K getKey() { 
			return key;
		}
		public V getValue() {
			return value; 
			}
		public V setValue(V val) { 
			V oldVal = value;
			value = val; 
			return oldVal;
		}
		@Override
		public String toString() {
			return key.toString() + "=" + value.toString(); }
	}

	HashtableOpen() {
		table  = new Entry[START_CAPACITY]; 
	}
	
	
	/**
	 * Find key using linear probing
	 * @param key
	 * @return index where key was located	
	 */
 	private int find(K key) {
		return null;
	}
	
	
 	public V put(K key, V value) { 
		return null;
	}
 	
 	  
 	private void rehash() {
 		return null;
 	}
 	
 	/**
 	 * Removes key. Does nothing if key is not in the table
 	 * @param key
 	 * @return
 	 */
 	public V remove(K key) { 
 		return null; 
 	}
	
 	public String toString() {
 		return Arrays.toString(table);
 	}
 	
	public static void main(String[] args) {
		HashtableOpen<Character,Integer> t = new HashtableOpen<>();
		
		Random r = new Random();
		
		for (int i=0; i<20; i++) {
			Character key = (char) (i+65);
			System.out.println(key+"-->"+key.hashCode());
			t.put(key, r.nextInt(1000));
		}
		System.out.println(t);
	}

}
