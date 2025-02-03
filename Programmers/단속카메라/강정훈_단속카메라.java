import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (o1, o2) -> {return o1[1] - o2[1];});
        int before = routes[0][0] - 1;
        for (int[] route : routes) {
            if(before < route[0]) {
                before = route[1];
                answer++;
                continue;
            }
        }
        return answer;
    }
}