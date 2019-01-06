package �ڱ���07;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;



public class TestHashTable {

	public static void main(String[] args) {
		HashMap<String,Integer> m = new HashMap<String,Integer>();
		String[] test = {"I" , "You" , "he" , "Brutus" , "evil" , "the" , "and"};
		String ul;
	
		try{
			BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\a\\Desktop\\���ڱ���\\Caesar.txt"));
	        String line = in.readLine();
			while(line != null) {//������ ���پ� �������鼭 ���ٱ��� ���� 
				ul = line.toUpperCase();
				if(m.containsKey(ul)) {
					int a = m.get(ul);
					m.put(ul, a+1);
				}
				else
					m.put(ul, 1);
				line = in.readLine();
			}
			in.close();
		} catch(IOException e) {System.out.println(e);}
		
		System.out.print("���� �ܾ���� �� : ");
		for(int i = 0; i < test.length; i++) {
			System.out.printf("%s ",test[i]);
		}
		System.out.println();
		for(int i = 0; i < test.length; i++) {
			String testK = test[i].toUpperCase();
			System.out.printf("%s : %d\n",test[i],m.get(testK));
		}
		
	}

}
