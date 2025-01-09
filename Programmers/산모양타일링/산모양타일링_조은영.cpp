#include <string>
#include <vector>
#define MOD 10007
using namespace std;

int solution(int n, vector<int> tops) {
    int answer = 0;
    vector<int>dp(n + 1);

    dp[0] = 1;
    dp[1] = 3 + tops[0];

    for (int i = 2; i <= n; i++) {
        dp[i] = ((dp[i - 1] * (3 + tops[i - 1])) % MOD - dp[i - 2] + MOD) % MOD;

    }

    answer = dp[n] % MOD;

    return answer;
}