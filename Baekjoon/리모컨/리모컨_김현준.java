
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 리모컨 {
    static String N;
    static int max_channel = 1000000;
    static int M;
    static boolean[] buttons;
    static int answer;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        M = Integer.parseInt(br.readLine());
        buttons = new boolean[11];
        answer = Integer.MAX_VALUE;
        if(M != 0)
            st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            // 현재 나온 버튼들을 비활성화
            buttons[Integer.parseInt(st.nextToken())] = true;
        }

        int target = Integer.parseInt(N); // 목표 채널
        int now = 100; // 현재 채널
        int nowMove = 0;
        if(now > target) nowMove = now - target;
        else nowMove = target - now;

        for(int i=0;i<=max_channel;i++) {
            // 버튼을 누를 수 있냐?
            int move = 0;
            if(canPush(i)) {
                // 누른 갯수는?
                move += findNumber(i);
                // 누를 수 있다면, 누른 버튼에서 목표치까지 가는데 걸리는 시간은?
                if(i > target) move += i - target;
                else move += target - i;
                answer = Math.min(answer, move);
            }
            // 기존 채널에서 움직인 것과 바뀐채널에서 움직일때 최소의 시간은?


        }
        answer = Math.min(answer, nowMove);

        System.out.println(answer);
    }

    public static int findNumber(int idx) {
        int cnt = 1;
        while(idx > 0) {
            if(idx / 10 == 0) break;
            idx /= 10;
            cnt++;
        }
        return cnt;
    }

    public static boolean canPush(int idx) {
        String s = String.valueOf(idx);
        char[] c = s.toCharArray();
        for(int i=0;i<c.length;i++) {
            int next = c[i] - '0';
            if(buttons[next])
                return false;
        }

        return true;
    }
}
