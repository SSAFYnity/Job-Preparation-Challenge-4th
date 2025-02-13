import java.util.*;

class Solution {
    static final int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m][n];  // 경로 수 저장 DP 배열
        int[][] right = new int[m][n];  // 오른쪽으로 진행 가능한 경로 수
        int[][] down = new int[m][n];   // 아래쪽으로 진행 가능한 경로 수

        dp[0][0] = 1;  // 출발점

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) continue; 

                if (cityMap[r][c] == 1) {
                    dp[r][c] = 0; 
                    right[r][c] = 0;
                    down[r][c] = 0;
                } else {
                    // 위쪽에서 오는 경우
                    if (r > 0) {
                        if (cityMap[r - 1][c] == 2) {
                            down[r][c] = down[r - 1][c]; // 방향 유지
                        } else {
                            down[r][c] = dp[r - 1][c]; // 위쪽 값 그대로 가져옴
                        }
                    }

                    // 왼쪽에서 오는 경우
                    if (c > 0) {
                        if (cityMap[r][c - 1] == 2) {
                            right[r][c] = right[r][c - 1]; // 방향 유지
                        } else {
                            right[r][c] = dp[r][c - 1]; // 왼쪽 값 그대로 가져옴
                        }
                    }

                    // 최종적으로 가능한 경로 합산
                    dp[r][c] = (down[r][c] + right[r][c]) % MOD;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
