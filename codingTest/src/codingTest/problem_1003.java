package codingTest;

import java.util.Scanner;

public class problem_1003 {
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		int count = key.nextInt();
		for (int i = 0; i < count; i++) {
			int value = key.nextInt();
			int result = fibonacci(value);
			int result_Z = fibonacci_Z(value);
			System.out.println(result_Z + " " + result);
		}
		key.close();
	}

	static int fibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}

	static int fibonacci_Z(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 0;
		} else {
			return fibonacci_Z(n - 1) + fibonacci_Z(n - 2);
		}
	}

}
