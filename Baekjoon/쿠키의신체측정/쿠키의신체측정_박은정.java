import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static char[][] box;
	static int[][] way = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		box = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				box[i][j] = line.charAt(j);
			}
		}

		int[] heart = findHeart();
		int i = heart[0], j = heart[1];

		System.out.println((i + 1) + " " + (j + 1)); // 심장 위치
		// 왼쪽팔
		int leftHandLen = findLen(i, j - 1, 0, -1);
		System.out.print(leftHandLen + " ");

		// 오른쪽 팔
		int rightHandLen = findLen(i, j + 1, 0, 1);
		System.out.print(rightHandLen + " ");

		// 허리
		int waist = findLen(i + 1, j, 1, 0);
		System.out.print(waist + " ");

		// 왼쪽 다리
		int x = i + waist, y = j;
		int leftLeg = findLen(x + 1, y - 1, 1, 0);
		System.out.print(leftLeg + " ");
		// 오른쪽 다리
		int rightLeg = findLen(x + 1, y + 1, 1, 0);
		System.out.print(rightLeg);

	}

	static int[] findHeart() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (box[i][j] == '*') {
					boolean isHeart = true;
					for (int w = 0; w < 4; w++) {
						int nx = i + way[w][0];
						int ny = j + way[w][1];
						if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
							isHeart = false;
							break;
						}
						if (box[nx][ny] != '*') {
							isHeart = false;
							break;
						}
					}
					if (isHeart) {
						return new int[] { i, j };
					}
				}
			}
		}
		return new int[] { -1, -1 };
	}

	static int findLen(int x, int y, int dx, int dy) {
		int len = 1;
		while (true) {
			x = x + dx;
			y = y + dy;
			if (x < 0 || x >= N || y < 0 || y >= N) {
				break;
			}
			if (box[x][y] != '*') {
				break;
			}
			len += 1;
		}
		return len;
	}

}
