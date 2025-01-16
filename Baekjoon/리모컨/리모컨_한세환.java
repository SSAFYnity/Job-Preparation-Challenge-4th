import java.io.*;
import java.util.*;

// bj #1107 리모컨
public class Main {
    static int n, ans;
    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ans = Integer.MAX_VALUE;
        int m = Integer.parseInt(br.readLine());

        if (m != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                int temp = Integer.parseInt(st.nextToken());
                broken[temp] = true;
            }
        }

        if (n == 100) { // 시작점이 0이므로
            System.out.println(0);
            return;
        }

        ans = Math.abs(n-100);

        for (int i = 0; i <= 999000; i++) {
            String str = String.valueOf(i);
            boolean check = true;

            for (int j = 0; j < str.length(); j++) {
                if (broken[str.charAt(j) - '0']) {
                    check = false;
                    break;
                }

            }
            if (check) {
                int cnt = Math.abs(i - n) + str.length();
                ans = Math.min(cnt, ans);
            }
        }

        System.out.println(ans);

    }

}