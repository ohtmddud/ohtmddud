package algorithm;
// 시간복잡도: O(logN) 
public class BinarySearch {
	static int[] arr = {17, 28, 43, 67, 88, 92, 100};
	
	public static void main(String[] args) {
		BinarySearch binary = new BinarySearch();
		System.out.println("1. 순환 호출을 이용한 이진 탐색");
		System.out.println(binary.Binary1(17, 0, arr.length-1));
		
		System.out.println("2. 재귀를 이용한 이진 탐색");
		System.out.println(Binary2(67, 0, arr.length-1));
	}
	
	public int Binary1(int key, int low, int high) {
		int mid;
		while(low <= high) {
			mid = (low + high) / 2;
			if(key == arr[mid]) {
				return mid + 1;
			}else if(key < arr[mid]) {
				high = mid - 1;
			}else {
				low = mid + 1;
			}
		}
		return -1; // 없음.
	}
	
	public static int Binary2(int key, int low, int high) {
		int mid;
		if(low <= high) {
			mid = (low + high) / 2;
			if(key == arr[mid]) {
				return mid + 1;
			}else if(key < arr[mid]) { // 왼쪽.
				return Binary2(key, low, mid - 1);
			}else { // 오른쪽.
				return Binary2(key, mid + 1, high);
			}
		}
		return -1; // 없음.
	}
	
}
