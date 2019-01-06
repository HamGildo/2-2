package 자구설06;

public class DoubleHashingHashTable {
	private Entry[] table;
	private int size, used = 0;
	private int collision = 0;
	private float loadFactor;
	private final Entry NIL = new Entry(null,null);
	
	public int size() {return size;}
	public int getcollision() {return collision;}
	
	private class Entry {
		Object value, key;
		public Entry(Object k , Object v) {this.key = k; this.value = v;}
	}
	
	private int hash(Object key) {
		if (key == null) throw new IllegalArgumentException();
		return (key.hashCode() & 0x7FFFFFFF) % table.length; 
	}
	
	private int hash2(Object key) {
		if (key == null) throw new IllegalArgumentException();
		return 1 + (key.hashCode() & 0x7FFFFFFF) % (table.length-1); 
	}
	
	private int nextProbe(int h,int d, int i) { // Double Hashing
		return (h + i * d)%table.length;
	}
	
	private void rehash() {
		Entry[] oldTable = table;
		table = new Entry[2*oldTable.length+1];
		for(int k = 0; k < oldTable.length; k++) {
			Entry entry = oldTable[k];
			if(entry == null || entry == NIL)continue;
			int h = hash(entry.key);
			int d = hash2(entry.key);
			for(int i = 0; i < table.length;i++) {
				int j = nextProbe(h,d,i);
				if(table[j] == null) {
					table[j] = entry;
					break;
				}
			}
		}
		used = size;
	}
	
	public DoubleHashingHashTable(int capacity, float loadFactor) {
		table = new Entry[capacity];
		this.loadFactor = loadFactor;
	}
	public DoubleHashingHashTable(int capacity) {
		this(capacity, 0.75F);
	}
	public DoubleHashingHashTable() {
		this(101);
	}
	
	public Object get(Object key) {
		int h = hash(key);
		int d = hash2(key);
		for(int i = 0; i < table.length; i++) {
			int j = nextProbe(h,d,i);
			Entry entry = table[j];
			if(entry == null) break;
			if(entry == NIL) continue;
			if(entry.key.equals(key)) return entry.value;
		}
		return null;
	}
	
	public Object put(Object key, Object value) {
		if(used > loadFactor*table.length) rehash();
		int h = hash(key);
		int d = hash2(key);
		for(int i = 0; i < table.length;i++) {
			int j = nextProbe(h,d,i);
			Entry entry = table[j];
			if(entry == null) {
				table[j] = new Entry(key,value);
				++size;
				++used;
				return null; // success
			}
			if(entry == NIL) {collision++; continue;}
			if(entry.key.equals(key)) {
				Object oldValue = entry.value;
				int v = (int)table[j].value; // value가 Object형이라서 int형으로 형변환
				table[j].value = v+1; // value에 +1을 해준다
				return oldValue;
			}
			collision++;
		}
		return null; //table overflow
	}
	
	public Object remove(Object key) {
		int h = hash(key);
		int d = hash2(key);
		for(int i = 0; i < table.length; i++) {
			int j = nextProbe(h,d,i);
			Entry entry = table[j];
			if(entry == null) break;
			if(entry == NIL) continue;
			if(entry.key.equals(key)) {
				Object oldValue = entry.value;
				table[j] = NIL;
				--size;
				return oldValue;
			}
		}
		return null;
	}
	


}
