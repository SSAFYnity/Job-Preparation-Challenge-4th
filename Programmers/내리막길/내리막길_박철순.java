import java.util.*;
import java.io.*;

public class Main_1520 {
    static int N;
    static int M;
    static int[][] graph;
    static int count = 0;
    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        dp = new int[N][M];
        for (int i=0;i<N;i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i=0;i<N;i++){
            st = new StringTokenizer(in.readLine());
            for (int j=0;j<M;j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count = dfs(0,0);
        System.out.println(count);
    }

    static int dfs(int x, int y) {
        if (x == N-1 && y == M-1) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int dir=0;dir<4;dir++) {
            int nx = x+dx[dir];
            int ny = y+dy[dir];
            if (nx<0||nx>=N||ny<0||ny>=M) continue;
            if (graph[nx][ny] < graph[x][y]) {
                dp[x][y] += dfs(nx,ny);
            }
        }

        return dp[x][y];
    }
}

