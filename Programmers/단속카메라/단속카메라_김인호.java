import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes,(o1,o2)->o1[1]-o2[1]);
        int min = Integer.MIN_VALUE;
        for(int []route: routes ){
            if(route[0]>min){
                min = route[1];
                answer++;
            }
        }
        return answer;
    }
}
