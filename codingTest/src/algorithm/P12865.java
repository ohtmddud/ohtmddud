package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865 {
	// https://www.acmicpc.net/problem/12865, 평범한 배낭
	// https://st-lab.tistory.com/141 (해설)
	private static int[] W, V;
	private static Integer[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		W = new int[N + 1];
		V = new int[N + 1];
//		int[] A = new int[K + 1];
		A = new Integer[N][K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(knapsack(N, K));
		// Bottom-Up 1

		// Bottom-Up 2

	}

	// Top-down
	static int knapsack(int i, int k) {
		if (i < 0) {
			return 0;
		}

		if (A[i][k] == null) {

			if (W[i] < k) {
				A[i][k] = knapsack(i - 1, k);
			} else {
				A[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - W[i]) + V[i]);
			}
		}
		return A[i][k];
	}

}
