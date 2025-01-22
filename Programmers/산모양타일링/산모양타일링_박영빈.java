package SSAFYnity.no6;

// 다른 사람 풀이 해설 참고
class Solution {
	public int solution(int n, int[] tops) {
		int mod = 10007;
		int[][] dp = new int[n][2];

		// 최소 사다리꼴에서 이루어지는 타일 놓기 경우의 수
		// 1. dp[n][0]: 마름모 타일로 끝나지 않는 경우의 수,
		if (tops[0] == 1) { // 위에 삼각형이 있다면,
			dp[0][0] = 3;
		} else {
			dp[0][0] = 2;
		}
		// 2. dp[n][1]: 마름모 타일로 끝나는 경우의 수,
		dp[0][1] = 1;

		// dp 배열 채우기
		for (int i = 1; i < n; i++) {
			int notDiamondEnd = 0;
			int diamondEnd = 0;

			if (tops[i] == 1) {
				notDiamondEnd = 3;
				diamondEnd = 2;
			} else {
				notDiamondEnd = 2;
				diamondEnd = 1;
			}

			dp[i][0] = (dp[i - 1][0] * notDiamondEnd + dp[i - 1][1] * diamondEnd) % mod;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
		} // for문

		return (dp[n - 1][0] + dp[n - 1][1]) % mod;
	}// ()
}// class
