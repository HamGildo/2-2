package �ڱ���02;
import java.io.*;
import java.util.StringTokenizer;
public class TestGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\a\\Desktop\\���ڱ���\\2����\\graph.txt"));
			String line = in.readLine(); //������ ù��° �� ����
			String lineSplit[] = line.split(" "); //ù��(������)�� �о ������ �迭�� ����
			Graph g = new Graph(lineSplit); //graph�������̿��Ͽ� ��ü����
			line = in.readLine();//�ι�° �� ����
			while(line != null) {//������ ���پ� �������鼭 ���ٱ��� ���� 
				StringTokenizer parser = new StringTokenizer(line," ,:;-.?!");
				String word1 = parser.nextToken();
				String word2 = parser.nextToken();
				g.add(word1, word2);
				line = in.readLine();
			}
			in.close();
			System.out.println(g);
			
			g.findPath("A", "B");
			g.findPath("B", "E");
			g.findPath("E", "F");
			
		} catch(IOException e) {System.out.println(e);}
		
	}
}