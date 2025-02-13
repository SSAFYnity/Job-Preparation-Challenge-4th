class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m + 1][n + 1][2];
        executeDP(cityMap, dp, m, n);
        return dp[m][n][0];
    }
    private void executeDP(int[][] cityMap, int[][][] dp, int m, int n) {
        dp[1][1][0] = dp[1][1][1] = 1;
        for (int r = 1; r < m + 1; r++) {
            for (int c = 1; c < n + 1; c++) {
                // 0은 아래 1은 오른쪽
                int condition = cityMap[r - 1][c - 1];
                if (condition == 0) {
                    dp[r][c][0] += (dp[r - 1][c][0] + dp[r][c - 1][1]) % MOD;
                    dp[r][c][1] += (dp[r - 1][c][0] + dp[r][c - 1][1]) % MOD;
                } else if (condition == 1) {
                    dp[r][c][0] = dp[r][c][1] = 0;
                } else {
                    dp[r][c][0] += dp[r - 1][c][0] % MOD;
                    dp[r][c][1] += dp[r][c - 1][1] % MOD;
                }
            }
        }
    }
}