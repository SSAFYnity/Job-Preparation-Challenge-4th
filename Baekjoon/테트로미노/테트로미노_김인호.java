import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visitedMap;
    static int N,M;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M =  Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        visitedMap = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visitedMap[i][j] = true;
                DFS(i,j,1,map[i][j]);
                visitedMap[i][j] = false;
            }
        }
        System.out.println(maxSum);
    }
    static void DFS(int y, int x, int depth, int sum){
        if(depth==4){
            maxSum = Math.max(maxSum,sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ny = dy[i]+y;
            int nx = dx[i]+x;
            if (nx>=0&&ny>=0&&nx<M&&ny<N){
                if(visitedMap[ny][nx]) continue;
                if (depth==2){
                    visitedMap[ny][nx] = true;
                    DFS(y,x,depth+1,sum+map[ny][nx]);
                    visitedMap[ny][nx] = false;
                }
                visitedMap[ny][nx] = true;
                DFS(ny,nx,depth+1,sum+map[ny][nx]);
                visitedMap[ny][nx] = false;
            }
        }
    }
}
