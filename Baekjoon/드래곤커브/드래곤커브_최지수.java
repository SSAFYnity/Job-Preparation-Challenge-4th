import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k, answer, len, inf = Integer.MAX_VALUE;
    static int[][] dr = {{0,-1,0,1}, {1,0,-1,0}};
    static boolean[][] squ = new boolean[101][101];
    static class WV {
        int y, x;
        public WV(int y, int x) {
            this.y = y;
            this.x = x;
        }
        public String toString() {return "[" + y + ", " + x + "]";}
    }
    static WV[] arr = new WV[(int) Math.pow(2, 10)+1];
    static int idx = 0;

    static void zero(int y, int x, int d) {
        idx = 0;
        setting(idx, y, x);
        idx++;
        setting(idx, y+dr[0][d], x+dr[1][d]);
    }
    static void gen(int g) {
        if (g == 0) return;
        // 변화값 바꾸고 y에 * -1
        WV fin = arr[idx];
        for (int i = 1; i <= idx; i++) {
            WV now = arr[idx-i];
            setting(idx+i, fin.y+(now.x-fin.x), fin.x-(now.y-fin.y));
        }
        idx += idx;
        gen(g-1);
    }
    static void initialFunc() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new WV(0,0);
        }
    }

    static void setting(int idx, int y, int x) {
        arr[idx].y = y;
        arr[idx].x = x;
        squ[y][x] = true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        initialFunc();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            zero(y,x,d);
            gen(g);
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (squ[i][j] && squ[i+1][j] && squ[i][j+1] && squ[i+1][j+1]) answer++;
            }
        }

        System.out.println(answer);
    }
}

