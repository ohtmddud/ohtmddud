package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
	static int V, E, start;
	static ArrayList<ArrayList<Node>> graph;
	
	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		graph = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i < V + 1; i++) {
			graph.add(new ArrayList<Node>());
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Node(e, c));
		}
		
		// 다익스트라 알고리즘 초기화
		int[] dist = new int[V + 1];
		for(int i = 0; i < V + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		q.offer(new Node(start, 0));
		
		dist[start] = 0;
		while(!q.isEmpty()) {
			Node curNode = q.poll();
			if (dist[curNode.idx] < curNode.cost) {
				continue;
			}
			
			for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
				Node nxtNode = graph.get(curNode.idx).get(i);
				if(dist[nxtNode.idx] > curNode.cost + nxtNode.cost) {
					dist[nxtNode.idx] = curNode.cost + nxtNode.cost;
					q.offer(new Node(nxtNode.idx, dist[nxtNode.idx]));
				}
			}
			
		}
		System.out.println(Arrays.toString(dist));
	}

}
/*
sample input
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6
 */
