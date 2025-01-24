import java.util.*;

class Solution {
    static Map<String, Integer> map = new HashMap<>();
    
    static void delete(String j) {
        int cnt = map.getOrDefault(j, 0);
        if (cnt > 1) map.put(j, cnt-1);
        else if (cnt == 1) map.remove(j);
    }
    
    static void insert(String j) {
        map.put(j, map.getOrDefault(j, 0) + 1);
    }
    
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        for (String j : gems) {
            insert(j);
        }
        int n = map.size();
        int len = gems.length;
        int gap = len;
        
        int s = 0;
        int e = len-1;
        while (s <= e && e < len) {
            if (map.size() == n) {
                if (gap > e - s) {
                    answer[0] = s+1;
                    answer[1] = e+1;
                    gap = e - s;
                }
                delete(gems[e]);
                e--;
            } else {
                delete(gems[s]);
                s++; 
                e = Math.min(e+1, len-1);
                insert(gems[e]);
            }
        }
        
        return answer;
    }
}