import java.util.*;
class Solution {
    public int solution(int[] a) {
        // answer의 초기값을 1로 하는 이유는 a의 양쪽 끝 값은 항상 터트릴 수 있다.
        // 하지만, 아래 로직에서는 양 끝 값은 누락이 된다.
        // 대신, left와 right가 겹치는 부분에서 1개가 중복 집계 되기 때문에, 1로 한다.
        // while의 조건문을 left < right - 1로 걸어서 겹치는 부분의 중복 집계를 막을 수도 있지만
        // a의 길이가 1일 수 있기 때문에, 초기값을 1로하는게 좋아보임.
        int answer = 1;
        int left = 0;
        int right = a.length - 1;
        int leftMinValue = a[left];
        int rightMinValue = a[right];
        while(left < right) {
            // 어차피 left나 right 중 한 쪽이 크다면 그쪽 방향에 있는 최솟값은 a[left]나 a[right]와 같거나 낮기 때문
            // 그래서 right값이 left보다 크다면, 자신의 오른쪽에 있는 값들만 확인하면 된다.
            // left와 right가 중앙으로 오면서 구간별 최솟값이 갱신된다.
            // 그래서 투포인터로 적용할 수 있다.
            if(a[left] < a[right]) {
                if(rightMinValue > a[right-1]) {
                    rightMinValue = a[right-1];
                    answer++;
                }
                right--;
            }else {
                if(leftMinValue > a[left+1]) {
                    leftMinValue = a[left+1];
                    answer++;
                }
                left++;
            }
        }
        return answer;
    }
}