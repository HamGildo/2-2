package �ڱ���06;

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
			BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\a\\Desktop\\���ڱ���\\Caesar.txt"));
	        String line = in.readLine();
			while(line != null) {//������ ���پ� �������鼭 ���ٱ��� ���� 
				ul = line.toUpperCase();
				t1.put(ul, 1);
				t2.put(ul, 1);
				t3.put(ul, 1);
				t4.put(ul, 1);
				line = in.readLine();
			}
			in.close();
		} catch(IOException e) {System.out.println(e);}
		
		System.out.printf("< �浹  > : ��������(%d) ��������(%d) �����ؽ�(%d)\n",t1.getcollision(),t2.getcollision(),t3.getcollision());
		for(int i = 0; i < test.length; i++) {
			String testK = test[i].toUpperCase();
			System.out.printf("%s : ��������(%d) ��������(%d) �����ؽ�(%d) ��� �ּҹ�(%d)\n",test[i],t1.get(testK),t2.get(testK),t3.get(testK),t4.get(testK));
		}
		
		
	}

}
