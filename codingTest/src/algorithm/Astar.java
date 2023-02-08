package algorithm;
//원리
//F = G + H
//G - 시작점 A로부터 현재 사각형까지의 경로를 따라 이동하는데 소요되는 비용입니다.
//H - 현재 사각형에서 목적지 B까지의 예상 이동 비용입니다. 사이에 벽, 물 등으로 인해 실제 거리는 알지 못합니다. 그들을 무시하고 예상 거리를 산출합니다. 여러 방법이 있지만, 이 포스팅에서는 대각선 이동을 생각하지 않고, 가로 또는 세로로 이동하는 비용만 계산합니다.
//F - 현재까지 이동하는데 걸린 비용과 예상 비용을 합친 총 비용입니다.

//의사코드
//pq.enqueue(start_node, g(start_node) + h(start_node))       // 우선순위 큐에 시작 노드를 삽입한다.
//while pq is not empty       // 우선순위 큐가 비어있지 않은 동안
//    node = pq.dequeue       // 우선순위 큐에서 pop한다.
//    if node == goal_node    // 만약 해당 노드가 목표 노드이면 반복문을 빠져나온다.
//        break
//    for next_node in (next_node_begin...next_node_end)       // 해당 노드에서 이동할 수 있는 다음 노드들을 보는 동안
//        pq.enqueue(next_node, g(node) + cost + h(next_node)) // 우선순위 큐에 다음 노드를 삽입한다.
//return goal_node_dist       // 시작 노드에서 목표 노드까지의 거리를 출력한다.

public class Astar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
