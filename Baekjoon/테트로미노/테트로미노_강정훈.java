import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Tetromino tetromino = new Tetromino(n, m, map);
        int answer = tetromino.findAnswer();

        System.out.println(answer);
    }
}

class Tetromino {

    private int n;
    private int m;
    private int[][] map;
    private boolean[][] visited;
    private int answer;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public Tetromino(int n, int m, int[][] map) {
        this.n = n;
        this.m = m;
        this.map = map;
        this.visited = new boolean[n][m];
        this.answer = Integer.MIN_VALUE;
    }

    public int findAnswer() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                visited[row][col] = true;
                dfs(row, col, map[row][col], 1);
                visited[row][col] = false;
            }
        }
        return answer;
    }

    private void dfs(int row, int col, int sum, int count) {
        if (count == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int[] direction : directions) {
            int nr = row + direction[0];
            int nc = col + direction[1];

            if (isValid(nr, nc)) {
                if (count == 2) {
                    visited[nr][nc] = true;
                    dfs(row, col, sum + map[nr][nc], count + 1);
                    visited[nr][nc] = false;
                }

                visited[nr][nc] = true;
                dfs(nr, nc, sum + map[nr][nc], count + 1);
                visited[nr][nc] = false;
            }
        }
    }
    private boolean isValid(int nr, int nc) {
        return nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc];
    }
}