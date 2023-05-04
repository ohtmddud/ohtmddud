package codingTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3191 {
	// 백조의 호수, https://www.acmicpc.net/problem/3197

	// 백조가 탐색을 한다. (백조, BFS → 맵, 탐색여부)

	// 얼음이 녹아서 물이 된다. (얼음, 물)

	private static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	private static String[][] Map;
	private static boolean[][] Visited;
	private static int[][] Dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static Node[] Swan;
	private static Queue<Node> Que;
	private static Queue<Node> WaterQue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		Map = new String[R][C];
		Visited = new boolean[R][C];
		Swan = new Node[1];
		Que = new LinkedList<>();
		WaterQue = new LinkedList<>();
		
		int SwanIdx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				Map[i][j] = st.nextToken();
				if (Map[i][j] == "L") {
					Swan[SwanIdx++] = new Node(i, j);
				} else if(Map[i][j] != "X") {
					WaterQue.offer(new Node(i, j));
				}

			}
		}
		
		

	}

}
