package 자구설09;

public class BinarySearchTree {
	private int key;
	private BinarySearchTree left, right;
	public static final BinarySearchTree NIL = new BinarySearchTree();
	
	public BinarySearchTree(int key) {
		this.key = key;
		left = right = NIL;
	}
	
	public BinarySearchTree() {
		left = right = this;
	}
	
	public BinarySearchTree add(int key) {
		if (this == NIL) {
			return new BinarySearchTree(key);
			}
		else if(key<this.key)
			left = left.add(key);
		else if(key>this.key)
			right = right.add(key);

			return this;
	}
	
	public String toString() {
		if(this == NIL) return "";
		return left + " " + key + " " + right;
	}

	public boolean search(int key) {
		BinarySearchTree temp = this;
		while(true) {
			if(temp == NIL) return false;
			else if(key < temp.key) {
				 temp = temp.left;
			}
			else if(key > temp.key){
				temp = temp.right;
			}
			else 
				return true;
		}
	}
	

}
