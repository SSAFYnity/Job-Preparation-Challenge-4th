class Solution {
    public int solution(int n, int[] stations, int w) {
        int count = 0;  // 추가 기지국 수
        int i = 1;  // 현재 아파트 위치
        int si = 0;  // stations 배열의 인덱스

        while (i <= n) {
            // 현재 아파트 위치가 기지국 범위에 있는 경우 & 기지국 범위 내
            if (si < stations.length && i >= stations[si] - w) {
                i = stations[si] + w + 1;  // 해당 기지국 범위 넣어서 이동
                si++;  // 다음 기지국
            } else {
                count++;
                i += 2 * w + 1;
            }
        }

        return count;
    }
}
