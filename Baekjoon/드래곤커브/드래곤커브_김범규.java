package Baekjoon.드래곤커브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class 드래곤커브_김범규 {

    static Set<Point> drPoints;
    static int N, x, y, d, g;

    // x좌표 증가 (y, x + 1);
    // y좌표 감소 (y - 1, x);
    // x좌표 감소 (y, x - 1);
    // y좌표 증가 (y + 1, x);
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 드래곤 커브 수
        N = Integer.parseInt(br.readLine());

        drPoints = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 좌표 입력 받고...
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            // 여기서 드래곤 커브에 대한 좌표를 저장?
            dragonCurve(x, y, d, g);
        }

        int cnt = 0;

        for (Point p : drPoints) {
            int x = p.x;
            int y = p.y;
            // 사각형을 확인할 때, 네 점을 한번에 확인
            if (drPoints.contains(new Point(x + 1, y)) && 
                drPoints.contains(new Point(x, y + 1)) &&
                drPoints.contains(new Point(x + 1, y + 1))) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static void dragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        // 초기 방향을 추가해줌
        directions.add(d);

        // 세대만큼 방향 전환해줘서 리스트에 추가
        for (int i = 0; i < g; i++) {
            int size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                // 시계방향으로 회전
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        // 첫 점을 추가하고
        drPoints.add(new Point(x, y));
        int nx = x;
        int ny = y;
        for (int dir : directions) {
            // 하나씩 전환해주면서 점 추가하기
            nx += dx[dir];
            ny += dy[dir];
            drPoints.add(new Point(nx, ny));
        }
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}
