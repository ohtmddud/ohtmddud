package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

//의사코드
//while(true){
//	  1. 시간재기
//	  2. 뱀 이동하기 
//	  3. 범위를 벗어나거나, 뱀 몸통 만날 때 종료
//	  4. 사과가 있을 때 없을 때 처리 
//	  5. 방향을 바꿔주는 시간을 만날 때 방향 변경 
//	  6. 현재값 업데이트 
//	}

public class P3190 {
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
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String y = st.nextToken();
			hm.put(x, y);
		}
		System.out.println(N);
		System.out.println(apple);
		System.out.println(L);
		
		solve();
	}
	
	public static boolean finish(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return true;
		}
		for(int i=0; i<snake.size(); i++) {
			int[] t = snake.get(i);
			if(x==t[0] && y==t[1]) {
				return true;
			}
		}
		return false;
	}
	
	public static void solve() {
		int x = 0, y = 0;
		int time = 0;
		int d = 0;
		snake.add(new int[] {0, 0});
		
		while(true) {
			// 시간
			time++; 
			System.out.println(x + ", " + y);
			// 이동
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// 범위 밖, 몸통 종료
			if(finish(nx, ny)) {
				System.out.println(nx + ", " + ny);
				break;
			}
			
			// 사과
			if(map[nx][ny] == 1) {
				map[nx][ny] = 0;
				snake.add(new int[] {nx, ny});
			} else {
				snake.add(new int[] {nx, ny});
				snake.remove(0);
			}
			
			// 방향 전환
			if(hm.containsKey(time)) {
				if(hm.get(time).equals("D")) {
					System.out.println(hm.get(time));
					d += 1;
					if(d==4) {
						d=0;
					}
				} else {
					d -= 1;
					if(d==-1) {
						d=3;
					}
				}
			}
			
			// 업데이트
			x = nx;
			y = ny;
			
		}
		System.out.println(time);
	}
}
