import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k, y, x;

    static int[][] dr = {{0,0,0,-1,1}, {0,1,-1,0,0}};
    static int[][] ver = {{0,1,3,6,4}, {0,1,5,6,2}};
    static boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= m;}

    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dice = new int[7];

        st = new StringTokenizer(br.readLine());
        for (int t = 0; t < k; t++) {
            int d = Integer.parseInt(st.nextToken());
            if (isOut(y+dr[0][d], x+dr[1][d])) continue;
            if (d == 1 || d == 4) {
                for (int i = 4; i >= 0; i--) {
                    dice[ver[d/3][(1+i)%5]] = dice[ver[d/3][i]];
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    dice[ver[d/3][i]] = dice[ver[d/3][(i+1)%5]];
                }
            }
            y+=dr[0][d];
            x+=dr[1][d];
            if (map[y][x] == 0) map[y][x] = dice[6];
            else {
                dice[6] = map[y][x];
                map[y][x] = 0;
            }
            sb.append(dice[1]+"\n");
        }

        System.out.println(sb.toString());
    }
}

