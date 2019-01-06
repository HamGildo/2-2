package 자구설11;

import java.lang.Math;

public class Deap {
	int[] deap;
	int n = 0; //deap에 있는 원소의 개수; 루트는 비어 있다.

	public Deap(int maxSize) {
		deap = new int[maxSize]; 
	}
        
        //deap의 크기를 i로 증가시키고 기존의 원소를 복사한다.  
    private void increaseheap(int i){
        int [] newDeap = new int [i];
        System.arraycopy(deap, 0, newDeap, 0, deap.length);
        deap = newDeap;      
    }

        //인덱스 i가 min-heap에 위치해 있으면 true를 리턴하고, 그렇지 않으면 false를 리턴한다
	private boolean inMinHeap(int i) {
		while(true) {
			if(i == 1) return true;
			if(i == 2) return false;
			i = (i-1)/2;
		}
	}

        //인덱스 i가 min-heap에 위치해 있을때 max partner의 인덱스를 리턴한다
	private int maxPartner(int i) {
		  int j = (int)(i + Math.pow(2, (int)(Math.log(i+1)/Math.log(2))-1));
	      if(j > n) return (j-1)/2;
	      else return j;
	}

        //인덱스 i가 max-heap에 위치해 있을때 min partner의 인덱스를 리턴한다
	private int minPartner(int i) {
		 int j = (int)(i - Math.pow(2, (int)(Math.log(i+1)/Math.log(2))-1));
	      return j;
	}
        
        //min-heap에 있는 인덱스 at 위치에 key를 삽입
	private void minInsert(int at, int key) {
		deap[at] = key;
		int parent = (at -1)/2;	 
		int child = at;
		while(parent >= 1) {
		if(deap[parent] > deap[child]) {
			int temp = deap[child];
			deap[child] = deap[parent];
			deap[parent] = temp;
		}
		child = parent;
		parent = (child -1)/2;
		}
	}

        //max-heap에 있는 인덱스 at 위치에 key를 삽입
	private void maxInsert(int at, int key) {
		deap[at] = key;
		int parent = (at -1)/2;	 
		int child = at;
		while(parent > 1) {
		if(deap[parent] < deap[child]) {
			int temp = deap[child];
			deap[child] = deap[parent];
			deap[parent] = temp;
		}
		child = parent;
		parent = (child -1)/2;
		}
	}

        //max 값을 삭제하여 리턴한다
	public int deleteMax() {
		int temp = deap[n];
		n--; // 마지막 노드 temp에 저장 후, 삭제
		//최소값 노드 삭제 후, 힙 재구성
		int max = deap[2];
		int i = 2; //최대값 인덱스
		int left = i *2 +1; //왼쪽 자식 인덱스
		int right = (i + 1)*2 ; //오른쪽 자식 인덱스
		while(left < n) { //리프노드찾기
		if(deap[left] > deap[right]) {
			deap[i] = deap[left];
			i = left;
		}
		else {
			deap[i] = deap[right];
			i = right;
		}
		left = i * 2 +1;
		right = (i + 1) * 2;
		}
		int j = minPartner(i);
		int maxChld; //minpartner의 자식들 중 더 큰 값을 가진 인덱스
		if(deap[j*2 +1] < deap[(j+1)*2])
			maxChld = (j+1)*2;
		else
			maxChld = j*2 +1;
		if(deap[maxChld] > temp) {
			deap[i] = deap[maxChld];
			deap[maxChld] = temp;
		}
		else
			deap[i] = temp;
		return max;
	}
        
        //min 값을 삭제하여 리턴한다
	public int deleteMin() {
		int temp = deap[n];
		n--; // 마지막 노드 temp에 저장 후, 삭제
		//최소값 노드 삭제 후, 힙 재구성
		int min = deap[1];
		int i = 1; //최소값 인덱스
		int left = i *2 +1; //왼쪽 자식 인덱스
		int right = (i + 1)*2 ; //오른쪽 자식 인덱스
		while(left < n) { //리프노드찾기
		if(deap[left] < deap[right]) { //왼쪽 자식이 더 작음
			deap[i] = deap[left];
			i = left;
		}
		else {
			deap[i] = deap[right];
			i = right;
		}
		left = i * 2 +1;
		right = (i + 1) * 2;
		}
		int j = maxPartner(i);
		if(temp > deap[j]) {
			deap[i] = deap[j];
			maxInsert(j,temp);
		}
		else
			deap[i] = temp;
		
		return min;
	}
        
        //x를 삽입한다
	public void insert(int x) {

		if (n == deap.length - 1) {
			System.out.println("The heap is full. The heap size is doubled");
			increaseheap(deap.length*2);
		}
		n++;

		if (n == 1) {
			deap[1] = x;
			return;
		}
		if (inMinHeap(n)) {
			int i = maxPartner(n);
			if (x > deap[i]) {
				deap[n] = deap[i];
				maxInsert(i, x);
			} else {
				minInsert(n, x);
			}
		} else {				
			int i = minPartner(n);
			if (x < deap[i]) {
				deap[n] = deap[i];
				minInsert(i, x);
			} else {
				maxInsert(n, x);
			}			
		}
	}

	//deap을 프린트한다
	public void print() {
	        int levelNum = 2;
	        int thisLevel = 0;
	        int gap = 8;
	        for (int i = 1; i <= n; i++) {
	            for (int j = 0; j < gap-1; j++) {
	                System.out.print(" ");
	            }
	            if (thisLevel != 0) {
	                for (int j = 0; j < gap-1; j++) {
	                    System.out.print(" ");
	                }
	            }
	            if (Integer.toString(deap[i]).length() == 1) {
	                System.out.print(" ");
	            }
	            System.out.print(deap[i]);
	            thisLevel++;
	            if (thisLevel == levelNum) {
	                System.out.println();
	                thisLevel = 0;
	                levelNum *= 2;
	                gap/=2;
	            }
	        }
	        System.out.println();
	        if (thisLevel != 0) {
	            System.out.println();
	        }
	}
	
	public static void main(String[] argv) {
		
		Deap a = new Deap(10);
                int i=0;
		int[] data = { 4, 65, 8, 9, 48, 55, 10, 19, 20, 30, 15, 25, 50 };
		for (i = 0; i < 9; i++) {
			a.insert(data[i]);
		}

		System.out.println("initial Deap");
		a.print();
		
		for (   ; i < data.length; i++) {
			a.insert(data[i]);
		}
		
		System.out.println("enlarged Deap");
		a.print();
		
		System.out.println("delete Min");
		a.deleteMin();
		a.print();
		
		System.out.println("delete Min");
		a.deleteMin();
		a.print();
		
		System.out.println("delete Min");
		a.deleteMin();
		a.print();
		
		System.out.println("delete Max");
		a.deleteMax();
		a.print();
		
		System.out.println("delete Max");
		a.deleteMax();
		a.print();
		
		System.out.println("delete Max");
		a.deleteMax();
		a.print();

	}

}
