class Solution {
    public int solution(int n, int[] stations, int w) {
        int count = 0; 
        int left = 1; 
        for (int i=0;i<stations.length;i++) {
            if (left < stations[i]-w) {
                count += getCount(left, stations[i]-w-1, w);
            }
            left = stations[i]+w+1; 
        }
        if (stations[stations.length-1]+w < n) {
            count += getCount(stations[stations.length-1]+w+1, n, w); 
        }
        
        return count; 
    }
    
    int getCount (int left, int right, int w) {
        int count = (right-left+1) / (2*w+1); 
        int mod = (right-left+1) % (2*w+1);
        if (mod > 0) count++; 
        return count; 
    }
}
