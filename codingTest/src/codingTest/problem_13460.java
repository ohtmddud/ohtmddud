package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem_13460 {
	static int N, M;
	static char[][] map;
	static boolean [][][][] visited;
	static int holeX, holeY;
	static Marble blue, red;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine()	;
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				
				if(map[i][j] == '0') {
					holeX = i;
					holeY = j;
				} else if(map[i][j] == 'B') {
					blue = new Marble(0, 0, i, j, 0);
				} else if(map[i][j] == 'R') {
					red = new Marble(i, j, 0, 0, 0);
				}
			}
		}
		System.out.println(bfs());
		br.close();
	}
	
	public static int bfs() {
		Queue<Marble> que = new LinkedList<>();
		que.add(new Marble(red.rx, red.ry, blue.bx, blue.by, 1));
		visited[red.rx][red.ry][blue.bx][blue.by] = true;
		
		while(!que.isEmpty()) {
			Marble marble = que.poll();
			
			int curRx = marble.rx;
			int curRy = marble.ry;
			int curBx = marble.bx;
			int curBy = marble.by;
			int curCnt = marble.cnt;
			
			if(curCnt > 10) {
				return -1;
			}
			
			for(int i = 0; i < 4; i++) {
				int newRx = curRx;
				int newRy = curRy;
				int newBx = curBx;
				int newBy = curBy;
				
				boolean isRedHole = false;
				boolean isBlueHole = false;
				
				// 빨간 구슬 이동 -> # 벽을 만날 때 까지 이동
				while(map[newRx + dx[i]][newRy + dy[i]] != '#') {
					newRx += dx[i];
					newRy += dy[i];
					
					// 이동 중 구멍을 만날 경우
					if(newRx == holeX && newRy == holeY) {
						isRedHole = true;
						break;
					}
				}
				
				// 파란 구슬 이동 -> # 벽을 만날 때 까지 이동
				while(map[newBx + dx[i]][newBy + dy[i]] != '#') {
					newBx += dx[i];
					newBy += dy[i];
					
					// 이동 중 구멍을 만날 경우
					if(newBx == holeX && newBy == holeY) {
						isBlueHole = true;
						break;
					}
				}
				
				if(isBlueHole) { // 파란 구슬이 구멍에 빠지면 무조건 실패
					continue; // 하지만 큐에 남은 다른 좌표도 봐야하니 다음으로
				}
				
				if(isRedHole && !isBlueHole) { // 빨간 구슬만 구멍에 빠지면 성공
					return curCnt;
				}
				
				// 둘 다 구멍에 빠지지 않았는데 이동할 위치가 같은 경우 -> 위치 조정
				if(newRx == newBx && newRy == newBy) {
					if(i == 0) { // 위쪽으로 기울이기, 더 큰 x 값을 가지는 구슬이 뒤로 감
						if(curRx > curBx) {
							newRx -= dx[i];
						} else {
							newBx -= dx[i];
						}
					} else if(i == 1) { // 오른쪽으로 기울이기, 더 작은 y값을 가지는 구슬이 뒤로 감
						if(curRy < curBy) {
							newRy -= dy[i];
						} else {
							newBy -= dy[i];
						}
					} else if(i == 2) { // 아래쪽으로 기울이기, 더 작은 x값을 가지는 구슬이 뒤로 감
						if(curRx < curBx) {
							newRx -= dx[i];
						} else {
							newBx -= dx[i];
						}
					} else { // 왼쪽으로 기울이기, 더 큰 y값을 가지는 구슬이 뒤로 감
						if(curRy > curBy) {
							newRy -= dy[i];
						} else {
							newBy -= dy[i];
						}
					}
				}
				
				// 두 구슬이 이동할 위치가 처음 방문하는 곳인 경우만 이동 -> 큐에 추가
				if(!visited[newRx][newRy][newBx][newBy]) {
					visited[newRx][newRy][newBx][newBy] = true;
					que.add(new Marble(newRx, newRy, newBx, newBy, curCnt + 1));
				}
			}
		}
		
		return -1;
	}
}

class Marble{
	int rx;
	int ry;
	int bx;
	int by;
	int cnt;
	
	public Marble(int rx, int ry, int bx, int by, int cnt) {
		super();
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.cnt = cnt;
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

