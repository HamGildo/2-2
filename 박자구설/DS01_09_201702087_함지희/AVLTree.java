package 자구설09;

public class AVLTree {
	private int key;
	private int height;
	private AVLTree left, right;
	public static final AVLTree NIL = new AVLTree();
	
	public AVLTree(int key) {
		this.key = key;
		left = right = NIL;
	}
	
	private AVLTree() { //empty tree
		left = right = this;
		height = -1;
	}
	
	private AVLTree(int key, AVLTree left, AVLTree right) {
		this.key = key;
		this.left = left;
		this.right = right;
		height = 1 + Math.max(left.height, right.height);
	}
	
	public int size() {
		if(this == NIL) return 0;
		return 1 + left.size()+right.size();
	}	
	
	public String toString() {
		if(this == NIL) return "";
		return left + " " + key + "("+ (left.height - right.height) + ")" + " " + right;
	}
	
	public boolean add(int key) {
		int oldSize = size();
		grow(key);
		return size() > oldSize;
	}
	
	public boolean delete(int key) {
		int oldSize = size();
		delete(key);
		return size() < oldSize;
	}
	
	public AVLTree grow(int key) {
		if (this == NIL) return new AVLTree(key);
		if (key == this.key) return this;
		if(key < this.key)
			left = left.grow(key);
		else
			right = right.grow(key);
		rebalance();
		height = 1 + Math.max(left.height, right.height);
		return this;
	}
	
	public AVLTree remove(int key) {
		if(key == this.key) { 
			if(left == NIL && right == NIL) {
				return NIL;
			}
			if(left != NIL && right == NIL) { 
				this.key = left.key;
				left = left.left;
				right = left.right;
			}
			if(left == NIL && right != NIL) {
				this.key = right.key;
				left = right.left;
				right = right.right;
			}
			else { 
				int minKey = rightMin(right);
				remove(minKey);
				this.key = minKey;
			} 			
		}
		if(this == NIL) return this; 
		
		if(key < this.key) 
			left = left.remove(key);
		else
			right = right.remove(key);
		
		rebalance();
		height = 1 + Math.max(left.height, right.height);
		return this;
	}
	private int rightMin(AVLTree a) {
		if(a.left == NIL) {
			return a.key;
		}
		return rightMin(a.left);
	}
	
	public boolean search(int key) {
		AVLTree temp = this;
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
	
	private void rebalance() {
		if (right.height > left.height + 1) {
			if(right.left.height > right.right.height)
				right.rotateRight();
			rotateLeft();
		}
		else if(left.height > right.height + 1) {
			if(left.right.height > left.left.height)
				left.rotateLeft();
			rotateRight();
		}
	}
	
	private void rotateLeft() {
		left = new AVLTree(key,left,right.left);
		key = right.key;
		right = right.right;
	}
	
	private void rotateRight() {
		right = new AVLTree(key,left.right,right);
		key = left.key;
		left = left.left;
	}

}
