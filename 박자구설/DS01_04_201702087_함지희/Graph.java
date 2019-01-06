package 자구설04;
import java.io.*;
import java.util.StringTokenizer;
// String -> int : Integer.parseInt(String);

public class Graph {
	Edge a[];
	int parent[];
	int vertexSize ;
	int edgeSize ;
	
	
	private class Edge{
		int v , w , weight ;
		boolean selected;
		
		public Edge(int v , int w , int weight){
			this.v = v;
			this.w = w;
			this.weight = weight;
		}
	}
	
	public Graph (String gf) { //그래프 생성자이고, String형의 파일경로를 매개변수로 받는다.
		try{
			BufferedReader in = new BufferedReader(new FileReader(gf));
			String line = in.readLine(); //파일의 첫번째 줄 읽음
			StringTokenizer parser = new StringTokenizer(line," ,:;-.?!");
			String vs = parser.nextToken(); //첫 줄엔 정점의 개수와 간선의 개수가 있으므로 
			String es = parser.nextToken(); //두 단어를 받아서 
			vertexSize = Integer.parseInt(vs); //각각의 size에 넣어줌
			edgeSize = Integer.parseInt(es); 
			a = new Edge[edgeSize]; // Edge배열의 사이즈로 배열 만듦
			parent = new int[vertexSize];
			for(int i = 0; i < vertexSize; i++) { //parent배열을 -1로
				parent[i] = -1;
			}
			int i = 0;
			line = in.readLine(); //두번째줄로	
			while(line != null) {//파일을 한줄씩 내려가면서 끝줄까지 읽음 
				StringTokenizer parser1 = new StringTokenizer(line," ,:;-.?!");
				int v1 = Integer.parseInt(parser1.nextToken());
				int v2 = Integer.parseInt(parser1.nextToken());
				int ww = Integer.parseInt(parser1.nextToken());
				a[i] = new Edge(v1 , v2 , ww);
				i++;
				line = in.readLine();
			}
			in.close();
			
		} catch(IOException e) {System.out.println(e);}
	}
	
	
	public void weightedunion(int s1, int s2){
		s1 = collapsingfind(s1);
		s2 = collapsingfind(s2);
		if(parent[s1] <= parent[s2]) {
			parent[s1] += parent[s2];
			parent[s2] = s1;
		}
		else {
			parent[s2] += parent[s1];
			parent[s1] = s2;
		}
	}
	public int collapsingfind(int n) {
		int root;
		for(root = n; parent[root] >= 0; root = parent[root]);
		
		while(parent[n] >= 0) {
			int temp = n;
			n = parent[n];
			parent[temp] = root;
		}
		return root;
	}
	
	public void kruskal() {
		int cnt = 1;
		int w;
		sort(a);
		System.out.println("-최소 신장 트리에 포함된 간선");
		System.out.print("( "+a[0].v+" , "+a[0].w+" )");
		System.out.print(" ");
		weightedunion(a[0].v,a[0].w);
		w = a[0].weight;

		while(cnt < vertexSize-1) {
			if(collapsingfind(a[cnt].v) != collapsingfind(a[cnt].w)) {
			System.out.print("( "+a[cnt].v+" , "+a[cnt].w+" )");
			System.out.print(" ");
			weightedunion(a[cnt].v , a[cnt].w);
			w += a[cnt].weight;
			cnt++;
			}
			else
				cnt++;
		}
		System.out.println();
		System.out.println("- Min cost : "+ w);
				
	}
	
	public void sort(Edge a[]) { //간선배열을 선택정렬해주는 함수
        
        for(int i=0; i<a.length-1; i++) {
            int min = i;
            for(int j=i+1; j<a.length; j++) { 
                if(a[j].weight < a[min].weight) { //오름차순 
                    min = j;
                }
            }
            Edge swap = a[i];
            a[i] = a[min];
            a[min] = swap;
        }
	}



}
