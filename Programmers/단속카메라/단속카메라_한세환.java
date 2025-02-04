// 프로그래머스 단속카메라
import java.util.*;
import java.io.*;

public class Solution {

    public static int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] r1, int[] r2) {
                return r1[1] - r2[1];
            }
        });

        int pos = Integer.MIN_VALUE;
        for(int[] r : routes){
            if(pos<r[0]){
                pos = r[1];
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int s1 = solution(new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}});
        System.out.println(s1);
    }
}



