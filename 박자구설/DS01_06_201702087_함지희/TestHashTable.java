package 자구설06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestHashTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ul;
		LinearProbingHashTable t1 = new LinearProbingHashTable();
		QuadraticProbingHashTable t2 = new QuadraticProbingHashTable();
		DoubleHashingHashTable t3 = new DoubleHashingHashTable();
		SeparateChainingHashTable t4 = new SeparateChainingHashTable();
		String[] test = {"I" , "You" , "he" , "Brutus" , "evil" , "the" , "and"};
		
		try{
			BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\a\\Desktop\\박자구설\\Caesar.txt"));
	        String line = in.readLine();
			while(line != null) {//파일을 한줄씩 내려가면서 끝줄까지 읽음 
				ul = line.toUpperCase();
				t1.put(ul, 1);
				t2.put(ul, 1);
				t3.put(ul, 1);
				t4.put(ul, 1);
				line = in.readLine();
			}
			in.close();
		} catch(IOException e) {System.out.println(e);}
		
		System.out.printf("< 충돌  > : 선형조사(%d) 제곱조사(%d) 이중해싱(%d)\n",t1.getcollision(),t2.getcollision(),t3.getcollision());
		for(int i = 0; i < test.length; i++) {
			String testK = test[i].toUpperCase();
			System.out.printf("%s : 선형조사(%d) 제곱조사(%d) 이중해싱(%d) 폐쇄 주소법(%d)\n",test[i],t1.get(testK),t2.get(testK),t3.get(testK),t4.get(testK));
		}
		
		
	}

}
