
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내리막길_김현준 {
    static int N, M;
    static StringTokenizer st;
    static int[][] map;
    static int[][] dp;
    static int answer;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;
        map = new int[N][M];
        dp = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int r, int c) {
        // 마지막 위치에 도착했다면
        if(r == N-1 && c == M-1) return 1;
        if(dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 0;

        // 현재 위치보다 낮은 방향으로만 진행 가능
        for(int k=0;k<4;k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if(inRange(nr, nc) && map[r][c] > map[nr][nc]) {
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
