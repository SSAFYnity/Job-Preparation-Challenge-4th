#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int solution(int n, vector<int> stations, int w)
{

    int answer = 0, s = 1, range = 2 * w + 1;

    for (int i = 0; i < stations.size(); i++) {
        int e = stations[i] - w - 1;

        if (s <= e) {
            // 범위가 range보다 작은 경우
            if (e - s + 1 <= range) answer++;
            else {
                answer += ((e - s + 1) / range);
                if ((e - s + 1) % range != 0) answer++;
            }
        }


        s = stations[i] + w + 1;
    }

    // 끝부분
    if (s <= n) {
        int len = n - s + 1;
        if (len <= range) answer++;
        else {
            answer += len / range;
            if (len % range != 0) answer++;
        }
    }

    return answer;
}