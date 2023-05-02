package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865_BU1 {
	static int[] W;
	static int[] V;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); 
		W = new int[N + 1]; // 무게
		V = new int[N + 1]; // 가치
		A = new int[N + 1][K + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				// i번째의 무게를 더 담을 수 없는 경우
				if (W[i] > j) {
					A[i][j] = A[i - 1][j];

					// i번째의 무게를 담을 수 있는 경우
				} else {
					A[i][j] = Math.max(A[i - 1][j], A[i - 1][j - W[i]] + V[i]);
				}
			}

		}
		System.out.println(A[N][K]);
	}

}
