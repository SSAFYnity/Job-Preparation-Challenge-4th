import java.util.*;
class Solution {
    static int answer = 0;
    static List<Integer>[] adj;
    public int solution(int n, int[][] lighthouse) throws Exception{
        
        adj = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < n-1; i++) {
            adj[lighthouse[i][0]].add(lighthouse[i][1]);
            adj[lighthouse[i][1]].add(lighthouse[i][0]);
        }
        
        dfs(1, 0);
        return answer;
    }
    private int dfs(int node, int prev) {
        if(adj[node].size() == 1 && adj[node].get(0) == prev) {  // 리프노드인 경우, 불을 켜지 않는다
            return 1;
        }
        int net = 0;
        for(int i = 0; i < adj[node].size(); i++) {
            int next = adj[node].get(i);
            if(next == prev)continue;
            net += dfs(next, node);
        }
        if(net == 0)return 1; // 자식노드가 모두 불을 켠 경우, 현재 노드는 불을 킬 필요가 없다 
        answer += 1;
        return 0; // 그 외의 경우 현재 노드는 반드시 불을 켜야 한다
    }
}