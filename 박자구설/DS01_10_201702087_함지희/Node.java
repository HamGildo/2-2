package 자구설10;

public class Node {
	int freq;
	char al;
	Node left;
	Node right;
	
	public Node(char al, int freq) {
		this.freq = freq;
		this.al = al;
		left = right = null;
	}
	
	public Node(Node left, Node right) {
		this.freq = left.freq + right.freq;
		this.left = left;
		this.right = right;
	}
}
