import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] a) {
        int size = a.length;
        int[] minLeftArr = new int[size];
        int[] minRightArr = new int[size];
        
        int min = 1000000001;
        for(int i = 0; i < size; i++){
            min = Math.min(min, a[i]);
            minLeftArr[i] = min;
        }
        
        min = 1000000001;
        for(int i = size-1; i >= 0; i--){
            min = Math.min(a[i], min);
            minRightArr[i] = min;
        }
        
        if(size < 3)
            return size;

        int answer = 2;
        for(int i = 1; i < size-1; i++){
            if(minLeftArr[i-1] < a[i] && a[i] > minRightArr[i+1])
                continue;
            answer++;
        }
        return answer;
    }
}