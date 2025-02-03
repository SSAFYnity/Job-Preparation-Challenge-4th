import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        
        int cam = -30001;
        for (int[] i : routes) {
            if (i[0] > cam) {
                answer++;
                cam = i[1];
            }
        }
        
        return answer;
    }
}