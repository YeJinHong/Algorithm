import java.util.Arrays;
import java.util.Scanner;

import ReferenceCode.MaxHeap;

public class Main {

	public static void main(String[] args) {
		int T, N;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();

			MaxHeap maxHeap = new MaxHeap();

//			maxHeap.init();
			int[] heap = new int[14];

			for (int i = 0; i < N; i++) {
				int value;
				value = sc.nextInt();
				maxHeap.heapPush(heap, i, value);
			}

			System.out.printf("#%d ", test_case);

			for (int i = 0; i < N; i++) {
				int value = maxHeap.heapPop(heap, N - i);
				System.out.printf("%d", value);
				System.out.printf(" ");
			}
			System.out.printf("\n");
			System.out.printf(" ");
		}
	}
}