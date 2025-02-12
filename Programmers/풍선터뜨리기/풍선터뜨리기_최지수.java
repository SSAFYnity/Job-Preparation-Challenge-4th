class Solution {
    public int solution(int[] a) {
        int inf = Integer.MAX_VALUE;
        // 양쪽에 나보다 작은 게 있으면 실패
        int alen = a.length;
        int answer = alen;
        int fmin = inf, bmin = inf;
        boolean[] check = new boolean[alen];
        for (int i = 0; i < alen; i++) {
            if (fmin < a[i]) {
                if (check[i]) answer--;
                else check[i] = true;
            } else fmin = a[i];
            
            if (bmin < a[alen-1-i]) {
                if (check[alen-1-i]) answer--;
                else check[alen-1-i] = true;
            } else bmin = a[alen-1-i];
        }
        return answer;
    }
}