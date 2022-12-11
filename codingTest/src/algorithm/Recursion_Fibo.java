package algorithm;

public class Recursion_Fibo {

	public static void main(String[] args) {
//		피보나치 수열 연습
		System.out.println(Fibonacci(4));
	}

	// 1 1 2 3 5 8 13..
	// 피보나치 수열
	static int Fibonacci(int n) {
//		1. Base case - num = 1.
		if (n == 1 || n == 2) {
			return 1;
		}
//		2. Recursive calls
		return Fibonacci(n - 2) + Fibonacci(n - 1);
	}

}
