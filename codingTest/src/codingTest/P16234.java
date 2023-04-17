package codingTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//풀이
//구현 능력에 더불어 탐색 알고리즘을 활용할 수 있는지 묻는 문제이다.
//구현 문제들은 문제가 길고 복잡한 경우가 많아 세분화 하여 구현해 주기 위해 먼저 어떤 순서대로 구현할지 작업을 분류하고 시작하는 것이 좋다. 나는 아래와 같은 순서로 구현해 주었다.

//1. 순회를 하며 방문하지 않은 노드를 방문한다. 이 과정은 모든 노드를 방문할 때까지 반복된다.
//2. 노드를 방문할 때에는 BFS/DFS 탐색 알고리즘을 사용하여 구현한다. 이때 다음으로 이동 할 노드는 현재 노드의 값과의 차이가 L이상 R이하여야 한다.
//3. 방문한 노드들을 차례대로 list에 넣어주고 노드 값의 합을 따로 저장해 둔다.
//4. 모든 노드의 방문이 끝났다면 list에 넣어준 노드들의 인구이동을 시작한다. 이때 list의 크기가 1보다 커야 이동을 시작한다.
//5. 이동 시에는 문제의 조건에 맞게 노드 값의 합을 노드의 사이즈로 나눈 값을 모든 노드에 변경시켜준다.
//6. 1 ~ 6과정 동안 인구 이동이 일어난 적이 없다면 더 이상 이동할 수 있는 인구가 없으므로 순회를 멈추고 이때의 result값을 반환한다.

//이 문제의 BFS탐색을 구현할 때 실수했던 부분이 있었다. 방문체크를 하는 부분이었는데, 문제 예제에도 나와있듯이 현재 노드에서 상 하 좌 우 한 방향이라도 인구가 이동될 수 있다면 이동할 수 있는 노드가 된다. 그런데 처음에는 한번 확인한 노드는 모두 visited를 false로 바꿔버려 다른 방향에서 인구가 이동될 수 있어도 이동하지 못하였다. 이러한 실수를 하지 않도록 유의하자.
//위와 같은 순서로 차례차례 구현을 하다 보면 그리 어렵지 않은 탐색 문제라는 것을 알게 될 것이다. 또 이런 문제는 코드가 길어지고 복잡해질 가능성이 있으므로 주석을 활용하여 코드 설명을 적는 습관을 기르면 좋을것같다.

public class P16234 {
	// https://www.acmicpc.net/problem/16234, 인구 이동
	static int n, l, r;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static ArrayList<Node> list; // 인구 이동이 필요한 노드 리스트

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		n = scan.nextInt();
		l = scan.nextInt();
		r = scan.nextInt();

		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = scan.nextInt();
			}
		}

		System.out.println(move());
	}

	public static int move() { // 더 이상 인구이동이 일어나지 않을 때까지 반복
		int result = 0;
		while (true) {
			boolean isMove = false;
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						int sum = bfs(i, j); // bfs탐색으로 열릴 수 있는 국경선 확인 하며 인구 이동할 총 인구수 반환
						if (list.size() > 1) {
							changePopulation(sum); // 열린 국경선 내의 노드들 인구 변경
							isMove = true;
						}
					}
				}
			}
			if (!isMove)
				return result;
			;
			result++;
		}
	}

	public static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		list = new ArrayList<>();

		q.offer(new Node(x, y));
		list.add(new Node(x, y));
		visited[x][y] = true;

		int sum = board[x][y];
		while (!q.isEmpty()) {
			Node current = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
					int diff = Math.abs(board[current.x][current.y] - board[nx][ny]);
					if (l <= diff && diff <= r) {
						q.offer(new Node(nx, ny));
						list.add(new Node(nx, ny));
						sum += board[nx][ny];
						visited[nx][ny] = true;
					}
				}
			}
		}
		return sum;
	}

	public static void changePopulation(int sum) {
		int avg = sum / list.size();
		for (Node n : list) {
			board[n.x][n.y] = avg;
		}
	}

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
