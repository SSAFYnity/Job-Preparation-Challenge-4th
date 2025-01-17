#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int R, C, T;
int map[51][51];
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };
vector<int>xPosition;

bool isRange(int x, int y) {
	return x >= 0 && x < R&& y >= 0 && y < C;
}

int main() {

	cin >> R >> C >> T;


	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cin >> map[i][j];
			if (map[i][j] == -1) xPosition.push_back(i);
		}
	}

	while (T--) {
		// 확산

		int temp[51][51] = { 0, };

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				temp[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0){
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (isRange(nx, ny) && map[nx][ny] != -1) {
							temp[nx][ny] += floor(map[i][j] / 5);
							cnt++;
						}
					}
					temp[i][j] -= floor(map[i][j] / 5) * cnt;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = temp[i][j];
			}
		}

		/*for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				cout << map[i][j];
			}
			cout << "\n";
		}*/

		// 공기청정
		int X = xPosition[0];
		int x = xPosition[1];

		for (int i = X - 1; i >0; i--) map[i][0] = map[i - 1][0];
		for (int i = 0; i < C - 1; i++) map[0][i] = map[0][i + 1];
		for (int i = 0; i < X; i++) map[i][C-1] = map[i+1][C-1];
		for (int i = C - 1; i > 1; i--) map[X][i] = map[X][i - 1];
		map[X][1] = 0;
		for (int i = x + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
		for (int i = 0; i < C - 1; i++) map[R - 1][i] = map[R - 1][i + 1];
		for (int i = R - 1; i > x; i--) map[i][C - 1] = map[i - 1][C - 1];
		for (int i = C - 1; i > 1; i--) map[x][i] = map[x][i - 1];
		map[x][1] = 0;
	}

	int ans = 0;

	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (map[i][j] > 0) ans += map[i][j];
		}
	}

	cout << ans << "\n";

	return 0;
}