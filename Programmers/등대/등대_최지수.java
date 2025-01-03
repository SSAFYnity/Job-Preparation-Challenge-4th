import java.util.*;

class Solution {
    static int answer = 0;
    static ArrayList<Integer>[] arr;
    static boolean[] visit;
    
    static boolean dfs(int node) {
        visit[node] = true;
        
        boolean need = false;
        boolean flag = false;
        for (int i : arr[node]) {
            if (!visit[i]) {
                flag = true;
                need = need | !dfs(i);
            }
        }
        
        if (!flag) return false;
        
        answer += need ? 1 : 0;
        // System.out.println(node + " " + (need ? "on" : "off"));
        return need;
    }
    
    public int solution(int n, int[][] lighthouse) {
        arr = new ArrayList[n+1];
        visit = new boolean[n+1];
        
        for (int i = 1; i < n+1; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int[] l : lighthouse) {
            arr[l[0]].add(l[1]);
            arr[l[1]].add(l[0]);
        }
        
        dfs(1);
        
        return answer;
    }
}