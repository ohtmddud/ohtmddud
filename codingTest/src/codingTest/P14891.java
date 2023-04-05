package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14891 {
	// https://www.acmicpc.net/problem/14891, 톱니바퀴
	private static int[][] Gear;
	private static int K, Cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Gear = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String sr = br.readLine();
			for (int j = 0; j < 8; j++) {
				String[] str = sr.split("");
				Gear[i][j] = Integer.parseInt(str[j]);
			}
		}
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			Solve(idx, dir);
		}
		Cnt = 0;
		for (int i = 0; i < 4; i++) {
			Cnt += Math.pow(2, i) * Gear[i][0];
		}
		System.out.println(Cnt);
	}

	private static void Solve(int idx, int dir) {
		left(idx-1, -dir);
		right(idx+1, -dir);
		rotation(idx, dir);
	}

	private static void rotation(int idx, int dir) {
		if (dir == 1) {
			int tmp = Gear[idx][7];
			for (int i=7; i>0; i--) {
				Gear[idx][i] = Gear[idx][i-1];
			}
			Gear[idx][0] = tmp;
		} else {
			int tmp = Gear[idx][0];
			for (int i=0; i<7; i++) {
				Gear[idx][i] = Gear[idx][i+1];
			}
			Gear[idx][7] = tmp;
		}
	}

	private static void left(int i, int j) {
		if (i < 0) {
			return;
		}
		if (Gear[i][2] == Gear[i+1][6]) {
			return;
		}
		left(i-1, -j);
		rotation(i, j);
	}

	private static void right(int i, int j) {
		if (i > 3) {
			return;
		}
		if (Gear[i][6] == Gear[i-1][2]) {
			return;
		}
		right(i+1, -j);
		rotation(i, j);
	}

}
