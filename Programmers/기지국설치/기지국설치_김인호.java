import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        for(int i = 0; i<=stations.length; i++){//한 번 더 나눠야 됨 예로 기지국이 두 개면 3번 돌아야 마지막꺼 체크 
            int gap = 0;
            if(i == 0){//배열 기준 첫 기지국 
                gap = stations[i] - w - 1;
            }else if(i>0&&i<stations.length){//배열 기준 중간 기지국
                gap = (stations[i] - w) - (stations[i-1] + w + 1);
            }else{ //배열 기준 마지막 기지국 i == stations.length
                gap = n - (stations[i-1] + w );
            }
            if(gap>0){ //갭이 있음 설치 
                int newCntCenter = gap/(w*2+1);
                int remain = gap%(w*2+1); //나머지 남으면 땜빵 매꾸기
                if(remain!=0){
                    answer++;
                }
                answer += newCntCenter;
            }
        }
        return answer;
    }
}
