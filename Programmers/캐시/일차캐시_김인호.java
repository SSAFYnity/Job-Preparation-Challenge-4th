import java.util.*;

class Solution { //프로그래머스 [1차]캐시 카카오 블라인드
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize==0) return cities.length*5;

        int answer = 0;
        Deque<String> deque = new ArrayDeque<>();

        for (int i = 0; i < cities.length; i++) {
            String tmpName = cities[i].toLowerCase();

            if (deque.contains(tmpName)){//값이 있는 경우
                //캐시 hit
                deque.remove(tmpName);// 그 값을 제거.
                answer+=1;
            }else{//값이 없는 경우
                //캐시 미스
                if (deque.size()==cacheSize){
                    deque.pollFirst();
                }
                answer+=5;
            }
            deque.offerLast(tmpName);
        }
        return answer;
    }
}