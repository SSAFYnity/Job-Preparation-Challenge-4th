import java.util.*;
import java.io.*;

public class Main {
    static int n, k, inf = Integer.MAX_VALUE;

    static boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= n;}

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        int[] heart = new int[2];
        int[] body = new int[5];
        boolean[] thr = new boolean[3];
        heart[0] = heart[1] = -1;

        outer:
        for (int i = 0; i < n; i++) {
            inner:
            for (int j = 0; j < n; j++) {
                if (arr[i].charAt(j) == '*') {
                    heart[0] = i+1;
                    heart[1] = j;
                    i++;
                    break inner;
                }
            }

            for (int j = 1; j < n && heart[0] != -1; j++) {
                if (!isOut(i, heart[1]-j) && arr[i].charAt(heart[1]-j) == '*') body[0]++;
                else thr[0] = true;
                if (!isOut(i, heart[1]+j) && arr[i].charAt(heart[1]+j) == '*') body[1]++;
                else thr[1] = true;
                if (!isOut(i+j, heart[1]) && arr[i+j].charAt(heart[1]) == '*') body[2]++;
                else thr[2] = true;
                if (thr[0] && thr[1] && thr[2]) break outer;
            }
        }

        int center = heart[0] + body[2];
        boolean[] two = new boolean[2];
        for (int i = 1; i < n; i++) {
            if (!isOut(center+i, heart[1]-1) && arr[center+i].charAt(heart[1]-1) == '*') body[3]++;
            else two[0] = true;
            if (!isOut(center+i, heart[1]+1) && arr[center+i].charAt(heart[1]+1) == '*') body[4]++;
            else two[1] = true;
            if (two[0] && two[1]) break;
        }

        sb.append((heart[0]+1) + " " + (heart[1]+1) + "\n");
        for (int i = 0; i < 5; i++) {
            sb.append(body[i]+" ");
        }
        System.out.println(sb.toString());
    }
}

