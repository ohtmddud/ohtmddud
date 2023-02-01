package algorithm;

import java.util.Stack;

public class Depth_First_Search {
	static int[][] graph = {{}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2}};
	static boolean[] visited = new boolean[9];
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		
		stack.push(1);
		visited[1] = true;
		
		while(!stack.isEmpty()) {
			int nodeIdx = stack.pop();
			System.out.print(nodeIdx + " -> ");
			for(int linkedNode : graph[nodeIdx]) {
				if(!visited[linkedNode]) {
					stack.push(linkedNode);
					visited[linkedNode] = true;
				}
			}
		}
		System.out.println(visited[2]);
		dfs_recursion(1);
		

	}
	
	public static void dfs_recursion(int nodeIdx) {
		visited[nodeIdx] = false;
		System.out.print(nodeIdx + " -> ");
		for(int node : graph[nodeIdx]) {
			if(visited[node]) {
				dfs_recursion(node);
			}
		}
	}

}

//(1) 그래프의 모든 정점을 방문하는 것이 주요한 문제: DFS, BFS 모두 무방하다.
//(2) 경로의 특징을 저장해둬야 하는 문제: 각 장점에 숫자가 있고 a 부터 b까지 가는 경로를 구하는데 경로에 같은 숫자가 있으면 안된다는 문제 등, 각각의 경로마다 특징을 저장해둬야 하는 경우는 DFS를 사용해야 한다. BFS는 경로의 특징을 저장하지 못한다.
//(3) 최단거리를 구하는 문제: BFS가 유리하다. DFS의 경우 처음으로 발견되는 해답이 최단거리가 아닐 수 있지만 BFS의 경우 먼저 찾아지는 해답이 곧 최단거리이기 때문이다.