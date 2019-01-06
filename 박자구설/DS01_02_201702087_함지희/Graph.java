package 자구설02;

public class Graph {
	int size;
	String[] vertices; //정점 저장
	Node[] a; //각 정점당 하나의 리스트를 가지게 한다
	public Graph(String[] args) { //생성자
		size = args.length;
		
		vertices = new String[size];
		for(int i =0; i < size; i ++)
			vertices[i] = args[i];
		
		a = new Node[size];
		for(int j = 0; j <size; j ++)
			a[j] = new Node(index(args[j]));
	}
	
	public String toString() {
		if (size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{" + a[0]); 
		for(int i = 1; i < size; i ++)
			buf.append("," + a[i]);
		return buf + "}";
	}
	
	private int index(String v) { //정점배열에서 index번호를 알아냄
		for(int i = 0; i < size; i++)
			if(vertices[i].equals(v)) return i;
		return vertices.length;
	}
	
	public void add(String v, String w) {
		a[index(v)].add(index(w));
		a[index(w)].add(index(v));
	}
	
	public void findPath(String v, String w) {
		int cnt =0;
		System.out.print(vertices[index(v)]+"와 "+vertices[index(w)]+"사이의 길이가 2인 경로 : ");
		for(Node p = a[index(v)].edge; p != null; p = p.edge) {
			if(p.vertex != index(w)) {
				for(Node p2 = a[p.vertex].edge; p2 != null; p2 = p2.edge) {
					if(p2.vertex == index(w)) {
						System.out.print(vertices[index(v)]+"-"+vertices[p.vertex]+"-"+vertices[index(w)]+"  ");
						cnt++;
					}
				}
			}
		}
		if(cnt == 0)
			System.out.println("없음");
		else
			System.out.println();
	}
	
	private class Node{
		int vertex; Node edge;
		Node(int vertex) { this.vertex = vertex; };
		Node (int vertex , Node edge) {this.vertex = vertex; this.edge = edge;}
		public void add(int j) {edge = new Node(j , edge);};
		
		public String toString() {
			StringBuffer buf = new StringBuffer(vertices[vertex]);
			if (edge != null) buf.append(":");
			for (Node p = edge; p != null; p = p.edge)
				buf.append(Graph.this.vertices[p.vertex]);
			return buf + "";
		}
	}

}
