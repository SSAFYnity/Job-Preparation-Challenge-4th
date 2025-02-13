import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;

public class Main {

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int[][] cMap;
    static List<Point> glaciers = new ArrayList<>();
    static List<Point> newGlaciers = new ArrayList<>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cMap = new int[N][M];
        v = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) glaciers.add(new Point(i, j));
            }
        }

        // 지도 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cMap[i][j] = map[i][j];
            }
        }

        if(dfs(glaciers.get(0).r, glaciers.get(0).c) != glaciers.size()) {
            System.out.println(0);
            System.exit(0);
        }

        // 빙산이 있는 기준에서, 4개의 방향에 0의 개수마다 -1씩 진행하면서, 0이 되면 종료

        // 지도를 갱신하지말고, 빙하를 갱신하자

        // 빙산이 분리되는지 안되는지 체크해야함, 분리되면 종료

        int year = 1;
        while (true) {
            // 모든 빙산 기준 탐색
            v = new boolean[N][M];

            for (Point p : glaciers) {
                for (int k = 0; k < 4; k++) {
                    int nr = p.r + dr[k];
                    int nc = p.c + dc[k];
                    if (inRange(nr, nc) && map[nr][nc] == 0) {
                        cMap[p.r][p.c]--;
                    }
                }
                if (cMap[p.r][p.c] <= 0) cMap[p.r][p.c] = 0; // -면 0으로 초기화
                else newGlaciers.add(new Point(p.r, p.c)); // 계산 끝난 빙산들 추가
            }

            // 기존 지도 갱신
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = cMap[i][j];
                }
            }

            glaciers.clear();
            for (Point p : newGlaciers) {
                glaciers.add(new Point(p.r, p.c));
            }
            newGlaciers.clear();

            if(glaciers.isEmpty()) {
                year = 0;
                break;
            }
            int connect = dfs(glaciers.get(0).r, glaciers.get(0).c);
            if(connect != glaciers.size()) break;
            year++;
        }

        System.out.println(year);

    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static int dfs(int r, int c) {
        v[r][c] = true;
        int count = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (inRange(nr, nc) && map[nr][nc] != 0 && !v[nr][nc]) {
                count += dfs(nr, nc);
            }
        }
        return count;
    }
}