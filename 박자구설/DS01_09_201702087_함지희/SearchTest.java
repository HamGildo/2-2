package 자구설09;

import java.util.ArrayList;
import java.util.Collections;

public class SearchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 200000;
		int searchN = 500000;
		long start;
		long end;
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList(n);
		System.out.printf("총 %d개의 정수를 무작위 생성합니다.\n",n);
		for(int i = 0; i < n; i ++) {
			list.add(i+1);
		}
		Collections.shuffle(list);
		System.out.printf("정수 생성 완료!\n");
		
		AVLTree a = new AVLTree(list.get(0));
		BinarySearchTree b = new BinarySearchTree(list.get(0));
		QuadraticHashTable q = new QuadraticHashTable();
		
		System.out.printf("각 자료구조에 %d개의 정수를 추가합니다.\n",n);
		for(int i = 0; i < n; i++) {
			a.add(list.get(i));
			b.add(list.get(i));
			q.put(list.get(i),0);
		}
		System.out.println("추가 완료!");
		//avl 
		start = System.currentTimeMillis();
		for(int i = 0; i < searchN; i++) {
			if(a.search(i+1)) {
				cnt++;
				}
		}
		end = System.currentTimeMillis();
		System.out.println("avl 검색 시간 : "+ (float)(end-start)/1000 + "초");
		System.out.println("avl 검색 성공 횟수 : "+cnt+ "회");
		cnt = 0;
		
		//bst 
		start = System.currentTimeMillis();
		for(int i = 0; i < searchN; i++) {
			if(b.search(i+1)) {
				cnt++;
				}
		}
		end = System.currentTimeMillis();
		System.out.println("bst 검색 시간 : "+ (float)(end-start)/1000 + "초");
		System.out.println("bst 검색 성공 횟수 : "+cnt+ "회");
		cnt = 0;
		
		//hash 
		start = System.currentTimeMillis();
		for(int i = 0; i < searchN; i++) {
			if(q.get(i+1)){
				cnt++;
			}
		}
		end = System.currentTimeMillis();
		System.out.println("hash 검색 시간 : "+ (float)(end-start)/1000 + "초");
		System.out.println("hash 검색 성공 횟수 : "+cnt+ "회");


	}

}
