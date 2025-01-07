import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 익명 클래스 사용
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		// 덩어리의 개수
		int N = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 100,000
		// 필요한 고기의 양
		int M = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 2,147,483,647
		
		// 무게의 총 합과 가격의 총 합은 각각 2,147,483,647을 넘지 않는다.
		int totalWeight = 0;
		// N개의 줄에는 각 고기 덩어리의 무게와 가격
		int[][] Meat = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Meat[i][0] = Integer.parseInt(st.nextToken()); // 무게
			Meat[i][1] = Integer.parseInt(st.nextToken()); // 가격
			
			totalWeight += Meat[i][0];
		}

		// 불가능한 경우에는 -1을 출력
		if (totalWeight < M) {
			bw.write("-1\n");
			bw.close();
			br.close();
			return;
		}

		Arrays.sort(Meat, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 가격 오름차순 정렬
				// 같은 가격에는, 무게 내림차순 정렬
//				if(Integer.compare(o1[1], o2[1]) == 0) {
//					// x-y를 안 쓰는 이유:
//					// 값이 너무 크거나 작을 때 오버플로우가 발생할 수 있습니다. 
//					return Integer.compare(o2[0], o1[0]); // 내림차순
//				}
//				return Integer.compare(o1[1], o2[1]); // 오름차순
				
				// 오버플로우가 발생할 일 없는 범위라
				if (o1[1] == o2[1]) {
					return o2[0] - o1[0]; // 내림차순
				}
				return o1[1] - o2[1]; // 오름차순
			}
		});

		int totalPrice = 0;
		totalWeight = 0;
		
		// 배열의 특정 인덱스를 반복적으로 참조하는 경우, 
		// 로컬 변수를 사용하는 것이 더 효율적입니다.
		int beforePrice = 0; 
		int ansPrice = Integer.MAX_VALUE;

		// 낮은 가격부터,
		// 가격이 같다면 무거운 무게부터
		for (int i = 0; i < N; i++) {
			totalWeight += Meat[i][0];

			// 덤 계산
			if(i != 0 && beforePrice < Meat[i][1]) {
//			if (i != 0 && Meat[i - 1][1] < Meat[i][1]) {
				totalPrice = Meat[i][1];
			} else {
				totalPrice += Meat[i][1];
			}

			beforePrice = Meat[i][1];

			if (totalWeight >= M) {
				if (ansPrice > totalPrice) {
					ansPrice = totalPrice;
				}
			}
		}

		bw.write(ansPrice + "\n");
		bw.close();
		br.close();
	}// main
}// class
