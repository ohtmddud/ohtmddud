package algorithm;

public class Sort {
	static int []arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};
	
	public static void swap(int[] a, int b, int c) {
		int tmp = a[b];
		a[b] = a[c];
		a[c] = tmp;
	}
	
	public void SelectSort() {
		for(int i=0; i<arr.length-1; i++) {
			int Idx = i;
			for(int j=i+1; j<arr.length; j++) {
				if(arr[j] < arr[Idx]) {
					swap(arr, Idx, j);
				}
			}
		}
	}
	
	public void InsertionSort() {
		for(int i=1; i<arr.length; i++) {
			for(int j=i; j>=1; j--) {
				if(arr[j] < arr[j-1]) { // 한 칸씩 왼쪽으로 이동
					swap(arr, j, j-1);
				}else break; // 작은 값을 만나면 정지
			}
		}
	}
	
	public void QuickSort() {
		
	}
	
	public void CountSort() {
		
	}
	
	public static void main(String[] hi) {
		System.out.println("hi");
		Sort sort = new Sort();
		
//		sort.SelectSort();
		sort.InsertionSort();
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " → ");
		}
	}

}

