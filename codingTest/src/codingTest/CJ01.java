package codingTest;

import java.util.Stack;

public class CJ01 {

	public static void main(String[] args) {
		int a = solution("12156");
		System.out.println(a);

	}

	// 1234567890
	private static int solution(String number) {
		int[] keyboard = new int[10];
		for (int i = 0; i < 10; i++) {
			keyboard[i] = i + 1;
			if (i == 9) {
				keyboard[9] = 0;
			}
		}
		int answer = 0;

		String[] num = number.split("");
		Stack<Integer> A = new Stack<>();

		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < keyboard.length; j++) {
				if (Integer.parseInt(num[i]) == keyboard[j]) {
					if(keyboard[j] == A.peek()) {
						
					}
					A.add(keyboard[j]);
					A.add(keyboard[j + 1]);
					answer++;
					System.out.println("i: "+ i + ", peek: " + A.peek());
					if (i == num.length - 1 && Integer.parseInt(num[i]) != A.peek()) {
						answer++;
					} else {
						if (Integer.parseInt(num[i + 1]) == A.peek()) {
							i++;
						} else if (Integer.parseInt(num[i + 1]) != keyboard[j + 1]) {
							A.pop();
							answer++;
						}

					}

				}
			}
		}

		return answer;
	}

}
