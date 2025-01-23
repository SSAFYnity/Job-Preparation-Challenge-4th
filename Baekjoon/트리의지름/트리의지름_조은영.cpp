#include <iostream>
#include <vector>
#include <stack>
#include <sstream>
using namespace std;

int N, node, edge, linked_node;

stack<pair<int, int>>st; // 노드, 
int last_node, sum;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> N;
	cin.ignore();

	vector <vector< pair<int, int >> > graph(N+1);
	bool *visited = new bool[N + 1];

	for (int i = 0; i < N; i++) {
		string line = "";
		getline(cin, line);
		istringstream iss(line);
		
		iss >> node;

		while (iss >> linked_node && linked_node != -1) {
			iss >> edge;
			graph[node].push_back({ linked_node, edge });
		}
	}

	st.push({ 1,0 });
	visited[1] = true;
	int len = 0, start_node=0;

	while (!st.empty()) {
		int current_node = st.top().first;
		int current_len = st.top().second;
		st.pop();

		// 간선 가중치가 더 높은 노드가 시작점 되어야함
		if (len < current_len) {
			start_node = current_node;
			len = current_len;
		}

		for (int i = 0; i < graph[current_node].size(); i++) {
			if (!visited[graph[current_node][i].first]) {
				visited[graph[current_node][i].first] = true;
				st.push({ graph[current_node][i].first, current_len+graph[current_node][i].second });
			}
		}
	}

	while (!st.empty()) st.pop();

	// 거리 구하기
	st.push({ start_node, 0 });
	fill(visited, visited + N+1, false);
	visited[start_node] = true;

	int ans = 0;

	while (!st.empty()) {
		int current_node = st.top().first;
		int current_len = st.top().second;
		st.pop();

		ans = max(ans, current_len);

		for (int i = 0; i < graph[current_node].size(); i++) {
			if (!visited[graph[current_node][i].first]) {
				visited[graph[current_node][i].first] = true;
				st.push({ graph[current_node][i].first, current_len+graph[current_node][i].second });
			}
		}
	}


	cout << ans << "\n";

	return 0;
}