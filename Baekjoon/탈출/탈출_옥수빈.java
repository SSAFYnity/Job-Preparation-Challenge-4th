import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C, DX, DY, SX, SY;
	static char[][] MAP;
	static Queue<Point> water = new LinkedList<>();
	static int answer = -1;
	static boolean [][] Visited;
	static boolean [][] waterVisited;

	// 상하좌우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		MAP = new char[R][C];
		Visited = new boolean[R][C];
		waterVisited = new boolean[R][C];
		
		char[] charArr = new char[C];

		for (int i = 0; i < R; i++) {
			charArr = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				MAP[i][j] = charArr[j];
				if (MAP[i][j] == 'D') {
					DX = j;
					DY = i;
				} else if (MAP[i][j] == 'S') {
					SX = j;
					SY = i;
				} else if (MAP[i][j] == '*') {
					water.add(new Point(j, i));
				}
			}
		}

		BFS();
		if (answer == -1)
			System.out.println("KAKTUS");
		else
			System.out.println(answer);
	}

	public static void BFS() {
		Queue<Point> hedgehog = new LinkedList<>();
		hedgehog.add(new Point(SX, SY));
		int time = 0;
		while (!hedgehog.isEmpty()) {
			int waterSize = water.size(), hedgehogSize = hedgehog.size();
			// 물 넘치기
			for (int i = 0; i < waterSize; i++) {
				Point p = water.poll();
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					if (canGo(nx, ny)) {
						if (MAP[ny][nx] != 'D' && !waterVisited[ny][nx]) {
							MAP[ny][nx] = '*';
							water.add(new Point(nx, ny));
							waterVisited[ny][nx] = true;
						}
					}
				}
			}
			// 고슴도치 이동
			for (int i = 0; i < hedgehogSize; i++) {
				Point p = hedgehog.poll();
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					if (canGo(nx, ny)) {
						if (MAP[ny][nx] == 'D') {
							answer = ++time;
							return;
						} else if (MAP[ny][nx] != '*' && !Visited[ny][nx]) {
							hedgehog.add(new Point(nx, ny));
							Visited[ny][nx] = true;
						}
					}
				}
			}
			time++;
		}
	}

	public static boolean canGo(int x, int y) {
		if (x >= 0 && y >= 0 && x < C && y < R) {
			if (MAP[y][x] != 'X')
				return true;
		}
		return false;
	}

}