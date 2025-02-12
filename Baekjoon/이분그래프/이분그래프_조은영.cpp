#include <iostream>
#include <map>
#include <vector>
using namespace std;

int T, V, E;
int a, b;
bool visited[20001];
map<int, int>mp;

void dfs(int start, vector<int> (&v)[20001]) {
	visited[start] = true;
	for (int i = 0; i < v[start].size(); i++) {
		int next = v[start][i];
		if (!visited[next]) {
			// start와 다른 색칠하기
			mp[next] = (mp[start]==1 ? 2 : 1);
			dfs(next, v);
		}
	}
}

string ans = "";

void check(int node, vector<int>(&v)[20001], int nodeNum) {
	for (int i = 1; i <= nodeNum; i++) {
		for (int j = 0; j < v[i].size(); j++) {
			int next = v[i][j];
			if (mp[next] == mp[i]) {
				ans = "NO";
				return;
			}
		}
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> T;

	while (T--) {
		vector<int>v[20001];
		cin >> V >> E;

		mp.clear();
		fill(visited, visited + 20001, false);
		ans = "YES";

		for (int i = 0; i < E; i++) {
			cin >> a >> b;
			v[a].push_back(b);
			v[b].push_back(a);
		}

		// 연결 안된 정점 고려
		for (int i = 1; i <= V; i ++) {
			if (!visited[i]) {
				mp[i] = 1;
				dfs(i, v);
			}
		}

		check(1, v, V);

		cout << ans << "\n";
	}

	return 0;
}