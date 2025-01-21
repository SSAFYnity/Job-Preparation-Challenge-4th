import java.io.*;
import java.util.*;

// 0: x좌표가 증가하는 방향 (→)
// 1: y좌표가 감소하는 방향 (↑)
// 2: x좌표가 감소하는 방향 (←)
// 3: y좌표가 증가하는 방향 (↓)

public class Main {
    static int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};  // 방향: →, ↑, ←, ↓
    static int[][] checks = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};  // 방향: →, ↓, ↘
    // 드래곤 커브 방향 생성 함수
    private static List<Integer> makeDirections(int d, int g) {
        List<Integer> ds = new ArrayList<>();
        ds.add(d);
        // 각 세대별 이동 모양 계산
        for (int i = 1; i <= g; i++) {
            int size = ds.size();
            for (int j = size - 1; j >= 0; j--) {
                ds.add((ds.get(j) + 1) % 4);
            }
        }
        return ds;
    }
    // 드래곤 커브 생성 함수
    private static void generateDragonCurve(boolean[][] map, int x, int y, List<Integer> ds) {
        map[y][x] = true;
        for (int d : ds) {
            x += directions[d][1];
            y += directions[d][0];
            map[y][x] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[101][101];
        for (int i = 0; i < N; i++) {
            String[] dragonCurve = br.readLine().split(" ");
            int x = Integer.parseInt(dragonCurve[0]), y = Integer.parseInt(dragonCurve[1]);  // x, y 시작점
            int d = Integer.parseInt(dragonCurve[2]);  // 방향
            int g = Integer.parseInt(dragonCurve[3]);  // 세대
            List<Integer> ds = makeDirections(d, g);
            generateDragonCurve(map, x, y, ds);
        }

        int count = 0;
        for (int x = 0; x <= 99; x++) {
            for (int y = 0; y <= 99; y++) {
                boolean flag = true;
                for (int[] check : checks) {
                    int nx = x + check[1];
                    int ny = y + check[0];
                    if (!map[ny][nx]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    count++;
                }
            }
        }

        System.out.println(count);

    }
}
