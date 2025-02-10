#include <iostream>
#include <stack>
using namespace std;

int N;
int arr[1000001];
int ans[1000001];
stack<int>s;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);
	
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	for (int i = N - 1; i >= 0; i--) {
		while (!s.empty() && s.top() <= arr[i]) s.pop();
		if (s.empty()) ans[i] = -1;
		else ans[i] = s.top();
		s.push(arr[i]);
	}

	for (int i = 0; i < N; i++) {
		cout << ans[i] << " ";
	}

	return 0;
}