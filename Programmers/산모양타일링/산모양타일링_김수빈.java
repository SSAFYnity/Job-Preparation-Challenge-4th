public class 산모양타일링_김수빈 {
    static class Solution {
        public int solution(int n, int[] tops) {
            int MOD = 10007;
            int[] a = new int[n];  // i - 1 모양이 3번인 경우
            int[] b = new int[n];  // i - 1 모양이 3번이 아닌 경우
            // 인덱스는 0이지만 n이 1이라고 가정하기
            a[0] = 1;
            b[0] = 2 + tops[0];
            // 모듈러 연산을 계산마다 해줘야 초과 에러가 나지 않음
            for (int i = 1; i < n; i++) {
                a[i] = (a[i - 1] + b[i - 1]) % MOD;
                b[i] = (a[i - 1] * (1 + tops[i]) + b[i - 1] * (2 + tops[i])) % MOD;
            }
            int answer = (a[n - 1] + b[n - 1]) % MOD;
            return answer;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[] tops = {1, 1, 0, 1};
        Solution sol = new Solution();
        int result = sol.solution(n, tops);
        System.out.println(result);
    }
}
