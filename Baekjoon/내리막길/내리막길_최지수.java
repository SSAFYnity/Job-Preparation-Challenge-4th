import java.util.*;
import java.io.*;

public class Main {
    static int n, m;

    static int[][] dr = {{0,0,-1,1}, {1,-1,0,0}};
    static int[][] dp, map;
    static boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= m;}

    static int dfs(int y, int x) {
        if (y == n-1 && x == m-1) return 1;
        if (dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int w = y + dr[0][i];
            int v = x + dr[1][i];
            if (isOut(w,v) || map[w][v] >= map[y][x]) continue;
            dp[y][x] += dfs(w,v);
        }
        return dp[y][x];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0,0));
    }
}

