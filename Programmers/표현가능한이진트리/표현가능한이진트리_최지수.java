class Solution {
    static int[] digits = new int[6];
    static long[] limits = new long[6];
    static int bin(long target) {
        if (target == 1) return 0;
        for (int i = 1; i < 6; i++) {
            if (target < limits[i]) return i;
        }
        return -1;
    }
    static boolean ok(long num, int p) {
        if (p < 2 || num == 0) return true;
        
        int mid = (p-1) / 2;
        if ((num & (1 << mid)) == 0) return false;
        
        return ok(num >> (mid+1), mid) && ok((num%(1<<mid)) & ~(1 << mid), mid);
    }
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        digits[0] = 1;
        limits[0] = 1;
        for (int i = 1; i < 6; i++) {
            digits[i] = digits[i-1] * 2 + 1;
            limits[i] = (long) Math.pow(2, digits[i]);
        }
        
        int i = 0;
        for (long num : numbers) {
            int idx = bin(num);
            answer[i++] = ok(num, digits[idx]) ? 1 : 0;
        }
        
        return answer;
    }
}