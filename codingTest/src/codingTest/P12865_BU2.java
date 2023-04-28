package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865_BU2 {
	static int[] W;
	static int[] V;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		W = new int[N + 1]; // 무게
		V = new int[N + 1]; // 가치
		A = new int[K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < N; i++) {
			
			// K 부터 탐색하여 담을 수 있는 무게 한계치가 넘지 않을 때까지 반복
			for (int j = K; K - W[i] > 0; j--) {
				A[j] = Math.max(A[j], A[j - W[i]] + V[i]);
			}
		}

		System.out.println(A[K]);

	}

}
