package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class pro_14500 {
	// 테트로미노 : https://www.acmicpc.net/problem/14500
	private static int N, M;
	private static int[][] map;
	private static boolean[][] visit;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	private static int max = Integer.MIN_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visit[i][j] = true;
				solve(i, j, map[i][j], 1);
				visit[i][j] = false;
			}
		}
		System.out.println(max);
	}
	
	public static void solve(int x, int y, int sum, int cnt) {
		if(cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=N || ny<0 || ny>=M) {
				continue;
			}
			if(!visit[nx][ny]) {
				
				// 凸 테트로미노 조건
				if(cnt == 2) {
					visit[nx][ny] = true;
					solve(x, y , sum + map[nx][ny], cnt + 1);
					visit[nx][ny] = false;
				}
				
				visit[nx][ny] = true;
				solve(nx, ny , sum + map[nx][ny], cnt + 1);
				visit[nx][ny] = false;
			}
		}
		
	}

}
