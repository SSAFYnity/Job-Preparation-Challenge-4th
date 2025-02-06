import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int M, N;
	static int[][] box;
	static int[][] way = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	static int[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[M][N];
		visited = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = -1;
			}
		}

		System.out.println(go(0, 0));

	}

	static int go(int x, int y) {
		if (x == M - 1 && y == N - 1) {
			return 1;
		}
		if (visited[x][y] != -1) {
			return visited[x][y];
		}
		visited[x][y] = 0;
		for (int w = 0; w < 4; w++) {
			int nx = x + way[w][0];
			int ny = y + way[w][1];
			if (!inRange(nx, ny))
				continue;
			if (box[x][y] > box[nx][ny]) {
				visited[x][y] += go(nx, ny);
			}
		}
		return visited[x][y];
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}

}
