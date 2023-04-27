package ReferenceCode;

public class MaxHeap {
	public void heapPush(int[] heap, int heapSize, int value) {
		heap[heapSize] = value;
	
		int current = heapSize;
		while(current > 0 && heap[(current-1)/2] < heap[current]) {
			int temp = heap[current];
			heap[current] = heap[(current-1)/2];
			heap[(current-1)/2] = temp;
			
			current = (current-1)/2;
		}
		
		heapSize = heapSize + 1;
	}

	public int heapPop(int[] heap, int heapSize) {
		int value = heap[0];
		heapSize = heapSize - 1;

		heap[0] = heap[heapSize];

		int current = 0;
		while (current * 2 + 1 < heapSize) {

			int child;
			if (current * 2 + 2 == heapSize) {
				child = current * 2 + 1;
			} else {
				child = heap[current * 2 + 1] > heap[current * 2 + 2] ? current * 2 + 1 : current * 2 + 2;
			}
			
			if(heap[child] < heap[current]) {
				break;
			}
			
			int temp = heap[current];
			heap[current] = heap[child];
			heap[child] = temp;
			
			current = child;
		}

		return value;
	}

}
