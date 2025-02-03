import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int lastCamera = -30001;
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));
        
        //정렬된 순으로 반복
        for(int[] route: routes){
            //만약 설치된 카메라 밖에서 진입한다면...
            if(lastCamera < route[0]){
                answer++;
                lastCamera = route[1];
            }
        }
        
        return answer;
    }
}