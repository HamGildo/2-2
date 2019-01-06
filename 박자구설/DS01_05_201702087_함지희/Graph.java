package 자구설05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Graph {
	int vertexSize;
	int edgeSize;
	String[] vertices; //정점
	float[][] a; //인접행렬
	boolean[] visited;
	
		public Graph (String gf) { //그래프 생성자이고, String형의 파일경로를 매개변수로 받는다.
			try{
				BufferedReader in = new BufferedReader(new FileReader(gf));
				String line = in.readLine(); //파일의 첫번째 줄 읽음
				StringTokenizer parser = new StringTokenizer(line," ,:;-.?!");
				String vs = parser.nextToken(); //첫 줄엔 정점의 개수와 간선의 개수가 있으므로 
				String es = parser.nextToken(); //두 단어를 받아서 
				vertexSize = Integer.parseInt(vs); //각각의 size에 넣어줌
				edgeSize = Integer.parseInt(es); 
				a = new float[vertexSize][vertexSize]; //a인접행렬 생성 및 0으로 초기화
				for(int i = 0; i < vertexSize; i ++) {
					for(int j = 0; j < vertexSize; j++) {
						a[i][j] = 0;
					}
				}
				visited = new boolean[vertexSize];
				vertices = new String[vertexSize];
				line = in.readLine(); //두번째줄로	
				while(line != null) {//파일을 한줄씩 내려가면서 끝줄까지 읽음 
					StringTokenizer parser1 = new StringTokenizer(line," ,:;-.?!");
					int v1 = Integer.parseInt(parser1.nextToken());
					int v2 = Integer.parseInt(parser1.nextToken());
					float ww = Float.parseFloat(parser1.nextToken());
					add(v1,v2,ww);
					line = in.readLine();
				}
				in.close();
				
			} catch(IOException e) {System.out.println(e);}
		}
	
	public void add(int v1, int v2 , float weight) {
		int i = v1, j = v2;
		a[i][j] = a[j][i] = weight;
	}
	
	public void prim() {
		int[] near = new int[vertexSize];
		float min = 0;
		int minV = 0;
		int minVV = 0;
		int cntEdge = 0;
		float sum = 0;
		boolean idInitialMin; //간선의 비용을 비교할때 처음 값을 min에 넣어주기 위함

		visited[0] = true; //정점 0을 시작노드로 한다.
		for(int i = 0; i < near.length; i++) { //near배열 생성
			if(a[0][i] == 0) 
				near[i] = -1;
			else
				near[i] = 0;
		}
		System.out.println("선택된 간선들");
		while(cntEdge != vertexSize-1) { 			
		idInitialMin = false; //false값으로 초기화
			for(int z = 0; z < near.length; z++) {
				if(near[z] != -1 && visited[z] != true) {
					if(idInitialMin == false) { //맨 처음 접근하는 간선의 비용을 min에 넣어줌
						min = a[near[z]][z];
						minV = z;
						minVV = near[z];
						idInitialMin = true;
					}
					if(min > a[near[z]][z]) {//그 다음으로 접근하는 간선의 비용이 min보다 작으면 min갱신
						min = a[near[z]][z];
						minV = z;
						minVV = near[z];
					}	
				}
			}
		System.out.print("("+minVV+" , "+minV+"): "+min+"  ");
		cntEdge++;
		visited[minV] = true;
		sum += min;
		//near업데이트
			near[minV] = -1; 
			for(int j = 0; j < near.length; j++) { //minV정점에 인접하고 아직 접근하지 않은 정점찾기
				if(a[minV][j] != 0 && visited[j] != true)
					if(near[j] != -1 && a[minV][j] < a[near[j]][j]) {
						near[j] = minV; //minV정점과 인접하다는 걸 표시
					}
					else if(near[j] == -1)
						near[j] = minV;							
			}
	    }
		System.out.println("\n"+ "총 비용 : "+ sum);	
	}
	

}
