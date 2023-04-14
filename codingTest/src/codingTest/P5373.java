package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5373 {
	// https://www.acmicpc.net/problem/5373, 큐빙
	static int TC;
	static int[][] cube;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());

		for (int i = 0; i < TC; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				String method = st.nextToken(" ");
				solution(method);
			}
			System.out.println(view());
		}

	}
	// 큐브를 돌림.
	private static void solution(String method) {
		
	}
	// 큐브의 윗단을 출력.
	private static String view() {
		return null;
	}



}
