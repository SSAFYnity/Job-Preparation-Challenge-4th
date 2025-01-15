#include <iostream>
#include <vector>
using namespace std;

int N, M, ans;
int arr[501][501];
vector<vector<pair<int, int>>>poly = {
	// ���� ��ĭ
	{{0,0},{0,1},{0,2},{0,3}},
	{{0,0},{1,0},{2,0},{3,0}},
	// ���簢��
	{{0,0},{1,0},{0,1},{1,1}},
	// �� ���
	{{0,0},{1, 0},{2,0},{2,1}},
	{{2,0},{0,1},{1,1},{2,1}},
	{{0,0},{0,1},{1,1},{2,1}},
	{{0,0},{0,1},{1,0},{2,0}},
	{{1,0},{1,1},{1,2},{0,2}},
	{{0,0},{0,1},{0,2},{1,0}},
	{{0,0},{1,0},{1,1},{1,2}},
	{{0,0},{0,1},{0,2},{1,2}},
	// �� ���
	{{0,0},{1,0},{1,1},{2,1}},
	{{1,0},{1,1},{0,1},{0,2}},
	{{1,0},{2,0},{0,1},{1,1}},
	{{0,0},{0,1},{1,1},{1,2}},
	
	// �� ���
	{{0, 0},{0,1},{0,2},{1,1}},
	{{0, 0},{1,0},{2,0},{1,1}},
	{{1, 0},{1,1},{1,2},{0,1}},
	{{1, 0},{1,1},{0,1},{2,1}},
};

bool isRange(int x, int y) {
	return x >= 0 && x < N&& y >= 0 && y < M;
}

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> arr[i][j];
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			for (int k = 0; k < poly.size(); k++) {
				int sum = 0;
				bool isCal = true;
				// 4ĭ ��� ���� ��
				for (int z = 0; z < 4; z++) {
					int x =i+ poly[k][z].first;
					int y =j+ poly[k][z].second;
					if (isRange(x, y)) {
						sum += arr[x][y];
					}
					else {
						isCal = false;
						break;
					}
				}
				if (isCal) ans = max(ans, sum);
			}
		}
	}


	cout << ans << "\n";


	return 0;
}