package 자구설10;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanTree {

	MinHeap h = new MinHeap();
	Node hufTree;
	String code;
	
	public HuffmanTree(String tx) {
		// 해시맵을 이용하여 빈도수를 조사하였다.
		HashMap<Character,Integer> m = new HashMap<Character, Integer>();
		int charNum = 0; //문자의 종류 개수를 센다.
		char[] cc = new char[50]; //문자의 배열
		try{
			BufferedReader in = new BufferedReader(new FileReader(tx));
	        String line = in.readLine();
			while(line != null) {//파일을 한줄씩 내려가면서 끝줄까지 읽음 
				String ul = line.toUpperCase();
				for(int i = 0; i < ul.length(); i++) {
					char c = ul.charAt(i);
					if(m.containsKey(c)) {
						int a = m.get(c);
						m.put(c, a+1);
					}
					else {
						m.put(c, 1);
						cc[charNum] = c;
						charNum++;
					}
				}
				line = in.readLine();
			}
			in.close();
		} catch(IOException e) {System.out.println(e);}
		
		// 힙에 문자와 빈도수를 넣어준다. 
		for(int i =0; i < charNum; i++) {
			Node x = new Node(cc[i], m.get(cc[i]));
			h.add(x);
		}
	}
	
	public void makeHuffman() {
		while(true) {	
		Node b1 = h.remove();
		Node b2 = h.remove();
		hufTree = new Node(b1 , b2);
		if(h.size() == 0) return;
		h.add(hufTree);
		}
	}
	
	public static void printEachCharacterCode(Node htRoot, int []trace, int top)
	{
		if(htRoot.left != null)
		{
			trace[top] = 0;
			printEachCharacterCode(htRoot.left, trace, top+1);
		}
		if(htRoot.right != null)
		{
			trace[top] = 1;
			printEachCharacterCode(htRoot.right, trace, top+1);
		}

		if(htRoot.left == null && htRoot.right == null) // 단말 노드를 만났을 경우
		{
			System.out.print(htRoot.al + "(빈도 수: " + htRoot.freq +"): ");
			for(int i=0;i<top;i++)
				System.out.print(trace[i]);
			System.out.println("");
		}
	}
	
	public static void CharacterCode(Node htRoot, int []trace, int top , char a)
	{
		if(htRoot.left != null)
		{
			trace[top] = 0;
			CharacterCode(htRoot.left, trace, top+1 ,a);
		}
		if(htRoot.right != null)
		{
			trace[top] = 1;
			CharacterCode(htRoot.right, trace, top+1 ,a);
		}

		if(htRoot.left == null && htRoot.right == null) // 단말 노드를 만났을 경우
		{
			if(a == htRoot.al) {
				for(int i=0;i<top;i++)
					System.out.print(trace[i]);
			}
		}
	}
	
 }
