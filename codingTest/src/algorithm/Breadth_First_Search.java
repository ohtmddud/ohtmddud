package algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class Breadth_First_Search {

	public static void main(String[] args) {
//		그래프를 2차원 배열로 표현해줍니다.
//		배열의 인덱스를 노드와 매칭시켜서 사용하기 위해 인덱스 0은 아무것도 저장하지 않습니다.
//		1번 인덱스는 1번 노드를 뜻하고 노드의 배열의 값은 연결된 노드들입니다.
		int[][] graph = {{}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2}};
		
//		방문처리를 위한 boolean 배열
		boolean[] visited = new boolean[9];
		
		System.out.println(bfs(1, graph, visited));
	}
	
	static String bfs(int start, int[][] graph, boolean[] visited) {
//		BFS 에 사용할 큐를 생성
		Queue<Integer> que = new LinkedList<Integer>();
		
//		탐색 순서를 출력
		StringBuilder sb = new StringBuilder();
		
//		큐에 시작 노드 번호 입력
		que.offer(start);
		
//		시작 노드 방문 처리
		visited[start] = true;
		
//		큐가 빌 때까지 반복
		while(!que.isEmpty()) {
			int nodeIdx = que.poll();
			sb.append(nodeIdx + " → ");
			
//			큐에서 꺼낸 노드와 연결 된 노드들을 체크
			for(int i: graph[nodeIdx]) {
//				방문하지 않았으면 방문 처리 후 큐에 넣기
				if(!visited[i]) {
					visited[i] = true;
					que.offer(i);
				}
			}
		}
//		탐색 순서 리턴		
		return sb.toString();
	}

}
