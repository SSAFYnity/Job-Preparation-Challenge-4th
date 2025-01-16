class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int apt = 1;
        for(int i = 0; i < stations.length; i++) {
            int start = stations[i] - w;
            int end = stations[i] + w;
            if(apt < start) {
                answer += build(start-apt, w);
            }
            apt = end + 1; // 커버되지 않은 아파트 
        }
        if(apt <= n) {
            answer += build(n-apt+1, w); // n까지의 모든 아파트를 포함해야 하므로 + 1
        }
        return answer;
    }
    static int build(int dist, int w) {
        return dist / (2 * w + 1) + (dist % (2 * w + 1) > 0 ? 1 : 0);
    }
}