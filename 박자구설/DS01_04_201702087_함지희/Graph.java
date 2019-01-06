package �ڱ���04;
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
	
	public Graph (String gf) { //�׷��� �������̰�, String���� ���ϰ�θ� �Ű������� �޴´�.
		try{
			BufferedReader in = new BufferedReader(new FileReader(gf));
			String line = in.readLine(); //������ ù��° �� ����
			StringTokenizer parser = new StringTokenizer(line," ,:;-.?!");
			String vs = parser.nextToken(); //ù �ٿ� ������ ������ ������ ������ �����Ƿ� 
			String es = parser.nextToken(); //�� �ܾ �޾Ƽ� 
			vertexSize = Integer.parseInt(vs); //������ size�� �־���
			edgeSize = Integer.parseInt(es); 
			a = new Edge[edgeSize]; // Edge�迭�� ������� �迭 ����
			parent = new int[vertexSize];
			for(int i = 0; i < vertexSize; i++) { //parent�迭�� -1��
				parent[i] = -1;
			}
			int i = 0;
			line = in.readLine(); //�ι�°�ٷ�	
			while(line != null) {//������ ���پ� �������鼭 ���ٱ��� ���� 
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
		System.out.println("-�ּ� ���� Ʈ���� ���Ե� ����");
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
	
	public void sort(Edge a[]) { //�����迭�� �����������ִ� �Լ�
        
        for(int i=0; i<a.length-1; i++) {
            int min = i;
            for(int j=i+1; j<a.length; j++) { 
                if(a[j].weight < a[min].weight) { //�������� 
                    min = j;
                }
            }
            Edge swap = a[i];
            a[i] = a[min];
            a[min] = swap;
        }
	}



}
