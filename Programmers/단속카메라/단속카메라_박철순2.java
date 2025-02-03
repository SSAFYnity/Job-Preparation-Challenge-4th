import java.util.*; 

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(x->x[1]));
        
        int count = 0; 
        int camera = Integer.MIN_VALUE; 
        for (int[] route : routes) {
            if (route[0] > camera) {
                camera = route[1];
                count++; 
            }
        }
        return count;         
    }
    
}
