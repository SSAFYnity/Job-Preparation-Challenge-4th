import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 행의 개수, 열의 개수
	static int[][] box;
	static int[][] way = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		box = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = Integer.MIN_VALUE;
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, box[i][j], 1, visited);
				visited[i][j] = false;
				// ㅗ ㅜ ㅓ ㅏ 확인
				other(i, j);
			}
		}
		System.out.println(max);

	}

	static void other(int i, int j) {
		for (int s = 0; s < 4; s++) {
			int sum = box[i][j];
			for (int l = 0; l < 3; l++) {
				int idx = (s + l) % 4;
				int nx = i + way[idx][0];
				int ny = j + way[idx][1];
				if (!isValid(nx, ny)) {
					sum = 0;
					break;
				}
				sum += box[nx][ny];
			}
			max = Math.max(sum, max);
		}
	}

	static void dfs(int i, int j, int sum, int depth, boolean[][] visited) {
		if (depth == 4) {
			max = Math.max(sum, max);
			return;
		}
		for (int w = 0; w < 4; w++) {
			int nx = i + way[w][0];
			int ny = j + way[w][1];
			if (!isValid(nx, ny)) {
				continue;
			}
			if (visited[nx][ny]) {
				continue;
			}
			visited[nx][ny] = true;
			dfs(nx, ny, sum + box[nx][ny], depth + 1, visited);
			visited[nx][ny] = false;
		}
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}