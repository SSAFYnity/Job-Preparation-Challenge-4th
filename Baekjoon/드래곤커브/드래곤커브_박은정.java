import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static int[][] way = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };

	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[101][101];

		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()); // 시작 방향
			int g = Integer.parseInt(st.nextToken()); // generatiton
			draw(x, y, createDragonCurve(d, g));

		}

		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (visited[i][j] && visited[i + 1][j] && visited[i][j + 1] && visited[i + 1][j + 1]) {
					cnt += 1;
				}
			}
		}
		System.out.println(cnt);

	}

	static void draw(int x, int y, List<Integer> directions) {
		int nx = x, ny = y;
		visited[x][y] = true;
		for (int d : directions) {
			nx += way[d][0];
			ny += way[d][1];
			visited[nx][ny] = true;
		}
	}

	static List<Integer> createDragonCurve(int d, int g) {
		List<Integer> directions = new ArrayList<>();
		directions.add(d);
		while (g-- > 0) {
			int size = directions.size();
			for (int j = size - 1; j >= 0; j--) {
				directions.add((directions.get(j) + 1) % 4);
			}
		}
		return directions;
	}

}