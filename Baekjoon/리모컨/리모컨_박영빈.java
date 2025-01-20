import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int targetNum, M, count1, count2;
	static int[] brokenButtons;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		targetNum = Integer.parseInt(br.readLine());

		M = Integer.parseInt(br.readLine());
		brokenButtons = new int[10];
		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				brokenButtons[Integer.parseInt(st.nextToken())] = 1; // 고장난 버튼 체크
			}
		}

		if (targetNum == 100) {
			bw.write("0");
		} else if (targetNum > 97 && targetNum < 104) {
			// 99면 9, 9 버튼 2번 누르는 것보다 - 버튼 1번 누르는 게 최소 횟수
			bw.write(String.valueOf(Math.abs(targetNum - 100)));
		} else if (M == 0) {
			String str = Integer.toString(targetNum);
			bw.write(String.valueOf(str.length()));
		} else {
			count1 = Math.abs(targetNum - 100);
			count2 = Integer.MAX_VALUE;

			findNum: for (int n = 0; n <= 999999; n++) {
				String str = String.valueOf(n);
				for (int i = 0; i < str.length(); i++) {
					if (brokenButtons[str.charAt(i) - '0'] > 0) {
						continue findNum;
					}
				}

				int cnt = str.length() + Math.abs(n - targetNum);
				if (count2 >= cnt) {
					count2 = cnt;
				} else {
					break;
				}
			}
			bw.write(String.valueOf(Math.min(count1, count2)));
		}
		
		bw.close();
		br.close();
	}// main
}// class
