package 자구설01;
import java.io.*;
import java.util.StringTokenizer;
public class TestGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\a\\Desktop\\박자구설\\1주차\\graph.txt"));
			String line = in.readLine(); //파일의 첫번째 줄 읽음
			String lineSplit[] = line.split(" "); //첫줄(정점들)을 읽어서 정점의 배열을 만듦
			Graph g = new Graph(lineSplit); //graph생성자이용하여 객체생성
			line = in.readLine();//두번째 줄 읽음
			while(line != null) {//파일을 한줄씩 내려가면서 끝줄까지 읽음 
				StringTokenizer parser = new StringTokenizer(line," ,:;-.?!");
				String word1 = parser.nextToken();
				String word2 = parser.nextToken();
				g.add(word1, word2);
				line = in.readLine();
			}
			in.close();
			System.out.println(g);
			g.degree("B");
			g.degree("C");
			g.findPath("A", "B");
			g.findPath("B", "E");
			g.findPath("E", "F");
			
		} catch(IOException e) {System.out.println(e);}
		
	}

}