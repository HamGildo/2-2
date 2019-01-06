package ÀÚ±¸¼³06;

public class SeparateChainingHashTable {
	private Entry[] table;
	private int size;
	private float loadFactor;
	
	public int size() {return size;}
	
	private class Entry {
		Object key,value;
		Entry next;
		Entry(Object k, Object v, Entry n) {
			key = k; value = v; next = n;
		}
	}
	
	private int hash(Object key) {
		if (key == null) throw new IllegalArgumentException();
		return (key.hashCode() & 0x7FFFFFFF) % table.length;
	}
	
	private void rehash() {
		Entry[] oldTable = table;
		table = new Entry[2*oldTable.length+1];
		for(int k = 0; k < oldTable.length; k++) {
			for (Entry old = oldTable[k]; old != null;) {
				Entry e = old;
				old = old.next;
				int h = hash(e.key);
				e.next = table[h];
				table[h] = e;
			}
		}
	}
	
	public SeparateChainingHashTable(int capacity, float loadFactor) {
		table = new Entry[capacity];
		this.loadFactor = loadFactor;
	}
	public SeparateChainingHashTable(int capacity) {
		this(capacity,0.75F);
	}
	public SeparateChainingHashTable() {
		this(101);
	}
	
	public Object get(Object key) {
		int h = hash(key);
		for(Entry e = table[h]; e!= null; e = e.next) {
			if(e.key.equals(key)) return e.value; //success
		}
		return null;
	}
	
	public Object put(Object key, Object value) {
		int h = hash(key);
		for(Entry e = table[h]; e!= null; e = e.next) {
			if(e.key.equals(key)) {
				Object oldValue = e.value;
				int v = (int)e.value;
				e.value = v+1;
				return oldValue;
			}
		}
		table[h] = new Entry(key,value,table[h]);
		++size;
		if(size > loadFactor*table.length) rehash();
		return null;
	}
	
	public Object remove(Object key) {
		int h = hash(key);
		for(Entry e = table[h],prev = null; e!=null; prev = e, e = e.next) {
			if(e.key.equals(key)) {
				Object oldValue = e.value;
				if(prev == null) table[h] = e.next;
				else prev.next = e.next;
				--size;
				return oldValue;
			}
		}
		return null;
	}

}
