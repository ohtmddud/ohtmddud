package algorithm;

public class Recursion_Fibo {

	public static void main(String[] args) {
//		피보나치 수열 연습
		System.out.println(Fibonacci(4));

		fib = new int[10];
		for (int n = 1; n < 10; n++) {
			System.out.print(fibo(n) + " ");
		}
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

	
	static int[] fib;
	static int fibo(int n) {
		
		// memoization
		if (fib[n] > 0) {
			return fib[n];
			
		} else if (n == 1) {
			return fib[1] = 1;
			
		} else if (n == 2) {
			return fib[2] = 1;
		}
		
		return fib[n] = fibo(n - 2) + fibo(n - 1);
	}

}
