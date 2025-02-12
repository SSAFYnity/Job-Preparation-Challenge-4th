import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k, answer, len, inf = Integer.MAX_VALUE;
    static int[][] room, nextPlus;
    static int up, down;
    static int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};
    static boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= m;}

    public static void spread() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(nextPlus[i], 0);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nextPlus[i][j] += room[i][j];
                int value = room[i][j] / 5;
                if (value < 1) continue;
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int y = i + dr[0][d];
                    int x = j + dr[1][d];
                    if (isOut(y,x) || room[y][x] == -1) continue;
                    nextPlus[y][x] += value;
                    cnt++;
                }
                nextPlus[i][j] -= value * cnt;
            }
        }

        int[][] temp = nextPlus;
        nextPlus = room;
        room = temp;
    }

    public static void filter() {
        answer -= (room[up-1][0] + room[down+1][0]);

        for (int i = up-2; i >= 0; i--) { room[i+1][0] = room[i][0]; }
        for (int i = 1; i < m; i++) { room[0][i-1] = room[0][i]; }
        for (int i = 1; i <= up; i++) { room[i-1][m-1] = room[i][m-1]; }
        for (int i = m-2; i > 0; i--) { room[up][i+1] = room[up][i]; }
        room[up][1] = 0;

        for (int i = down+2; i < n; i++) { room[i-1][0] = room[i][0]; }
        for (int i = 1; i < m; i++) { room[n-1][i-1] = room[n-1][i]; }
        for (int i = n-2; i >= down; i--) { room[i+1][m-1] = room[i][m-1]; }
        for (int i = m-2; i > 0; i--) { room[down][i+1] = room[down][i]; }
        room[down][1] = 0;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        room = new int[n][m];
        nextPlus = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] != -1) answer += room[i][j];
                else if (up == 0) up = i;
                else down = i;
            }
        }

        for (int i = 0; i < k; i++) {
            spread();
            filter();
        }

        System.out.println(answer);
    }
}

