import java.util.*;
import java.io.*;

public class Main {
    static int n, k, len, inf = Integer.MAX_VALUE;

    static int canDo(int num, boolean[] arr) {
        int numlen = (num+"").length();
        for (int i = 0; i < numlen; i++) {
            if (!arr[(int) (num%Math.pow(10,numlen-i)/Math.pow(10,numlen-i-1))]) return i;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        len = (n+"").length();
        boolean[] fixed = new boolean[10];
        Arrays.fill(fixed, true);
        if (k != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                fixed[Integer.parseInt(st.nextToken())] = false;
            }
        }

        int maxi = Math.abs(n-100);
        if (canDo(n, fixed) == -1) maxi = Math.min(len, maxi);
        for (int i = n+1; i < n+maxi-len; i++) {
            int digit = canDo(i, fixed);
            if (digit != -1) {
                digit = (int) Math.pow(10, (i+"").length()-digit-1);
                i = (i / digit) * digit + digit - 1;
            } else {
                maxi = Math.min((i+"").length() + (i-n), maxi);
                break;
            }
        }
        for (int i = n-1; i > n-maxi+(i+"").length() && i >= 0; i--) {
            int digit = canDo(i, fixed);
            if (digit != -1) {
                digit = (int) Math.pow(10, (i+"").length()-digit-1);
                i = (i / digit) * digit;
            } else {
                maxi = Math.min((i+"").length() + (n-i), maxi);
                break;
            }
        }

        System.out.println(maxi);
    }
}

