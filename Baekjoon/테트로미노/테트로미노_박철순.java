import java.util.*;
import java.io.*;

public class Main_14500 {

    static int N;
    static int M;
    static int[][] graph;
    // 상 하 좌 우
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    static int maxScore = -1;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int i=0;i<N;i++){
            st = new StringTokenizer(in.readLine());
            for (int j=0;j<M;j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                visited[i][j] = true;
                dfs(i, j, 1, graph[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(maxScore);
    }

    private static void dfs(int x, int y, int count, int score) {
        if (count == 4) {
            maxScore = Math.max(score, maxScore);
            return;
        }

        for (int dir=0;dir<4;dir++) {
            int nx = x+dx[dir];
            int ny = y+dy[dir];
            if (!isRange(nx,ny)) continue;
            if (visited[nx][ny]) continue;
            if (count == 2) {
                // 이전 위치에서 한번 더. (ㅜ, ㅗ) 모양
                visited[nx][ny] = true;
                dfs(x, y, count+1, score+graph[nx][ny]);
                visited[nx][ny] = false;
            }
            visited[nx][ny] = true;
            dfs(nx, ny, count+1, score+graph[nx][ny]);
            visited[nx][ny] = false;
        }

    }

    private static boolean isRange(int x, int y) {
        if (x<0 || x>=N || y<0 || y>=M) return false;
        return true;
    }
}
