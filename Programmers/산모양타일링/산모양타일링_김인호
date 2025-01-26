class Solution {
    public int solution(int n, int[] tops) {
        int MOD = 10007;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        // 초기값 설정
        dp1[0] = 1;
        dp2[0] = 2 + tops[0];
        
        // DP 계산
        for (int i = 1; i < n; i++) {
            dp1[i] = (dp1[i - 1] + dp2[i - 1]) % MOD;
            dp2[i] = ((dp1[i - 1] * (1 + tops[i])) + 
                      (dp2[i - 1] * (2 + tops[i]))) % MOD;
        }
        
        // 최종 결과 반환
        return (dp1[n - 1] + dp2[n - 1]) % MOD;
    }
}
