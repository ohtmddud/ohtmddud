package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://namu.wiki/w/%EB%B2%A8%EB%A8%BC-%ED%8F%AC%EB%93%9C%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
// https://www.acmicpc.net/problem/11657
	
// 의사 코드
//	BellmanFord(G,w,s):
//		//초기화 과정
//		for each u in G.V:     //노드를 초기화 하기
//		      distance[v] = inf      //모든 노드의 최단거리를 무한으로 지정
//		      parent[v] = null       //모든 노드의 부모 노드를 널값으로 지정
//		distance[s] = 0 //출발점의 최단거리는 0으로 지정한다
//		//거리측정 과정
//		for i from 1 to len(G.V):   //노드의 숫자만큼
//		     for each (u,v) in G.E:   //모든 변을 체크해 최단 거리를 찾아본다.
//		          if distance[u] + w[(u,v)] < distance[v]:   
//		          //만약 u를 경유하여 v로 가는 거리가 현재 v의 최단 거리보다 짧으면
//		               distance[v] = distance[u] + w[(u,v)]  //그 거리를 v의 최단거리로 지정
//		               parent[v] = u   //u를 v의 부모 노드로 지정
//		//음수 사이클 체크 과정
//		for each (u,v) in G.E:
//		     if distance[u] + w[(u,v)] < distance[v]:
//		          return false //음수 사이클을 확인하고 알고리즘을 정지
//		return distance[], parent[]

	class Edge {
		int v; // 나가는 정점
		int w; // 들어오는 정점
		int cost;

		public Edge(int v, int w, int cost) {
			this.v = v;
			this.w = w;
			this.cost = cost;
		}
	}

	public class BellmanFord {
		static ArrayList<Edge> graph;
		static final int INF = 1000000000;
		
		//정점의 개수, 간선의 개수, 출발지
		public static boolean Bellman(int n, int m, int start) {
			int[] dist = new int[n + 1];
			Arrays.fill(dist, INF);
			dist[start] = 0;

			//정점의 개수만큼 반복
			for (int i = 0; i < n; i++) {
				//간선의 개수만큼 반복
				for (int j = 0; j < m; j++) {
					Edge edge = graph.get(j); //현재 간선
					
					//현재 간선의 들어오는 정점에 대해 비교
					if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
						dist[edge.w] = dist[edge.v] + edge.cost;
					}
				}
			}
			
			//음수 가중치 확인
			for (int i = 0; i < m; i++) {
				Edge edge = graph.get(i); //현재 간선
				
				//현재 간선의 들어오는 정점에 대해 비교 -> 더 작은 값 생기면 음수 사이클 존재
				if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
					System.out.println("음수 사이클 존재");
					return false;
				}
			}
			
			//출력
			for (int i = 1; i < dist.length; i++) {
				if (dist[i] == INF)
					System.out.print("INF ");
				else
					System.out.print(dist[i] + " ");
			}
			
			return true;
		}

		public static void main(String[] args) throws IOException {
	    
	    //그래프 입력받기
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			// 정점의 개수, 간선의 개수 
			int n = Integer.parseInt(bf.readLine());
			int m = Integer.parseInt(bf.readLine());

			graph = new ArrayList<>();

			StringTokenizer st;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(bf.readLine());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				graph.add(new Edge(v, w, cost));
			}
			
	        //벨만-포드 알고리즘 수행
			Bellman(n, m, 4);
		}
	}
	
