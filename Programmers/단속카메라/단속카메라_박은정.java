import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(x-> x[1])); // 정렬
        
        int cnt = 0;
        int lastCamera = -30001;
        for(int i = 0; i < routes.length;  i++) {
            if(lastCamera >= routes[i][0] && lastCamera <= routes[i][1]){
                continue;
            }
            lastCamera = routes[i][1];
            cnt +=1;
        }
        
        return cnt;
    }
}