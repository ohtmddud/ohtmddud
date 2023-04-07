package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P15685 {
	// htps://www.acmicpc.net/problem/15685, 드래곤 커브
	private static int A = 0;
	private static boolean[][] M = new boolean[101][101];
	private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); 
			int y = Integer.parseInt(st.nextToken()); 
			int d = Integer.parseInt(st.nextToken()); 
			int g = Integer.parseInt(st.nextToken());
			
			dragon(x, y, d, g);
		}
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(M[i][j] && M[i + 1][j] && M[i][j + 1] && M[i + 1][j + 1]) {
					A++;
				}
			}
		}
		
		System.out.println(A);
		
	}

	private static void dragon(int x, int y, int d, int g) {
		List<Integer> d_List = new ArrayList<>();
		d_List.add(d);
		
		for(int i = 1; i <= g; i++) {
			for(int j = d_List.size() - 1; j >= 0; j--)	{
				d_List.add((d_List.get(j) + 1) % 4); 
			}
		}
		
		M[y][x] = true;
		for(int direction : d_List) {
			x += dx[direction];
			y += dy[direction];
			M[y][x] = true;
		}
	}

}

//1. 방향을 d_list에 추가한다.
//2. d_list에 있는 방향들을 마지막부터 뽑으면서 반시계 방향으로 한번 돌린 것을 다시 d_list에 추가한다.
//3. d_list에 있는 방향들을 따라서 map[y][x]를 true로 설정한다.
//4. map에서 네 점이 true 이면 카운트한다.
