#include <iostream>
using namespace std;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int N = 0;
	char arr[1001][1001] = {};
	int heartX = 0, heartY = 0, leftArm = 0, rightArm = 0, waist = 0, leftLeg = 0, rightLeg = 0, headX=0, headY=0;
	int lastIdx = 0;
	bool isHeart = false;

	cin >> N;

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> arr[i][j];
		}
	}

	// x가 행, y가 열
	for (int i = 1; i <= N; i++) {
		if (isHeart) break;
		for (int j = 1; j <= N; j++) {
			if (arr[i][j] == '*') {
				headX = i;
				headY = j;
				isHeart = true;
				break;
			}
		}
	}

	heartX = headX + 1, heartY = headY;

	for (int i = heartY - 1; i > 0; i--) {
		if (arr[heartX][i] == '_') break;
		leftArm++;
	}

	for (int i = heartY + 1; i <= N; i++) {
		if (arr[heartX][i] == '_') break;
		rightArm++;
	}

	for (int i = heartX+1; i <= N; i++) {
		if (arr[i][heartY] == '_') {
			lastIdx = i; // 마지막 행 (허리가 끝난 인덱스+1)
			break;
		}
		waist++;
	}

	for (int i = lastIdx; i <= N; i++) {
		if (arr[i][heartY - 1] == '_') break;
		leftLeg++;
	}

	for (int i = lastIdx; i <= N; i++) {
		if (arr[i][heartY + 1] == '_') break;
		rightLeg++;
	}

	cout << heartX << " " << heartY << "\n";
	cout << leftArm << " " << rightArm << " " << waist << " " << leftLeg << " " << rightLeg << "\n";

	return 0;
}