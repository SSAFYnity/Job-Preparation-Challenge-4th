class Solution {
    public int solution(int n, int[] tops) {        
        int[] one = new int[n];
        int[] another = new int[n];
        
        one[0] = 1;
        another[0] = 2 + tops[0];
        
        for (int i = 1; i < n; i++) {
            one[i] = (int) ((long) one[i-1] + another[i-1]) % 10007;
            if (tops[i] == 0) {
                another[i] = (int) ((long) one[i-1] + another[i-1] * 2) % 10007;
            } else {
                another[i] = (int) ((long) one[i-1] * 2 + another[i-1] * 3) % 10007;
            }
        }
        
        return (one[n-1] + another[n-1]) % 10007;
    }
}