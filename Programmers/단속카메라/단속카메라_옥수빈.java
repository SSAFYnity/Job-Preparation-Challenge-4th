import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));
        
        int lastCameraPosition = -30001;
        int cameraCount = 0;

        for (int[] route : routes) {
            if (lastCameraPosition < route[0]) {
                lastCameraPosition = route[1];
                cameraCount++;
            }
        }

        return cameraCount;
    }
}
