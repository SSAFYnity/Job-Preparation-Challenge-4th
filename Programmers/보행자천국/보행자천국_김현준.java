class Solution {
    static int [][][] dp;
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        dp = new int[m+1][n+1][2];
        dp[0][0][0] = 1; // 0은 좌측, 1은 위에서 내려옴
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(cityMap[i][j] == 0) {
                    dp[i][j+1][0] = (dp[i][j][0] + dp[i][j][1]) % MOD;
                    dp[i+1][j][1] = (dp[i][j][0] + dp[i][j][1]) % MOD;
                } else if(cityMap[i][j] == 2) {
                    dp[i][j+1][0] = (dp[i][j+1][0] + dp[i][j][0]) % MOD;
                    dp[i+1][j][1] = (dp[i+1][j][1] + dp[i][j][1]) % MOD;
                }
            }
        }
        return (dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD;

    }
}