import java.util.*;

public class Main {
    // 방향 벡터: 0-오른쪽, 1-위, 2-왼쪽, 3-아래
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] map = new boolean[101][101]; // 드래곤 커브 좌표 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 드래곤 커브의 개수

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt(); // 시작 x 좌표
            int y = sc.nextInt(); // 시작 y 좌표
            int d = sc.nextInt(); // 시작 방향
            int g = sc.nextInt(); // 세대 수

            drawDragonCurve(x, y, d, g);
        }

        System.out.println(countSquares());
    }

    static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>(); // 방향 저장 리스트
        directions.add(d);

        for (int i = 0; i < g; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4); // 방향 회전
            }
        }

        map[x][y] = true; // 시작 좌표 방문 처리
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            map[x][y] = true; // 드래곤 커브 방문 처리
        }
    }

    static int countSquares() {
        int count = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 정사각형이 되는 조건: (i, j), (i+1, j), (i, j+1), (i+1, j+1) 모두 true
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    count++;
                }
            }
        }

        return count;
    }
}