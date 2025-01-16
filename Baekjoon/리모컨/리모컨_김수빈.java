import java.util.*;
import java.io.*;

public class 리모컨_김수빈 {
    // 현재 채널 100
    // 원하는 채널로 이동하는데 가장 적은 이동 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 이동할 번호
        int M = Integer.parseInt(br.readLine());  // 고장난 번호 개수
        boolean[] brokenNum = new boolean[10];  // 고장난 번호
        if (M != 0) {
            String[] input = br.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                brokenNum[Integer.parseInt(input[i])] = true;
            }
        }
        int minCnt = Math.abs(N - 100);

        for (int i = 0; i <= 999900; i++) {
            String str = String.valueOf(i);
            boolean possible = true;
            for (int j = 0; j < str.length(); j++) {
                // 이동할 수 없다면 갈 수 없는 번호
                if (brokenNum[str.charAt(j) - '0']) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                int cnt = str.length() + Math.abs(N - i);
                minCnt = Math.min(cnt, minCnt);
            }
        }

    System.out.println(minCnt);

    }
}
