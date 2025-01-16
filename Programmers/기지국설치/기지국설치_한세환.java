package programmers;

public class Solution_기지국설치 {

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int sIdx = 0;
        int posi = 1;

        while (posi <= n) {

            if (sIdx < stations.length && stations[sIdx] - w <= posi) {
                posi = stations[sIdx] + w + 1;
                sIdx++;
            } else {
                answer++;
                posi += 2 * w + 1;
            }
        }

        return answer;
    }


    public static void main(String[] args) {

        int x = solution(16, new int[]{9}, 2);
        int y = solution(11, new int[]{4, 11}, 1);

        System.out.println("answer : " + x);
    }

}
