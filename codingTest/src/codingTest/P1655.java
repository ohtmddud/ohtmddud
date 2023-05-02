package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1655 {
	// https://www.acmicpc.net/problem/1655, 가운데를 말해요
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> QA = new PriorityQueue<>((a, b) -> b - a);
		PriorityQueue<Integer> QB = new PriorityQueue<>();

		// 숫자를 배열에 담아서 정렬 후, 가운데 숫자를 출력하면, 시간 초과
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(br.readLine());
			if (QA.size() == QB.size()) {
				QA.add(a);
			} else {
				QB.add(a);
			}

			if (!QA.isEmpty() && !QB.isEmpty()) {
				if (QA.peek() > QB.peek()) {

					int tmp = QA.poll();
					QA.offer(QB.poll());
					QB.offer(tmp);
				}
			}

			System.out.println(QA.peek());

		}

	}

}
