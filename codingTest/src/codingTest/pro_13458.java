package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 백준 13458 시험 감독관(exam) (https://www.acmicpc.net/problem/13458)
public class pro_13458 {
	//시험장 N, 응시자 A, 총감독관 B, 부감독관 C
	private static int N, B, C;
	private static int[] A;
	//필요한 감독관의 최솟값
	public static void main(String[] args) throws IOException{
		pro_13458 pro = new pro_13458();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		System.out.println(N);
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			System.out.println(A[i]);
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		System.out.println(B);
		System.out.println(C);
		
		System.out.println(pro.min()); 
	}
	public int min() {
		int min = 0;
		for(int i=0; i<N; i++) {
			int remain = B - A[i];
			if(remain>0) {
				int cnt = Math.round(remain/C);
			}
		}
		
		return min;
	}

}
