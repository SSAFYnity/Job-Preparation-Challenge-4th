class Solution {
    int MOD = 20170805;
    int n, m;
    int[][][] dp; // 위, 왼
    boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= m;}
    int legacy(int y, int x, boolean up, int[][] map) {
        /*
        물려줄 때 내가 어느쪽 조상인지 확인하고 통행 제한 사항에 따라 물려준다
        */
        if (isOut(y, x) || map[y][x] == 1) return 0;
        int temp = dp[y][x][up?0:1];
        if (map[y][x] == 0) temp += dp[y][x][up?1:0];
        return temp % MOD;
    }
    void find(int y, int x, int[][] map) {   
        /*
        나는 왼쪽에서 받은 거 위쪽에서 받은 거만 따로 기록하는 함수다
        내가 커브 금지 구역이다 -> 후세인이 알아서 받아가라
        조상이 커브 금지 구역이다 -> 조상이 위에 있다 그럼 위쪽 유산만 받아와라
        dp에서 바로 받아오려면 왼쪽 위쪽이 다 완성이 되어있어야한다
        */
        if (map[y][x] == 1) return;
        dp[y][x][0] = legacy(y-1,x,true,map);
        dp[y][x][1] = legacy(y,x-1,false,map);
    }
    
    public int solution(int N, int M, int[][] cityMap) {
        int answer = 0;
        n = N;
        m = M;
        dp = new int[n][m][2];
        
        if (m > 1) {
            if (cityMap[0][1] != 1) dp[0][1][1] = 1;
            for (int i = 2; i < m; i++) {
                find(0,i,cityMap);
            }
        }
        if (n > 1) {
            if (cityMap[1][0] != 1) dp[1][0][0] = 1;
            for (int i = 1; i < m; i++) {
                find(1,i,cityMap);
            }
        }
        
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < m; j++) {
                find(i,j,cityMap);
            }
        }
        
        return answer = (dp[n-1][m-1][0] + dp[n-1][m-1][1]) % MOD;
    }
}