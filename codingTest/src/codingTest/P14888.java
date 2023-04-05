package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14888 {
	// https://www.acmicpc.net/problem/14888, 연산자 끼워넣기
	// 백트래킹 : 해를 찾는 도중 해가 아니어서 막히면, 되돌아가서 다시 해를 찾아가는 기법을 말합니다. 최적화 문제와 결정 문제를 푸는 방법이 됩니다.
	private static int MAX = Integer.MIN_VALUE;
	private static int MIN = Integer.MAX_VALUE;
	private static int[] oper = new int[4];
	private static int[] num;
	private static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		num = new int[N];
				
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
 		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(num[0], 1);
		System.out.println(MAX);
		System.out.println(MIN);
	}

	private static void dfs(int num, int idx) {
		if(idx == N) {
			MAX = Math.max(MAX, num);
			MIN = Math.min(MIN, num);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(oper[i] > 0) {
				oper[i]--;
				
				switch (i) {
				case 0:
					dfs(num + P14888.num[idx], idx + 1);
					break;
				case 1:
					dfs(num - P14888.num[idx], idx + 1);
					break;
				case 2:
					dfs(num * P14888.num[idx], idx + 1);
					break;
				default:
					dfs(num / P14888.num[idx], idx + 1);
					break;
				}
				oper[i]++;
			}
			
		}
		
	}

}
