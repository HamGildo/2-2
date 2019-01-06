package �ڱ���02;

public class Graph {
	int size;
	String[] vertices; //���� ����
	Node[] a; //�� ������ �ϳ��� ����Ʈ�� ������ �Ѵ�
	public Graph(String[] args) { //������
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
	
	private int index(String v) { //�����迭���� index��ȣ�� �˾Ƴ�
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
		System.out.print(vertices[index(v)]+"�� "+vertices[index(w)]+"������ ���̰� 2�� ��� : ");
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
			System.out.println("����");
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
