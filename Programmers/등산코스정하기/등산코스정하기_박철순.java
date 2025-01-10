import java.util.*;
import java.io.*; 

class Edge implements Comparable<Edge> {
    int from; 
    int to; 
    int weight; 
    
    Edge(int from, int to, int weight) {
        this.from = from; 
        this.to = to; 
        this.weight = weight; 
    }
    
    public int compareTo(Edge o) {
        if (this.weight == o.weight) {
            return this.to - o.to; 
        }
        return this.weight - o.weight; 
    }
}

class Solution {
    static List<Edge>[] graph;  // 그래프를 표현하는 인접 리스트
    static Set<Integer> start;  // 출발점(출입구) 집합
    static Set<Integer> end;    // 도착점(산봉우리) 집합
    static int[] dist;          // 각 지점까지의 최소 intensity를 저장하는 배열
    static int minSummit;       // 최소 intensity를 가진 산봉우리 번호
    static int minIntensity;    // 최소 intensity 값
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new List[n+1];
        start = new HashSet<>();
        end = new HashSet<>();
        dist = new int[n+1];
        minSummit = Integer.MAX_VALUE; 
        minIntensity = Integer.MAX_VALUE; 
            
        for (int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for (int gate : gates) start.add(gate);
        for (int summit : summits) end.add(summit);
        
        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int weight = path[2];
            
            if (!start.contains(from) && !end.contains(to)) {
                graph[to].add(new Edge(to, from, weight));
            }
            if (!start.contains(to) && !end.contains(from)) {
                graph[from].add(new Edge(from, to, weight));
            }
        }

        findRoute(); 
        
        return new int[]{minSummit, minIntensity};
        
    }
    
    private static void findRoute() {
        Arrays.fill(dist, Integer.MAX_VALUE);   // 미방문 지점 초기화 
        
        PriorityQueue<Edge> pq = new PriorityQueue<>(); 
        for (int startPoint : start) {
            // 모든 출발 지점에서 시작하는 Edge PQ에 추가 
            for (Edge edge : graph[startPoint]) {
                pq.add(edge); 
            }
        }
        
        int max = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
            
            // 현재 Edge의 가중치가 intensity보다 크면 중지
            if (cur.weight > max) break;
			dist[cur.to] = cur.weight;

            // 산봉우리 도착  
			if (end.contains(cur.to)) {
				for (int value : dist) {
					if (value>max && value != Integer.MAX_VALUE || max == Integer.MAX_VALUE)
                        max = value;
				}
				minSummit = Math.min(minSummit, cur.to);
                minIntensity = max;
			}

            // 현재에서 이어지는 모든 Edge 중 아직 방문하지 않은 곳으로 가는 Edge 추가 
			for (int i = 0; i < graph[cur.to].size(); ++i) {
				Edge next = graph[cur.to].get(i);

				if (dist[next.to] == Integer.MAX_VALUE) {
					pq.add(next);
				}
			}
		}
    }
}
