#include <string>
#include <vector>

using namespace std;

int solution(vector<int> a) {
    int answer = 2;

    if (a.size() == 1 || a.size() == 2) return 1;

    int* leftMin = new int[a.size() + 1];
    int* rightMin = new int[a.size() + 1];

    int l = a[0]; // ���� �ּҰ�
    int r = a[a.size() - 1]; // ������ �ּҰ�

    for (int i = 1; i < a.size() - 1; i++) {
        if (l > a[i]) l = a[i];
        leftMin[i] = l;
    }

    for (int i = a.size() - 2; i > 0; i--) {
        if (r > a[i]) r = a[i];
        rightMin[i] = r;
    }

    for (int i = 1; i < a.size() - 1; i++) {
        if (rightMin[i] >= a[i] || leftMin[i] >= a[i]) answer++;
    }

    delete[] leftMin;
    delete[] rightMin;

    return answer;
}