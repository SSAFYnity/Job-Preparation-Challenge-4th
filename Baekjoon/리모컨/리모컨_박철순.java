import java.util.*;
import java.io.*;

public class Main_1107 {
    static int N;
    static int M;
    static boolean[] button;
    static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
        StringTokenizer st;

        button = new boolean[10]; // 1~9
        Arrays.fill(button, true);
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());

        if (M != 0) {
            st = new StringTokenizer(in.readLine());
            for (int i=0;i<M;i++){
                int brokenNum = Integer.parseInt(st.nextToken());
                button[brokenNum] = false;
            }
        }

        result = Math.abs(100 - N); // +,- 로만 이동

        for (int i=0;i<=999999;i++) {
            String num = String.valueOf(i);
            boolean isBroken = false;
            for (int j=0;j<num.length(); j++) {
                if (!button[num.charAt(j) - '0']) { // 고장난 버튼
                    isBroken = true;
                    break;
                }
            }
            if (!isBroken) {
                // 고장난 버튼을 누르지 않고, 근사치에서 +, -로 이동할 경우
                result = Math.min(result, Math.abs(N - i) + num.length());
            }
        }

        System.out.println(result);
    }

}
