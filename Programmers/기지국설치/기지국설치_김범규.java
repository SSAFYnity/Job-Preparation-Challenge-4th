class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int cover = 1;
        int range = 2 * w + 1;

        //풀이 방법 
        //1. 배열에 직접 저장하지 말고 빈 부분만 체크하여 2w + 1을 이용하기
        for(int station: stations){
            int left = station - w;
            int right = station + w;
            
            //기지국이 담당하는 부분보다 cover가 작을 경우
            if(cover < left){
                int gap = left - cover;
                answer += (int)(Math.ceil((double)gap / range));
            }
            
            //다음 부분으로 이동
            cover = right + 1;
        }
        
        //배열 순환 후, 아직 기지국이 설치되지 않은 곳이 남아있는 경우
        if(cover <= n){
            int gap = n - cover + 1;
            answer += (int)(Math.ceil((double)gap / range));
        }

        return answer;
    }
}