package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class pro_3190 {
	// 백준 3190 뱀(dummy) (https://www.acmicpc.net/problem/3190)
	private static int N, apple, L;
	private static int[][] map;
	private static List<int[]> snake = new ArrayList<>();
	private static HashMap<Integer, String> hm = new HashMap<>();
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		apple = Integer.parseInt(br.readLine());
		for(int i = 0; i < apple; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i < apple; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			String y = st.nextToken();
			hm.put(x, y);
		}
		
		System.out.println(N);
		System.out.println(apple);
		System.out.println(L);
	}
}
