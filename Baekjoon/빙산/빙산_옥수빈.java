import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N, M;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static boolean[][] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new int[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cnt += map[i][j];
            }
        }

        int[][] melt = new int[N][M];
        int t = 1;
        while(cnt > 0){
            for (int i = 0; i < N; i++) {
                Arrays.fill(melt[i], 0);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0){
                        melt[i][j] = countSide(i, j);
                    }
                }
            }

            boolean flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0) {
                        melt[i][j] = Math.min(map[i][j], melt[i][j]);
                        map[i][j] -= melt[i][j];
                        cnt -= melt[i][j];
                        if (map[i][j] == 0)
                            flag = true;
                    }
                }
            }
            if (flag) {
                if (isSeparated()) {
                    System.out.println(t);
                    return;
                }
            }
            t++;
        }

        System.out.println(0);

    }

    public static boolean isSeparated() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        int iceberg = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (map[i][j] == 0) {
                        visited[i][j] = true;
                    } else {
                        if (iceberg == 1) {
                            return true;
                        }
                        Queue<Point> q = new LinkedList<>();
                        q.add(new Point(i, j));
                        visited[i][j] = true;
                        while (!q.isEmpty()) {
                            Point p = q.poll();
                            for (int k = 0; k < 4; k++) {
                                int nx = p.x + dx[k];
                                int ny = p.y + dy[k];
                                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                                    if (!visited[nx][ny] && map[nx][ny] != 0) {
                                        q.add(new Point(nx, ny));
                                        visited[nx][ny] = true;
                                    }
                                }
                            }
                        }
                        iceberg++;
                    }
                }
            }
        }
        return false;
    }


    public static int countSide(int x, int y) {

        int side = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                if (map[nx][ny] == 0) {
                    side++;
                }
            }
        }
        return side;
    }

}