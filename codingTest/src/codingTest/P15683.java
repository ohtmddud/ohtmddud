package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class CCTV {
	int num;
	int x;
	int y;
	public CCTV(int num, int x, int y) {
		super();
		this.num = num;
		this.x = x;
		this.y = y;
	}
}

public class P15683 {
	// https://www.acmicpc.net/problem/15683, 감시
	private static int N, M;
	private static int[][] map;
	private static int[][] copy;
	private static int[] result;
	private static ArrayList<CCTV> cctv;
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	private static int A = Integer.MAX_VALUE;
	
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]!=0 && map[i][j]!=6) {
					cctv.add(new CCTV(map[i][j], i, j));
				}
			} 
		}
		
		int i = cctv.size();
		result = new int[i];
		dfs(0, i);
		
		System.out.print(A);

	}
	
	private static void dfs(int depth, int r) {
		if(depth == r) {
			copy = new int[N][M];
			// map 을 copy 함.
			for(int i=0; i<map.length; i++) {
				System.arraycopy(map[i], 0, copy[i], 0, map[i].length);
			}
			
			for(int i=0; i<cctv.size(); i++) {
				direction(cctv.get(i), result[i]);
			}
			
			getBlindSpot();
			return;
		}
		
		for(int i=0; i<4; i++) {
			result[depth] = i;
			dfs(depth+1, r);
		}
		
	}
	private static void getBlindSpot() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copy[i][j] == 0) {
					cnt++;
				}
			}
		}
		A = Math.min(A, cnt);
	}
	
	private static void direction(CCTV tv, int d) {
		int cctvNum = tv.num;
		
		if(cctvNum == 1) {
			if(d == 0) {
				watch(tv, 0);
			} else if (d == 1) {
				watch(tv, 1);
			} else if (d == 2) {
				watch(tv, 2);
			} else {
				watch(tv, 3);
			}
			
		} else if (cctvNum == 2) {
			if(d == 0 || d == 2) {
				watch(tv, 0);
				watch(tv, 2);
			} else {
				watch(tv, 1);
				watch(tv, 3);
			}
			
		} else if (cctvNum == 3) {
			if(d == 0) {
				watch(tv, 0);
				watch(tv, 1);
			} else if (d == 1) {
				watch(tv, 1);
				watch(tv, 2);
			} else if (d == 2) {
				watch(tv, 2);
				watch(tv, 3);
			} else {
				watch(tv, 0);
				watch(tv, 3);
			}
			
		} else if (cctvNum == 4) {
			if(d == 0) {
				watch(tv, 0);
				watch(tv, 1);
				watch(tv, 3);
			} else if (d == 1) {
				watch(tv, 0);
				watch(tv, 1);
				watch(tv, 2);
			} else if (d == 2) {
				watch(tv, 1);
				watch(tv, 2);
				watch(tv, 3);
			} else {
				watch(tv, 0);
				watch(tv, 2);
				watch(tv, 3);
			}
			
		} else {
			watch(tv, 0);
			watch(tv, 1);
			watch(tv, 2);
			watch(tv, 3);
		}
	}

	private static void watch(CCTV tv, int d) {
		Queue<CCTV> que = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		que.add(tv);
		visited[tv.x][tv.y] = true;
		
		while(!que.isEmpty()) {
			int nx = que.peek().x + dx[d];
			int ny = que.poll().y + dy[d];
			
			// 범위를 벗어나거나 벽이면 끝
			if(nx<0 || nx>=N || ny<0 || ny>=M || copy[nx][ny]==6) {
				break;
			}
			
			if(copy[nx][ny] == 0) {
				// 감시 가능
				copy[nx][ny] = -1;
				que.add(new CCTV(tv.num, nx, ny));
				// 그냥 지나가는 경우
			} else {
				que.add(new CCTV(tv.num, nx, ny));
			}
			
		}
		
	}

}
