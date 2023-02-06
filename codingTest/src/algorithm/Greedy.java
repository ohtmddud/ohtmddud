package algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class Greedy {
	public static void main(String[] args) {
		// 활동 정보를 List에 저장하고 정렬한다.
		ArrayList<Action> list = new ArrayList<>();
		list.add(new Action("A", 7, 8));
		list.add(new Action("B", 5, 7));
		list.add(new Action("C", 3, 6));
		list.add(new Action("D", 1, 2));
		list.add(new Action("E", 6, 9));
		list.add(new Action("F", 10, 11));
		Collections.sort(list);

		// Greedy 알고리즘 수행 후, 결과 출력
		ArrayList<Action> ans = greedy(list);
		for (Action act : ans) {
			System.out.print(act.name + ", ");
		}
	}

	// Greedy 알고리즘을 통해 선택된 활동들을 반환한다.
	private static ArrayList<Action> greedy(ArrayList<Action> list) {
		int time = 0;
		ArrayList<Action> ans = new ArrayList<>();

		for (Action act : list) {
			if (time <= act.startTime) {
				time = act.endTime;
				ans.add(act);
			}
		}
		return ans;
	}
}

// 활동 정보를 가진 Class로 Comparable을 구현하여 종료 시간 기준 오름차순으로 정렬함.
class Action implements Comparable<Action> {
	String name;
	int startTime;
	int endTime;

	public Action(String name, int startTime, int endTime) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Action a) {
		return this.endTime - a.endTime;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
