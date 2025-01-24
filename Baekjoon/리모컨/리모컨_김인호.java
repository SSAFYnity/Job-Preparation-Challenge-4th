import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int findValue = Integer.parseInt(br.readLine());
        int breakButtonCount = Integer.parseInt(br.readLine());
        boolean[] broken = new boolean[10];
        if (breakButtonCount > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < breakButtonCount; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }
        int minPress = Math.abs(findValue - 100);

        //0부터 999999까지 모든 숫자 검사
        for (int i = 0; i <= 999999; i++) {
            String str = String.valueOf(i);
            if (isValid(str, broken)) {
                int pressCount = str.length() + Math.abs(i - findValue);
                minPress = Math.min(minPress, pressCount);
            }
        }
        System.out.print(minPress);
    }
    private static boolean isValid(String str, boolean[] broken) {
        for (char ch : str.toCharArray()) {
            if (broken[ch - '0']) {
                return false;
            }
        }
        return true;
    }
}
