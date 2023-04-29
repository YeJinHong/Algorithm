import java.util.Arrays;

public class Main {
	
	public static void mergeSort(int low, int high, int[] arr) {
		
		int mid = (low+high)/2;
		mergeSort(low, mid, arr);
		mergeSort(mid+1, high, arr);
	}
	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i = 0; i < 10; i++) {
			arr[i] = (int)Math.random()*10;
		}
		
		System.out.println(Arrays.toString(arr));
		
		mergeSort(0, 10, arr);
	}
}