package ÀÚ±¸¼³08;

public class TestAVLTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 AVLTree a = new AVLTree(14);
		 int[] in = {17,11,7,53,4,13,12,8};
		 int[] re = {53,11,7,12,14,13};
		 
		 for(int i = 0; i < in.length; i++) {
			 a.grow(in[i]);
		 }
	      System.out.println("insert result : " +a);
	      
	     for(int i = 0; i < re.length; i++) {
	    	 a.remove(re[i]);
	    	 System.out.println(re[i]+" remove result : "+a);
	     }
	}

}
