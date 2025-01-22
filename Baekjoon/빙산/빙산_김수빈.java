import java.util.*;
import java.io.*;


public class 빙산_김수빈 {
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> icebergs = new ArrayList<>();
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);  // 행의 개수
        M = Integer.parseInt(input[1]);  // 열의 개수
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] > 0) {
                    icebergs.add(new int[]{i, j});
                }
            }
        }

        int count = countIceberg();
        int year = 0;

        while (count < 2) {
            if (icebergs.size() == 0) {
                year = 0;
                break;
            }
            meltIceberg();
            count = countIceberg();
            year++;
        }
        System.out.println(year);
    }
    // 빙산을 녹이는 함수
    private static void meltIceberg() {
        // 인접한 바닷물을 확인할 2차원 배열
        int[][] newMap = new int[N][M];
        ArrayList<int[]> newIcebergs = new ArrayList<>();

        for (int[] iceberg : icebergs) {
            int x = iceberg[0];
            int y = iceberg[1];
            int count = 0;
            for (int[] direction : directions) {
                int nx = x + direction[0];
                int ny = y + direction[1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
                    count++;
                }
            }
            newMap[x][y] = Math.max(map[x][y] - count, 0);
            if (newMap[x][y] > 0) {
                newIcebergs.add(new int[]{x, y});
            }
        }
        map = newMap;
        icebergs = newIcebergs;
    }

    // 빙산 개수를 세는 함수
    private static int countIceberg() {
        int count = 0;  // 빙산 개수
        boolean[][] visited = new boolean[N][M];  // 방문 여부

        // 이어진 빙산 확인
        for (int[] iceberg : icebergs) {
            int x = iceberg[0];
            int y = iceberg[1];
            if (!visited[x][y] && map[x][y] > 0) {
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{x, y});
                visited[x][y] = true;

                while(!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    for (int[] direction : directions) {
                        int nx = curr[0] + direction[0];
                        int ny = curr[1] + direction[1];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] > 0) {
                            queue.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
                count++;
            }
        }
        return count;
    };
}
