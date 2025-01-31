import java.util.*;

public class Main {

    static List<Integer> brokenBtn = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            brokenBtn.add(sc.nextInt());
        }
        int cnt = 0;

        int plusCnt = Math.abs(N - 100);
        while (true) {

            int x = N - cnt;
            int answer = (cnt + (Math.min(Math.abs(x - 100), Integer.toString(x).length())));
            if (answer >= plusCnt) {
                System.out.println(plusCnt);
                return;
            }
            if (0 <= x && canPush(x)) {
                System.out.println(answer);
                return;
            }

            x = N + cnt;
            answer = cnt + (Math.min(Math.abs(x - 100), Integer.toString(x).length()));
            if (answer >= plusCnt) {
                System.out.println(plusCnt);
                return;
            }
            if (canPush(x)) {
                System.out.println(answer);
                return;
            }

            cnt++;

        }

    }

    static boolean canPush(int n) {
        String channel = Integer.toString(n);
        for (int x : brokenBtn) {
            if (channel.contains(Integer.toString(x)))
                return false;
        }
        return true;
    }

}