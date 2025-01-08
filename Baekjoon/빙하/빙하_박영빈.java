package SSAFYnity.no5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon_250108_빙산2 {

	static class Iceberg {
		int r;
		int c;
		int height; // 0 이상 10 이하

		public Iceberg() {
		}

		public Iceberg(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Iceberg(int r, int c, int height) {
			this.r = r;
			this.c = c;
			this.height = height;
		}
	}// Iceberg

	// 사방탐색 (상하좌우)
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] sea, visited;
	static int N, M;
	static Queue<Iceberg> icebergs;
	static Queue<Iceberg> queue;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N과 M은 3 이상 300 이하
		N = sc.nextInt();
		M = sc.nextInt();

		// 배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.
		sea = new int[N][M];

		// 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하
		icebergs = new LinkedList<>();

		int h = -1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				h = sc.nextInt();
				if (h != 0) {
					sea[r][c] = h;
					icebergs.add(new Iceberg(r, c, h));
				}
			}
		}

		// 빙산 덩어리 개수 카운트 BFS
		int groupCount = countIceberg();
		int maxGroupCount = groupCount;
		int year = 0;

		// 반복
		// 그룹 개수가 2 이상이거나, 그룹 개수가 0이 되면 그만
		while (groupCount != 0 && groupCount < 2) {
			year++;

			// 사방 검색 후 높이 -1
			melting();

			// 그룹 개수 카운트
			groupCount = countIceberg();

			maxGroupCount = Math.max(maxGroupCount, groupCount);
		}

		// 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.
		if (maxGroupCount < 2) {
			System.out.println(0);
		} else {

			System.out.println(year);
		}

	}// main

	private static void melting() {
		if (!icebergs.isEmpty()) {
			Iceberg initIce = icebergs.poll();
			int initR = initIce.r;
			int initC = initIce.c;

			dfs(initIce);

			while (true) {
				if (icebergs.isEmpty()) {
					break;
				}
				Iceberg ice = icebergs.peek();
				if (ice.r == initR && ice.c == initC) {
					break;
				}
				ice = icebergs.poll();

				dfs(ice);
			}
		}
	}

	private static void dfs(Iceberg ice) {
		int r = ice.r;
		int c = ice.c;
		int h = ice.height;

		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				if (sea[nr][nc] <= 0) {
					ice.height--;
					sea[ice.r][ice.c]--;
				}
			}
		}

		if (ice.height > 0) {
			icebergs.add(ice);
		}
	}

	// bfs 빙산 덩어리 개수 카운트
	private static int countIceberg() {
		int count = 0;
		if (!icebergs.isEmpty()) {
			Iceberg initIce = icebergs.poll();
			int initR = initIce.r;
			int initC = initIce.c;

			visited = new int[N][M];
			visited[initR][initC] = 1;
			queue = new LinkedList<>();
			queue.add(initIce);
			count += bfs();
			icebergs.add(initIce);

			while (true) {
				Iceberg ice = icebergs.peek();
				if (ice.r == initR && ice.c == initC) {
					break;
				}
				ice = icebergs.poll();
				visited[ice.r][ice.c] = 1;
				queue = new LinkedList<>();
				queue.add(ice);
				count += bfs();
				icebergs.add(ice);
			}

			return count;
		}
		return count;
	}// countIceberg

	private static int bfs() {
		int count = 0;
		while (!queue.isEmpty()) {
			Iceberg ice = queue.poll();
			int r = ice.r;
			int c = ice.c;

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				// 경계 조건 확인
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (sea[nr][nc] > 0 && visited[nr][nc] == 0) {
						visited[nr][nc] = 1;
						count = 1;
						queue.add(new Iceberg(nr, nc));
					}
				}
			}
		}
		return count;
	} // bfs

}// class
