package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class point {
	int r;
	int c;
	
	public point(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class P15686 {
	// https://www.acmicpc.net/problem/15686, 치킨 배달
	// 처음에는 BFS로 최단거리를 구하고, 재귀함수를 이용하여 치킨집을 부쉈다가 다시 지었다가하는 로직을 세웠다가 시간 초과로 실패하였습니다. 알고보니, 복잡하게 할 필요없이 DFS와 백트래킹 알고리즘을 활용하면 쉽게 풀리는 문제였습니다.
	private static int N, M;
	private static int[][] city;
	private static int ans = Integer.MAX_VALUE;
	private static ArrayList<point> home;
	private static ArrayList<point> chic;
	private static boolean[] open;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		city = new int[N+1][N+1];
		home = new ArrayList<>();
		chic = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				
				if(city[i][j] == 1) {
					home.add(new point(i, j));
				}else if(city[i][j] == 2) {
					chic.add(new point(i, j));
				}
			}
		}
		
		open = new boolean[chic.size()];
		
		solution(0, 0);
		System.out.println(ans);
		br.close();
	}

	private static void solution(int start, int cnt) {
		if(cnt == M) {
			int res = 0;
			
			for(int i = 0; i < home.size(); i++) {
				int tmp = Integer.MAX_VALUE;
				
				// 임의의 집과 치킨집 중 open 한 치킨집의 모든 거리를 비교한다.
				for(int j = 0; j < chic.size(); j++) {
					if (open[j]) {
						int dis = Math.abs(home.get(i).r - chic.get(j).r) + Math.abs(home.get(i).c - chic.get(j).c);
						// 그 중 최소 거리를 구한다.
						tmp = Math.min(tmp, dis);
					}
				}
				res += tmp;
			}
			ans = Math.min(ans, res);
			return;
		}
		
		// 백트래킹
		for(int i = start; i < chic.size(); i++) {
			open[i] = true;
			solution(i + 1, cnt + 1);
			open[i] = false;
		}
		
		
	}

}
