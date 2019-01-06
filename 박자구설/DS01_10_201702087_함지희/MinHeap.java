package 자구설10;

public class MinHeap  {
	private static final int CAPACITY = 100;
	private Node[] a;
	private int size;
	
	
	public MinHeap() {
		this(CAPACITY);
	}
	
	public MinHeap(int capacity) {
		a = new Node[capacity];
	}
	
	public void add (Node node) {
		Node x = node;
		if(size == a.length) resize();
		int i = size++;
		while(i>0) {
			int j = i;
			i = (i - 1)/2;
			if(a[i].freq <= x.freq) {
				a[j] = x;
				return;
			}
			a[j] = a[i];
		}
		a[i] =x;
	}
	
	public Node best() {
		if (size == 0) throw new java.util.NoSuchElementException();
		return a[0];
	}
	
	public Node remove() {
		if(best() == null) return null;
		Node best = best();
		a[0] = a[--size];
		heapify(0,size);
		return best;
	}
	
	public int size() {return size;}
	
	private void heapify(int i, int n) {
		Node ai = a[i];
		while (i < n/2) {
			int j = 2*i + 1;
			if (j+1 < n && a[j+1].freq < a[j].freq) j++; 
			if(a[j].freq >= ai.freq) break;
			a[i] = a[j];
			i = j;
		}
		a[i] = ai;
	}
	
	private void resize() {
		Node[] aa = new Node[2*a.length];
		System.arraycopy(a, 0, aa, 0, a.length);
		a = aa;
	}
	
	
	

}
