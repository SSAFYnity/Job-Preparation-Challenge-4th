package SSAFYnity.no5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Baekjoon_250108_빙산_buffer {

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
	static int[][] sea, copy, visited;
	static int N, M;
	static Queue<Iceberg> icebergs; // 빙하 목록
	static Queue<Iceberg> queue; // bfs용 큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sea = new int[N][M];
		copy = new int[N][M];

		icebergs = new LinkedList<>();

		int h = -1;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				h = Integer.parseInt(st.nextToken());
				if (h != 0) {
					sea[r][c] = h;
					copy[r][c] = h;
					icebergs.add(new Iceberg(r, c, h));
				}
			}
		} // 빙산 입력

		// 빙산 덩어리 개수 카운트 bfs
		int groupCount = countIceberg();
		int year = 0;

		// 반복
		// 그룹 개수가 2 이상이거나, 그룹 개수가 0이 되면 그만
		while (groupCount != 0 && groupCount < 2) {
			year++;

			// 1. dfs
			// 사방 검색 후 빙하 높이 -1
			melting();

			// 배열 복사
			// 빙하 높이가 감소한 배열 값을 원본에 복사
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					sea[r][c] = copy[r][c];
				}
			}

			// 2. bfs
			// 빙하 덩어리 개수 카운트
			groupCount = countIceberg();
		}

		// 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.
		if (groupCount < 2) {
			System.out.println(0);
		} else {

			System.out.println(year);
		}
	}// main

	// 빙산 목록에서 하나씩 꺼내서 탐색
	private static void melting() {
		if (!icebergs.isEmpty()) {
			Iceberg initIce = icebergs.poll();
			int initR = initIce.r;
			int initC = initIce.c;

			visited = new int[N][M];
			visited[initR][initC] = 1;

			dfs(initIce);

			while (true) {
				if (icebergs.isEmpty()) {
					break;
				}

				Iceberg ice = icebergs.peek();
				if (visited[ice.r][ice.c] == 1) {
					break;
				}

				ice = icebergs.poll();
				visited[ice.r][ice.c] = 1;

				dfs(ice);
			}
		}
	}// ()

	// 빙하 높이 감소 dfs
	private static void dfs(Iceberg ice) {
		int r = ice.r;
		int c = ice.c;
		int h = ice.height;

		// 사방 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				if (sea[nr][nc] <= 0) {
					ice.height--;
					copy[ice.r][ice.c]--;
				}
			}
		}

		// 만약 0이 아니라면, 아직 빙산이므로 빙산 목록에 다시 추가
		if (ice.height > 0) {
			icebergs.add(ice);
		}
	}

	// 빙산 덩어리 개수 카운트
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
				if (initR == ice.r && initC == ice.c) {
					break;
				}
				// 상단 방문체크 조건문을 위해, 빙산 목록 꺼내고 넣기를 반복해야 함
				ice = icebergs.poll();
				if (visited[ice.r][ice.c] != 1) { // 아직 그룹 카운팅이 안 된 빙산이라면
					visited[ice.r][ice.c] = 1;
					queue = new LinkedList<>();
					queue.add(ice);

					count += bfs();
				}
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
			count = 1; // 단일 개체도 덩어리임

			// 사방탐색
			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				// 경계 조건 확인
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (sea[nr][nc] > 0 && visited[nr][nc] == 0) {
						visited[nr][nc] = 1;
						queue.add(new Iceberg(nr, nc));
					}
				}
			}
		}
		return count;
	} // bfs

}// class
