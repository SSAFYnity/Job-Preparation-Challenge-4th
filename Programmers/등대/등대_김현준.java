import java.util.*;

class Solution {
    static List<Integer>[] graph;
    static boolean[] v;
    static int answer;
    public int solution(int n, int[][] lighthouse) {
        graph = new ArrayList[n+1];
        v = new boolean[n+1];
        for(int i=1;i<=n;i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] i : lighthouse) {
            graph[i[0]].add(i[1]);
            graph[i[1]].add(i[0]);
        }
        answer = 0;
        dfs(1);
        return answer;
    }

    public static boolean dfs(int node) {

        // 현재 방문 노드 체크
        v[node] = true;
        boolean check = false;
        for(int i : graph[node]) {
            if(!v[i]) {
                if(dfs(i)) {
                    check = true;
                }
            }
        }

        if(check) {
            answer++;
            return false;
        }

        // 부모 노드에서 등대를 켜야 하는지
        return true;
    }
}