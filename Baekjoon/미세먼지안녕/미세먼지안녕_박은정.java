import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T;
	static int[][] box;
	static int[][] way = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		box = new int[R][C];
		int bottomR = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				box[r][c] = Integer.parseInt(st.nextToken());
				if (box[r][c] == -1) {
					bottomR = r;
				}
			}
		}

		while (T-- > 0) {
			spread();
			filter(bottomR - 2, 0, bottomR - 1, way);
			filter(bottomR + 1, bottomR, R - 1, new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } });

		}

		int ans = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (box[r][c] != -1) {
					ans += box[r][c];
				}
			}
		}
		System.out.println(ans);

	}

	static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(box[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("==========");
	}

	static void filter(int row, int minRow, int maxRow, int[][] way) {
		int x = row, y = 0;

		int last = box[x][y];

		for (int w = 0; w < 4; w++) {

			while (true) {
				int nx = x + way[w][0];
				int ny = y + way[w][1];
				if (nx < 0 || nx >= R || ny < 0 || ny >= C)
					break;
				if (!(nx >= minRow && nx <= maxRow)) {
					break;
				}
				if (box[nx][ny] == -1) {
					box[x][y] = 0;
				} else {
					box[x][y] = box[nx][ny];

				}
				// System.out.println(nx + "," + ny);
				x = nx;
				y = ny;
			}
		}
	}

	static void spread() {
		int[][] next = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (box[r][c] > 0) {
					int dust = box[r][c];
					int nearDust = dust / 5;
					int spreadCnt = 0;
					for (int w = 0; w < 4; w++) {
						int nx = r + way[w][0];
						int ny = c + way[w][1];
						if (inRange(nx, ny) && box[nx][ny] != -1) {
							spreadCnt += 1;
							next[nx][ny] += nearDust;
						}
					}
					next[r][c] += dust - nearDust * spreadCnt;
				} else if (box[r][c] == -1) {
					next[r][c] = -1;
				}
			}
		}
		box = next;
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}