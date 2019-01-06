package 자구설10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestHuffmanTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []arr = new int[50];
		HuffmanTree huf = new HuffmanTree("C:\\Users\\a\\Desktop\\박자구설\\text.txt");
	    huf.makeHuffman();
		huf.printEachCharacterCode(huf.hufTree, arr, 0);
		
		try{
			BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\a\\Desktop\\박자구설\\text.txt"));
	        String line = in.readLine(); //한 줄만 읽는다.  두번째 줄 읽고 싶으면 이 문장 한번더 쓰면 됨
	        System.out.println("Encoding this String : "+ line);
	        System.out.print("Huffman Code Stream : ");
				String ul = line.toUpperCase(); // 대문자 변환 
				for(int i = 0; i < ul.length(); i++) {
					char c = ul.charAt(i);
					huf.CharacterCode(huf.hufTree, arr, 0, c);
				}
			
			in.close();
		} catch(IOException e) {System.out.println(e);}

	}

}
