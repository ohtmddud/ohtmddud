package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15684 {
	// https://www.acmicpc.net/problem/15684, 사다리 조작
	private static int N, M, H; 
	private static int answer = -1;
	private static int[][] ladder;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ladder = new int[H+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			ladder[x][y] = 1;
			ladder[x][y+1] = -1;
		}
		
		for(int i=0; i<4; i++) {
			dfs(0, i);
		}
		
		System.out.println(answer);
			
	}

	private static void dfs(int cnt, int dest) {
		if(cnt == dest) {
			if(play()) {
				System.out.println(dest);
				System.exit(0);
			}
			return;
		}
		for(int i=1; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if(ladder[i][j]!=0 || ladder[i][j+1]>0) {
					continue;
				}
				ladder[i][j] = 1;
				ladder[i][j+1] = -1;
				dfs(cnt+1, dest);
				ladder[i][j] = 0;
				ladder[i][j+1] = 0;
			}
		}
	}

	private static boolean play() {
		boolean res = true;
		for(int i=1; i<=N; i++) {
			int cx = 1;
			int cy = i;
			
			while(cx < H+1) {
				if(ladder[cx][cy] > 0) {
					cy++;
				} else if(ladder[cx][cy] < 0) {
					cy--;
				}
				cx++;
			}
			if(cy != i) {
				res = false;
				break;
			}
		}
		return res;
	}

}

//사다리 게임입니다.
//i번에서 출발하여 i번으로 다시 돌아오게하는 사다리게임을 완성하려고 할 때, 추가적으로 배치해야하는 사다리 갯수의 최솟값을 구하는 문제입니다.
//먼저 연결된 사다리는 배열의 좌표에서 우측으로 연결된 선일경우 1, 좌측일경우 -1로 정의했습니다. 쉽게말해 배열의 각 인덱스에서 -1을 만나면 왼쪽으로 이동 ,1을만나면 오른쪽 이동합니다. 이후는 -1,0,1 공통으로 아래로 하강합니다.
//문제의 조건에 따라 3개를 초과하거나 방법이 없는경우 -1을 출력해야하기 때문에, dfs를 호출할때 새로 생성할 사다리의 갯수를 최대 3개까지로 하여 반복문을 구성했으며, 3개 이전에 사다리를 완성하면 값을 출력한 후 프로그램을 종료하도록 설계했습니다.
//사다리를 3개 추가함에도 결과를 얻어내지 못한다면 answer은 초기값인 -1로 유지되므로 결과는 -1이 출력됩니다.
//모든 경우의 수를 고려해야하는 문제이기 때문에 dfs 재귀함수를 통해 경우의 수를 조합했고, dest == cnt 일때, play() 함수를 통해 현재 상태에서 완료조건을 충족하는지 여부를 확인해주면 됩니다.
