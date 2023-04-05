package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14889 {
	private static int N;
	private static int[][] S;
	private static boolean[] Visit;
	private static int Min = Integer.MAX_VALUE;
	

	public static void main(String[] args) throws IOException {
		// https://www.acmicpc.net/problem/14889, 스타트와 링크
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Visit = new boolean[N];
		S = new int[N][N];Visit = new boolean[N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st  = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combo(0, 0);
		System.out.println(Min);

	}
	
	// 인덱스와 조합 개수(=재귀 깊이)
	private static void combo(int Idx, int Cnt) {
		// 팀 조합이 완성 될 경우		
		if(Cnt == N / 2) {
			// 방문한 팀과 방문하지 않은 팀을 나누어, 각 팀의 점수를 구한 뒤 최솟값을 찾음.
			differ();
			return;
		}
		
		for(int i=Idx; i<N; i++) {
			// 방문하지 않았다면,
			if(!Visit[i]) {
				// 방문으로 변경
				Visit[i] = true;
				// 재귀 호출
				combo(i+1, Cnt+1);
				// 재귀가 끝난 뒤, 비방문으로 변경
				Visit[i] = false;
			}
		}
	}

	private static void differ() {
		int Team_Start = 0;
		int Team_Link = 0;
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				// i 와 j 가 true 이면 스타트팀에 플러스
				if(Visit[i]==true && Visit[j]==true) {
					Team_Start += S[i][j];
					Team_Start += S[j][i];
				}
				// i 와 j 가 false 이면 링크팀에 플러스
				else if(Visit[i]==false && Visit[j]==false) {
					Team_Link += S[i][j];
					Team_Link += S[j][i];
				}
			}
		}
		// 두 팀의 점수 차이 (절대값)
		int Val = Math.abs(Team_Start - Team_Link);
		
		// 두 팀의 점수차가 0 이면, 더 이상 탐색 할 필요가 없다.
		
		if(Val==0) {
			System.out.println(Val);
			System.exit(0);
		}
		
		Min = Math.min(Val, Min);
		
	}
	
	

}
