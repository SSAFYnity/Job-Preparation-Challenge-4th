import java.util.*;
import java.IO.*;
public class Main {
    static int[][] map = new int[101][101]; 
    static int[] dx = {1, 0, -1, 0};        
    static int[] dy = {0, -1, 0, 1};        
    // 드래곤 커브를 그리는 함수
    static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);
        map[y][x] = 1;  // 시작점 표시

        // 0세대부터 g세대까지 방향 확장
        for (int i = 0; i < g; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        // 드래곤 커브 그리기
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            if (x >= 0 && x <= 100 && y >= 0 && y <= 100) {
                map[y][x] = 1;
            }
        }
    }

    // 정사각형 개수를 세는 함수
    static int countSquares() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1 && map[i + 1][j] == 1 &&
                    map[i][j + 1] == 1 && map[i + 1][j + 1] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 드래곤 커브의 개수
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt(); // 시작 방향
            int g = sc.nextInt(); // 세대
            drawDragonCurve(x, y, d, g);
        }
        System.out.print(countSquares());
    }
}
