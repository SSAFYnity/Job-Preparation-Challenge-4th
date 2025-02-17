import java.util.*;
class Solution {
    Map<String, Integer> gemSortMap = new HashMap<>();
    String[] gems;
    int gemSortCnt;
    public int[] solution(String[] gems) {
        this.gems = gems;
        this.gemSortCnt = getGemSortCnt();
        return getAnswer();
    }
    private int[] getAnswer() {
        int[] answer = {1, 1};
        int minRange = Integer.MAX_VALUE;
        int gemsLength = gems.length;
        int start = 0;
        int currentGemSortCnt = 0;
        for (int end = 0; end < gemsLength; end++) {
            String endGem = gems[end];
            if (gemSortMap.get(endGem) == 0) {
                currentGemSortCnt++;
            }
            gemSortMap.put(endGem, gemSortMap.get(endGem) + 1);
            while(gemSortMap.get(gems[start]) > 1) {
                gemSortMap.put(gems[start], gemSortMap.get(gems[start]) - 1);
                start++;
            }
            if (currentGemSortCnt == gemSortCnt && end - start < minRange) {
                minRange = end - start;
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
        }
        return answer;
    }
    private int getGemSortCnt() {
        int gemSortCnt = 0;
        for (String gem : gems) {
            if (gemSortMap.containsKey(gem)) {
                continue;
            }
            gemSortMap.put(gem, 0);
            gemSortCnt += 1;
        }
        return gemSortCnt;
    }
}