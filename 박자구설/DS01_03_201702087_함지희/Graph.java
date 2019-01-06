package 자구설01;
import java.util.Stack;

public class Graph {
	int size;
	String[] vertices; //정점
	boolean[][] a; //인접행렬
	boolean[] visited;
	
	public Graph(String[] args) {
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		a = new boolean[size][size];
		visited = new boolean[size];
	}
	public void add(String v, String w) {
		int i = index(v), j = index(w);
		a[i][j] = a[j][i] = true;
	}
	private int index(String v) {
		for(int i = 0; i < size; i ++)
			if(vertices[i].equals(v)) return i;
		return a.length;
	}
	public String toString() {
		if(size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{" + vertex(0));
		for (int i = 1; i < size; i++)
			buf.append(","+vertex(i));
		return buf + "}";
	}
	private String vertex(int i) {
		StringBuffer buf = new StringBuffer(vertices[i] + ":");
		for (int j = 0; j < size; j++)
			if(a[i][j]) buf.append(vertices[j]);
		return buf + "";
	}
	public void degree(String v) { //각 정점에 인접한 정점들의 수를 프린트하는 메소드
		int cnt = 0; //카운트
			int i = index(v); //v정점의 위치를 찾는다 
		for(int j = 0; j < size; j++) {
			if(a[i][j] == true) //v정점이 있는 행에서 true인 것을 찾아 카운트 증가시킴(인접한 정점 찾기)
				cnt++;
				}
			
		System.out.println(v + "에 인접한 정점들의 수는 " + cnt);
	}
	public void findPath(String v, String w) { //길이가 2인 경로가 존재하는지 찾아 프린트하는 메소드
		int first = index(v), second = index(w); //두 정점의 위치를 찾음
		int cnt = 0;
		System.out.print(v+"와 "+w+"사이의 길이가 2인 경로 :");
			for(int i = 0; i < size; i ++) {
				if(a[first][i] == true && i != second) { //인접한 정점끼리는 길이가 1이므로 v랑w가 인접하면 안됨, 그 외의 다른 인접한 정점을 찾는다
					if(a[i][second] == true) { //위에서 찾은 인접한정점(길이 1)에서 목적지인 w과 인접한 정점을 찾는다(길이2)
						cnt++;
						System.out.print(vertices[first]+"-"+vertices[i]+"-"+vertices[second]+"  ");
					}
				}		
			}
			if(cnt == 0)
				System.out.println("없음");
			else
				System.out.println();
			
		}
	public void recu_dfs(int v) {
		visited[v] = true;
		System.out.print(vertices[v]+" ");
		for(int i = 0; i < size; i++) {
			if(a[v][i] == true && visited[i] != true)//인접하는데 방문하지 않은 정점을 찾자
				recu_dfs(i);
		}
	}
	public void recu_dfs_tree(int v) {
		visited[v] = true;
		for(int i = 0; i < size; i++) {
			if(a[v][i] == true && visited[i] != true) {//인접하는데 방문하지 않은 정점을 찾자
				System.out.println("정점 "+vertices[i]+"는 "+"정점 "+vertices[v]+"의 자식이다.");
				recu_dfs_tree(i);
			}
		}
	}
	public void dfs_tree(int v) {
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		while(!stack.isEmpty()) { 
			int aa = stack.pop();
			visited[aa] = true;
			for(int i = 0; i < size; i++) {
				if( (a[aa][i] == true) && (visited[i] == false)) { //x와 인접하고 아직 방문하지 않은 y찾기
					visited[i] = true;
					stack.push(i);
					System.out.println("정점 "+vertices[i]+"는 정점 "+vertices[aa]+"의 자식이다.");
				}
			}
		}
	}
	public void initial_visited() {
		for(int j = 0 ; j < size; j ++) {
			visited[j] = false;
		}
	}
	

}