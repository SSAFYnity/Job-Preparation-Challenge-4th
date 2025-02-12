import java.util.*; 
import java.io.*; 

class Solution {
    public int solution(int[] a) {
        int N = a.length;         
        if (N == 1) {
            return 1; 
        } else if (N == 2) {
            return 2; 
        }
        
        int minLeft = Integer.MAX_VALUE; 
        int[] minRight = new int[N];
        Arrays.fill(minRight, Integer.MAX_VALUE); 
        minRight[N-1] = a[N-1]; 
        
        for (int i=N-2;i>0;i--) {
            minRight[i] = Math.min(minRight[i+1], a[i]);
        }
        
        int count = 0;
        for (int i=0;i<N;i++) {
            if (minLeft < a[i] && minRight[i] < a[i]) count++; 
            minLeft = Math.min(minLeft, a[i]);
        }
        
        return N-count; 
    }
}
