package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem_12100 {
	// 백준 12100 2048(Easy) (https://www.acmicpc.net/problem/12100)
	private static int N;
	private static int[][] map;
	private static int[][] tmp;
	private static boolean[][] visit;
	private static int[] direct;
	private static int ans;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");	
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				System.out.print(map[i][j]);
			}
		}
		System.out.println(N);	
	}
	
	public static void dfs(int end, int idx) {
		if(end == idx) {
			confirm();
		} else {
			for(int i = 0; i < 4; i++) {
				direct[idx] = i;
				dfs(end, idx+1);
			}
		}
	}

	private static void confirm() {
		tmp = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			tmp[i] = map[i].clone();
		}
		for(int i = 0; i < direct.length; i++) {
			visit = new boolean[N][N];
		}
		
	}
}
