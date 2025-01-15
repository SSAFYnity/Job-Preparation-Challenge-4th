import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N; // 이동하려는 채널
	static int M; // 고장난 버튼의 개수 M
	static Set<Integer> breakDown;// 고장난 버튼

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		breakDown = new HashSet<>();
		if (M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				breakDown.add(Integer.parseInt(st.nextToken()));
			}
		}

		int result = Math.abs(N - 100);
		int channel = 100;

		for (int i = 0; i <= 999999; i++) { // 다 해본다
			String candidate = String.valueOf(i); // N의 가장 근처 숫자가 될 수 있는 후보들

			boolean isPossible = true;
			for (char c : candidate.toCharArray()) {
				if (breakDown.contains(c - '0')) { // 해당 숫자를 만들 수 없다면
					isPossible = false;
					break;
				}
			}

			if (isPossible) {
				int currentCnt = Math.abs(N - i) + candidate.length();
				result = Math.min(currentCnt, result);
			}

		}
		System.out.println(result);

	}

}