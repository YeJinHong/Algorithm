package referenceCode;

import java.util.Arrays;

public class MinHeap {
	static final int MAX_SIZE = 13;
	int heap[] = new int[MAX_SIZE];
	int heapSize;
	
	public void init() {
		heapSize = 0;
	}
	
	public int heapPop() {
		if(heapSize <= 0) {
			System.out.println("heap is Empty");
			return -1;
		}

		int value = heap[0];
		heapSize = heapSize - 1;
		
		heap[0] = heap[heapSize];
		
		int current = 0;
		while(current*2 + 1 < heapSize) {
			
			int child;
			if(current*2 + 2 == heapSize) {
				child = current*2 + 1;
			} else {
				child = heap[current*2 +1] < heap[current*2 + 2] ? current*2 + 1 : current*2 + 2;
			}
			
			if(heap[current] < heap[child]) {
				break;
			}
			
			int temp = heap[current];
			heap[current] = heap[child];
			heap[child] = temp;
			
			current = child;
		}
		
		return value;
	}
	
	public int heapPush(int value) {
		if(heapSize == MAX_SIZE) {
			System.out.println("heap is full");
			return 0;
		}
		
		heap[heapSize] = value;
		
		int current = heapSize;
		
		while(current > 0 && heap[current] < heap[(current-1)/2]) {
				int temp = heap[current];
				heap[current] = heap[(current-1)/2];
				heap[(current-1)/2] = temp;
				
				current = (current-1)/2;
		}
		heapSize = heapSize + 1;
		
		return 1;
	}
	
}
