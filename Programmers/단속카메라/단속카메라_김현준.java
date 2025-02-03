import java.util.*;

class Solution {
    static boolean[] v;
    public int solution(int[][] routes) {
        v = new boolean[routes.length];
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        int answer = 0;
        for(int i=0;i<routes.length;i++) {
            if(!v[i]) {
                v[i] = true; answer++;
                int target = routes[i][1];
                for(int j=i+1;j<routes.length;j++) {
                    if(routes[j][0] <= target) {
                        v[j] = true;
                    }
                }
            }
        }
        return answer;
    }
}