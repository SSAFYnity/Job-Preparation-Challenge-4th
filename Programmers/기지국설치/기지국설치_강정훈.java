class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int currentCoverageStart = 1;
        for (int station : stations) {
            int stationCoverageStart = station - w;
            if (isOutsideCoverage(currentCoverageStart, stationCoverageStart)) {
                answer += calculateStationsToBuild(currentCoverageStart, stationCoverageStart, w);
            }
            currentCoverageStart = station + w + 1;
        }
        // 끝 범위에서 마지막 기지국의 전파 범위 체크
        if (isOutsideCoverage(currentCoverageStart, n + 1)) {
            answer += calculateStationsToBuild(currentCoverageStart, n + 1, w);
        }
        return answer;
    }
    // end에서 start까지 전파가 닿지 않는 범위를 계산 후 지어야 하는 기지국 개수 반환하는 메서드
    private int calculateStationsToBuild(int start, int end, int w) {
        int uncoveredRange = end - start;
        int stationCoverage = w * 2 + 1;
        return (uncoveredRange - 1) / stationCoverage + 1;
    }
    // 현재 station에서 기지국을 설치해야하는지 체크하는 메서드
    private boolean isOutsideCoverage(int currentStart, int nextStart) {
        return currentStart < nextStart;
    }
}