package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Ball{
	int x;
	int y;
	int cnt;

	public Ball(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class P13460 {
	static int N,M;
	static char[][]	map;
	static Ball red, blue;
	static boolean[][][][] visited;
	static int result = -1;
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static void bfs(Ball red, Ball blue) {
		Queue<Ball> redQ = new LinkedList<>();
		Queue<Ball> blueQ = new LinkedList<>();
		
		redQ.offer(red);
		blueQ.offer(blue);
		
		//구슬 위치 방문처리
		visited[red.x][red.y][blue.x][blue.y] = true;
		
		while (!redQ.isEmpty() && !blueQ.isEmpty()) {
			Ball nred = redQ.poll();
			Ball nblue = blueQ.poll();
			
			// 10회 이상
			if(nred.cnt > 10) {
				result = -1;
				return;
			}
			
			// 파란색 나감.
			if(map[nblue.x][nblue.y] == 'O') {
				continue;
			}
			
			// 빨간색 나감. 파란색 못 나감
			if(map[nred.x][nred.y] == 'O') {
				result = nred.cnt;
				return;
			}
			
			// 상하좌우 기울이기
			for(int i = 0; i < 4; i++) {
				
				// 파란색
				int bx = nblue.x;
				int by = nblue.y;
				while(true) {
					bx += dx[i];
					System.out.println(dx[i]);
					by += dy[i];
					
					// 구멍
					if(map[bx][by] == 'O') {
						break;
					}
					// 벽
					else if(map[bx][by] == '#') {
						bx -= dx[i];
						by -= dy[i];
						break;
					}
				}
				
				// 빨간색
				int rx = nred.x;
				int ry = nred.y;
				while(true) {
					rx += dx[i];
					ry += dy[i];
					// 구멍
					if(map[rx][ry] == 'O') {
						break;
					}
					// 벽
					else if(map[rx][ry] == '#') {
						rx -= dx[i];
						ry -= dy[i];
						break;
					}
				}
				
				// 두개 위치가 동일
				if(bx == rx && by == ry && map[rx][ry] != 'O') {
					// 이동한 거리가 던 긴 쪽
					int r_dis = Math.abs(nred.x - rx) + Math.abs(nred.y - ry);
					int b_dis = Math.abs(nblue.x - bx) + Math.abs(nblue.y - by);
					
					if(r_dis > b_dis) { // 빨간색
						rx -= dx[i];
						ry -= dy[i];
					} else { // 파란색
						bx -= dx[i];
						by -= dy[i];
					}
				}
				
				if(!visited[rx][ry][bx][by]) {
					// 방문
					visited[rx][ry][bx][by] = true;
					// 큐에 넣기
					redQ.offer(new Ball(rx, ry, nred.cnt + 1));
					blueQ.offer(new Ball(bx, by, nblue.cnt + 1));
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean [N][M][N][M];
		
		for(int i = 0; i < N; i++) {
			String row = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = row.charAt(j);
				if(map[i][j] == 'R') {
					red = new Ball(i, j, 0);
				}
				if(map[i][j] == 'B') {
					blue = new Ball(i, j, 0);
				}
			}
		}
		bfs(red, blue);
		System.out.println(result);
	}
}

//스타트링크에서 판매하는 어린이용 장난감 중에서 가장 인기가 많은 제품은 구슬 탈출이다. 
//구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
//
//보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다. 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다. 
//빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다. 게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다. 
//이때, 파란 구슬이 구멍에 들어가면 안 된다.
//
//이때, 구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다. 
//왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.
//
//각각의 동작에서 공은 동시에 움직인다. 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 
//빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다. 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 
//또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다. 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
//
//보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.
//
//첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다. 다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다. 
//이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다. '.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 
//'O'는 구멍의 위치를 의미한다. 'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치이다.
//
//입력되는 모든 보드의 가장자리에는 모두 '#'이 있다. 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.

//1. BFS(너비 우선 탐색)
//2. DFS(깊이 우선 탐색)

