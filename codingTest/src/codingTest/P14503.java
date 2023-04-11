package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14503 {
	// https://www.acmicpc.net/problem/14503, 로봇 청소기
	// dfs
	static int N, M, cnt;	
	static int[][] map;
	static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
	
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		cnt = 1;
		
		map = new int[M][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(r, c, d);
		System.out.println(cnt);
	}

	private static void dfs(int r, int c, int d) {
		map[r][c] = -1;
		
		for(int i=0; i<4; i++) {
			d = (d + 3) % 4;
			int ny = r + dy[d];
			int nx = c + dx[d];
			
			if(ny>=0 && ny<M && nx>=0 && nx<N && map[ny][nx]==0) {
				cnt++;
				dfs(ny, nx, d);
				return;
			}
		}
		int reverse = (d + 2) % 4;
		int ry = r + dy[reverse];
		int rx = c + dx[reverse];
		
		if(ry>=0 && ry<M && rx>=0 && rx<N && map[ry][rx]!=1) {
			dfs(ry,rx,d);
			
		}
		
	}

}
