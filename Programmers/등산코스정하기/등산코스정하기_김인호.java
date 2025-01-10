import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {


        // 그래프 초기화
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] path : paths) {
            int a = path[0], b = path[1], c = path[2];
            graph.get(a).add(new int[]{c, b});
            graph.get(b).add(new int[]{c, a});
        }

        // DP 배열 초기화
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 봉우리 집합
        Set<Integer> summitsSet = new HashSet<>();
        for (int summit : summits) {
            summitsSet.add(summit);
        }

        // 우선순위 큐 초기화
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int gate : gates) {
            dp[gate] = 0;
            pq.offer(new int[]{0, gate});
        }

        // 경로 탐색
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0], node = current[1];

            if (dp[node] < cost) continue;

            for (int[] edge : graph.get(node)) {
                int nextCost = edge[0], nextNode = edge[1];
                int newCost = Math.max(cost, nextCost);

                if (dp[nextNode] > newCost) {
                    dp[nextNode] = newCost;
                    if (!summitsSet.contains(nextNode)) {
                        pq.offer(new int[]{newCost, nextNode});
                    }
                }
            }
        }

        // 최소 비용 봉우리 탐색
        Arrays.sort(summits);
        int minCost = Integer.MAX_VALUE, target = 0;
        for (int summit : summits) {
            if (dp[summit] < minCost) {
                minCost = dp[summit];
                target = summit;
            }
        }

        return new int[]{target, minCost};
    }
}
