class Solution {
    static boolean[] v;
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int idx = 0;
        int cover = 1 + 2 * w;
        int position = 1;
        while(position <= n) {
            // 1. 현재 위치에서 전력이 닿으면 범위 밖으로
            if(idx < stations.length &&
                    position >= stations[idx] - w) {
                position = stations[idx] + w + 1;
                idx++;
            }
            // 2. 안 닿으면, 현재 위치에 기지국 설치 후 범위 밖으로 이동
            else {
                answer++;
                position += cover;
            }
        }
        return answer;
    }
}