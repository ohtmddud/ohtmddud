package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14501 {
	// 퇴사 https://www.acmicpc.net/problem/14501
	private static int N;
	private static int[] T, P;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N+1];
		P = new int[N+1];
		int[] DP = new int[N+2];
		
		StringTokenizer st;
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());  
			P[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(N);
		for(int i=N; i>0; i--) {
			if(i + T[i] > N + 1) {
				DP[i] = DP[i+1];
			} else {
				DP[i] = Math.max(DP[i+1], P[i] + DP[i+T[i]]);
			}
		}
		
		System.out.println(DP[1]);

	}

}
