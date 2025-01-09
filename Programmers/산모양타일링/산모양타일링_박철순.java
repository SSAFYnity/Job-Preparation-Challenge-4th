class Solution {
    final static int MOD = 10007;
    public int solution(int n, int[] tops) { 
        int[] dpA = new int[n*2+1]; 
        int[] dpB = new int[n*2+1]; 
        
        if (tops[0] == 1) {
            dpA[0] = 1; 
            dpB[0] = 3; 
        } else {
            dpA[0] = 1;
            dpB[0] = 2; 
        }
        
        for(int i=1;i<n;i++){
            if (tops[i] == 1) { // 위쪽 삼각형 O 
                dpA[i] = (dpA[i-1] + dpB[i-1]) % MOD; 
                dpB[i] = (dpA[i-1]*2 + dpB[i-1]*3) % MOD; 
            } else { // 위쪽 삼각형 X 
                dpA[i] = (dpA[i-1] + dpB[i-1]) % MOD;
                dpB[i] = (dpA[i-1] + dpB[i-1]*2) % MOD;
            }
        }
        
        return (dpA[n-1] + dpB[n-1]) % MOD;        
    }
}
