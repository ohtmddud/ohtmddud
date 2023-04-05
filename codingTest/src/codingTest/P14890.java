package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14890 {
	// https://www.acmicpc.net/problem/14890, 경사로
	private static int N, L, Cnt;
	private static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		Cnt = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			if(CheckPath(i, 0, true)) {
				Cnt++;
			
			}
			if(CheckPath(0, i, false)) {
				Cnt++;
			}
		}
		System.out.println(Cnt);
	}

	private static boolean CheckPath(int x, int y, boolean flag) {
		int[] height = new int[N];
		boolean[] visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			if(flag) {
				height[i] = map[x][i];
			}
			else {
				height[i] = map[i][y];
			}
		}
		
		for(int i=0; i<N-1; i++) {
			// 높이가 같을 때
			if(height[i] == height[i+1]) {
				continue;
			}
			// 내려 갈 때
			else if(height[i] - height[i+1] == 1) {
				for(int j=i+1; j<=i+L; j++) {
					// 범위를 넘거나 칸의 높이가 다르거나 이미 경사로가 있을 경우
					if(j>=N || height[i+1]!=height[j] || visited[j]) {
						return false;
					}
					visited[j] = true;
				}
			}
			// 올라 갈 때
			else if(height[i] - height[i+1] == -1) {
				for(int j=i; j>i-L; j--) {
					
					// 범위를 넘거나 칸의 높이가 다르거나 이미 경사로가 있을 경우
					if(j<0 || height[i]!=height[j] || visited[j]) {
						return false;
					}
					visited[j] = true;
				}
			}
			// 높이가 2칸 이상 차이 날 때
			else {
				return false;
			}
		}
		return true;
	}
}
