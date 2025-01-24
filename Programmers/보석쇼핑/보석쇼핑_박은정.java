import java.util.*;
import java.io.*;
class Solution {
    static int KIND_CNT;
    public int[] solution(String[] gems) throws Exception {
        KIND_CNT = getKindCnt(gems);
        
        int minLen = Integer.MAX_VALUE; // 가장 구간의 길이가 작을 때
        int start = 0, end = 0; // 정답 
        
        int sIdx = 0, eIdx = 0;
        Map<String, Integer> isContain = new HashMap<>();
        isContain.put(gems[eIdx], 1);
        
        while(eIdx < gems.length) {
            if(isContain.size() == KIND_CNT) { // all selected
                if(minLen > eIdx - sIdx + 1) {
                    minLen = eIdx - sIdx + 1;
                    start = sIdx; 
                    end = eIdx;
                }
                int prevCnt = isContain.getOrDefault(gems[sIdx], 0);
                if(prevCnt <= 1) {
                    isContain.remove(gems[sIdx]);                
                } else {
                    isContain.put(gems[sIdx], prevCnt - 1);
                }
                sIdx += 1;
            } else {
                eIdx += 1;      
                if(eIdx < gems.length){
                    isContain.put(gems[eIdx], isContain.getOrDefault(gems[eIdx], 0) + 1);
                }
            }
            
            
        }
        int[] answer = new int[]{start + 1, end + 1};
        return answer;
    }
    static int getKindCnt(String[] gems) {
        Set<String> jewels = new HashSet<>();
        for (String gem : gems) {
            jewels.add(gem);
        }
        return jewels.size();
    }
}