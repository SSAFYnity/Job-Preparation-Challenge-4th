#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

int N, M, button;
bool buttons[10];

bool isValid(int num) {
	string numStr = to_string(num);

	for (int i = 0; i < numStr.length(); i++) {
		if (buttons[numStr[i] - '0']) return false;
	}
	return true;
}

int main() {
	int now = 100;

	cin >> N >> M;

	for (int i = 0; i < M; i++) {
		cin >> button;
		buttons[button] = true; // ��� ���ϴ°� true
	}

	int ans = abs(now - N);

	for (int i = 0; i < 1000000; i++) {
		// ��ȿ���� �˻�
		if (isValid(i)) {
			int len = to_string(i).length();
			ans = min(ans, len + abs(N-i));
		}
	}

	cout << ans << "\n";

	return 0;
}