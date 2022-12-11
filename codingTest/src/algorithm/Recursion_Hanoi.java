package algorithm;

public class Recursion_Hanoi {

	public static void main(String[] args) {
//		하노이의 탑 연습
		int count = hanoi(3, 'A', 'C', 'B');
		System.out.println(count);
		String hi = "hello";
		boolean he = hi.equals("hello");
		System.out.println(he);
	}

	// 하노이의 탑
	// n 번째 탑을 목표로 옮기기 전, n-1 개의 탑이 또 다른 공간에 쌓여야 한다.
	static int count = 0;
	static int hanoi(int n, char from_rod, char to_rod, char help_rod) {		
//		1. Base case - The base case in our code is when we only have one disk. That is n=1.
		if (n == 1) {
			System.out.println("1번 디스크를 " + from_rod + " 에서 " + to_rod + " 으로 옮깁니다.");
			count++;
			return count;
		} else {
//		2. Recursive calls - The recursive calls to solve tower of Hanoi are as follows:
			count++;
			hanoi(n - 1, from_rod, help_rod, to_rod);
			System.out.println(n + "번 디스크를 " + from_rod + " 에서 " + to_rod + " 으로 옮깁니다.");
			hanoi(n - 1, help_rod, to_rod, from_rod);
			return count;
		}
		
	}

}
