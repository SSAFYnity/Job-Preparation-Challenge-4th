class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            if (idx < stations.length && stations[idx] - w <= i && i <= stations[idx] + w) {
                i = stations[idx++] + w;
                continue;
            }

            answer++;
            i += w*2;
        }

        return answer;
    }
}