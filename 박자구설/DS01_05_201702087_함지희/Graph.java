package �ڱ���05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Graph {
	int vertexSize;
	int edgeSize;
	String[] vertices; //����
	float[][] a; //�������
	boolean[] visited;
	
		public Graph (String gf) { //�׷��� �������̰�, String���� ���ϰ�θ� �Ű������� �޴´�.
			try{
				BufferedReader in = new BufferedReader(new FileReader(gf));
				String line = in.readLine(); //������ ù��° �� ����
				StringTokenizer parser = new StringTokenizer(line," ,:;-.?!");
				String vs = parser.nextToken(); //ù �ٿ� ������ ������ ������ ������ �����Ƿ� 
				String es = parser.nextToken(); //�� �ܾ �޾Ƽ� 
				vertexSize = Integer.parseInt(vs); //������ size�� �־���
				edgeSize = Integer.parseInt(es); 
				a = new float[vertexSize][vertexSize]; //a������� ���� �� 0���� �ʱ�ȭ
				for(int i = 0; i < vertexSize; i ++) {
					for(int j = 0; j < vertexSize; j++) {
						a[i][j] = 0;
					}
				}
				visited = new boolean[vertexSize];
				vertices = new String[vertexSize];
				line = in.readLine(); //�ι�°�ٷ�	
				while(line != null) {//������ ���پ� �������鼭 ���ٱ��� ���� 
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
		boolean idInitialMin; //������ ����� ���Ҷ� ó�� ���� min�� �־��ֱ� ����

		visited[0] = true; //���� 0�� ���۳��� �Ѵ�.
		for(int i = 0; i < near.length; i++) { //near�迭 ����
			if(a[0][i] == 0) 
				near[i] = -1;
			else
				near[i] = 0;
		}
		System.out.println("���õ� ������");
		while(cntEdge != vertexSize-1) { 			
		idInitialMin = false; //false������ �ʱ�ȭ
			for(int z = 0; z < near.length; z++) {
				if(near[z] != -1 && visited[z] != true) {
					if(idInitialMin == false) { //�� ó�� �����ϴ� ������ ����� min�� �־���
						min = a[near[z]][z];
						minV = z;
						minVV = near[z];
						idInitialMin = true;
					}
					if(min > a[near[z]][z]) {//�� �������� �����ϴ� ������ ����� min���� ������ min����
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
		//near������Ʈ
			near[minV] = -1; 
			for(int j = 0; j < near.length; j++) { //minV������ �����ϰ� ���� �������� ���� ����ã��
				if(a[minV][j] != 0 && visited[j] != true)
					if(near[j] != -1 && a[minV][j] < a[near[j]][j]) {
						near[j] = minV; //minV������ �����ϴٴ� �� ǥ��
					}
					else if(near[j] == -1)
						near[j] = minV;							
			}
	    }
		System.out.println("\n"+ "�� ��� : "+ sum);	
	}
	

}
