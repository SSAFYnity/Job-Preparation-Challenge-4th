class Solution {
    public int solution(int[] a) {
        int answer = 1;
        int l = 0;
        int r = a.length - 1;
        int lMin = a[l];
        int rMin = a[r];
        
        while(l < r){
            //왼쪽이 더 큰 값인경우, 왼쪽을 터트린다.
            if(lMin > rMin){
                l++;
                
                //좌보다 우가 더 큰경우 터트림
                if(a[l] < lMin){
                    answer++;
                }
                
                //아닌경우에는 답 증가 없이 최솟값 갱신만..
                lMin = Math.min(lMin, a[l]);
            }else {
                r--;
                
                //큰경우 터트리기
                if(a[r] < rMin){
                    answer++;
                }
                
                //값 갱신
                rMin = Math.min(rMin, a[r]);
            }
        }
        
        return answer;
    }
}