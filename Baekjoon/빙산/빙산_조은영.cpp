#include <iostream>
#include <queue>
using namespace std;

int map[301][301];
int temp[301][301];
bool visited[301][301];
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };
int N, M;

// 잘못한거 68줄에서 범위체크 안함, fill 함수를 반복문 안에 넣음

bool isRange(int x, int y) {
	return x >= 0 && x < N&& y >= 0 && y < M;
}

bool dfs(int x, int y) {
	// 범위 넘기면
	if (!isRange(x,y)) return false;
	if (map[x][y] == 0 || visited[x][y]) return false;

	visited[x][y] = true;

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		dfs(nx, ny);
	}

	return true;
}

int main() {

	int year = 0, ans=0;
	queue<pair<int,int>>q;

	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> map[i][j];
			if (map[i][j] != 0) q.push({ i,j });
		}
	}

	while (1) {
	
		year++;
		if (q.empty()) {
			ans = 0;
			break;
		}

		while (!q.empty()) {
			int x = q.front().first;
			int y = q.front().second;
			q.pop();
			int sea = 0;

			

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (map[nx][ny] == 0 && isRange(nx, ny)) sea++;
			}
			temp[x][y] = max(0, map[x][y] - sea);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = temp[i][j];
				if (temp[i][j] != 0) q.push({ i,j });
			}
		}
		fill(visited[0], visited[N], false);
		// 덩어리 개수 찾기. 2개 이상이면 break
		int iceberg = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (dfs(i, j)) iceberg++;
			}
		}

		if (iceberg >= 2) {
			ans = year;
			break;
		} 

	}
	
	cout << ans << "\n";

	return 0;
}