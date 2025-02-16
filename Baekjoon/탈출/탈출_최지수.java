import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k, len, inf = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    static char GROUND = '.', WATER = '*', ROCK = 'X', GOAL = 'D', HEDGEHOG = 'S';

    static int[][] dr = {{0,0,-1,1}, {1,-1,0,0}};
    static char[][] map;

    static class WV {
        int y, x, t = 0;
        public WV(int y, int x) {
            this.y = y;
            this.x = x;
        }
        public WV(int y, int x, int t) {
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }
    static boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= m;}
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        boolean[][] visit = new boolean[n][m];
        ArrayDeque<WV> que = new ArrayDeque<>();
        ArrayDeque<WV> waterQue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == HEDGEHOG) {
                    que.add(new WV(i,j,0));
                    map[i][j] = '.';
                    visit[i][j] = true;
                } else if (map[i][j] == WATER) {
                    waterQue.add(new WV(i,j));
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        int level = -1;
        while (!que.isEmpty()) {
            WV now = que.poll();
            if (map[now.y][now.x] == GOAL) {
                answer = now.t;
                break;
            }
            if (level < now.t) {
                int cnt = waterQue.size();
                for (int i = 0; i < cnt; i++) {
                    WV water = waterQue.poll();
                    for (int d = 0; d < 4; d++) {
                        int y = water.y + dr[0][d];
                        int x = water.x + dr[1][d];
                        if (isOut(y,x) || map[y][x] != GROUND) continue;
                        waterQue.add(new WV(y,x));
                        map[y][x] = WATER;
                    }
                }
                level++;
            }
            for (int d = 0; d < 4; d++) {
                int y = now.y + dr[0][d];
                int x = now.x + dr[1][d];
                if (isOut(y,x) || visit[y][x] || (map[y][x] != GROUND && map[y][x] != GOAL)) continue;
                que.add(new WV(y,x,now.t+1));
                visit[y][x] = true;
            }
        }


        System.out.println(answer == inf ? "KAKTUS" : answer);
    }
}

