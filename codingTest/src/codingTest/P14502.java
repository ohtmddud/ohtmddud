package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class virus{
	int y;
	int x;
	
	public virus(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}
}

public class P14502 {
	// https://www.acmicpc.net/problem/14502, 연구소(BFS, DFS)
	// dfs, bfs
	static int n, m;
	
	static int[][] map;
	static boolean[][] visit;

	static int result = Integer.MIN_VALUE;
	static int [] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");

		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);

		map = new int[n][m];
		visit = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		dfs(0);
		System.out.println(result);
		
	}

	private static void dfs(int d) {
		if(d == 3) {
			bfs();
			return;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(d + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static void bfs() {
		int[][] vmap = new int[n][m];
		Queue<virus> que = new ArrayDeque<>();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				vmap[i][j] = map[i][j];
				System.out.print(vmap[i][j]);
			}
			System.out.println();
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(vmap[i][j] == 2) {
					que.add(new virus(i, j));
				}
			}
		}
		
		while(!que.isEmpty()) {
			virus poll = que.poll();
			for(int i=0; i<4; i++) {
				int nx = poll.x + dx[i];
				int ny = poll.y + dy[i];
				if(nx>=0 && ny>=0 && nx<m && ny<n) {
					if(vmap[ny][nx] == 0) {
						vmap[ny][nx] = 2;
						que.add(new virus(ny, nx));
					}
				}
			}
		}
		count(vmap);
		
	}

	private static void count(int[][] vmap) {
		int tmp = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(vmap[i][j] == 0) {
					tmp++;
				}
			}
		}
		result = Math.max(result, tmp);
	}

}
