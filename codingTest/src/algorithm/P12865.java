package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865 {
	// https://www.acmicpc.net/problem/12865, 평범한 배낭

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] W = new int[N + 1];
		int[] V = new int[N + 1];
		int[] dp = new int[K + 1];
//		int[][] dp = new int[N][K+1]; 
				
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		// Bottom-Up 1
		
		// Bottom-Up 2
		
	}
	// Top-down
	static int knapsack(int i, int k) {
		return 0;
	}
	

}
