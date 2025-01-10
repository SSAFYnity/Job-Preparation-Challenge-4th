import java.util.*;

class Solution {
    static int inf = Integer.MAX_VALUE;
    static int intensity = inf;
    static TreeSet<Integer> gates = new TreeSet<>();
    static TreeSet<Integer> tops = new TreeSet<>();
    static List<Route>[] paths;
    static int[] answer = {inf, inf};
    static int[] dij;
    static boolean[] visit;
    class Route implements Comparable<Route> {
        int to, time;
        public Route(int to, int time) {
            this.to = to;
            this.time = time;
        }
        public int compareTo(Route o) {
            return this.time - o.time;
        }
        public String toString() {return "[" + to + ", " + time + "]";}
    }
    public int[] solution(int n, int[][] Paths, int[] Gates, int[] summits) {
        
        for (int i : Gates) {gates.add(i);}
        for (int i : summits) {tops.add(i);}
        visit = new boolean[n+1];
        dij = new int[n+1];

        paths = new ArrayList[n+1];
        for (int i = 1; i < n+1; i++) {
            paths[i] = new ArrayList<>();
        }
        
        for (int[] path : Paths) {
            for (int i = 0; i < 2; i++) {
                if (!gates.contains(path[i]) && !tops.contains(path[(i+1)%2])) {
                    paths[path[(i+1)%2]].add(new Route(path[i], path[2]));
                }
            }
        }
        for (int i = 1; i < n+1; i++) {
            Collections.sort(paths[i]);
        }
        
        PriorityQueue<Route> que = new PriorityQueue<>();
        for (int g : gates) {
            Arrays.fill(dij, inf);
                
            visit = new boolean[n+1];
            que.clear();
            que.add(new Route(g, 0));
            while (!que.isEmpty()) {
                Route now = que.poll();
                if (visit[now.to] || tops.contains(now.to)) continue;
                visit[now.to] = true;
                for (Route next : paths[now.to]) {
                    int newtime = Math.max(now.time, next.time);
                    if (answer[1] < newtime) continue;
                    if (dij[next.to] > newtime) {
                        if (!visit[next.to]) que.add(new Route(next.to, newtime));
                        dij[next.to] = newtime;
                    }
                }
            }
            
            for (int t : tops) {
                if (answer[1] > dij[t]) {
                    answer[0] = t;
                    answer[1] = dij[t];
                } else if (answer[1] == dij[t] && answer[0] > t) {answer[0] = t;}
            }
            
                
        }
        return answer;
    }
}