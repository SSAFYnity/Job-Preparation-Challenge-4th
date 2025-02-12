import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        for (int T = 0; T < K; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            List<Integer>[] graph = new ArrayList[V + 1];
            for (int index = 0; index < V + 1; index++) {
                graph[index] = new ArrayList<>();
            }
            for (int index = 0; index < E; index++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                graph[start].add(end);
                graph[end].add(start);
            }

            if (isBinaryGraph(graph, V)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean isBinaryGraph(List<Integer>[] graph, int V) {
        int[] groups = new int[V + 1];
        for (int initialNode = 1; initialNode <= V; initialNode++) {
            if (groups[initialNode] != 0) {
                continue;
            }
            Queue<Integer> q = new ArrayDeque<>();
            groups[initialNode] = 1;
            q.add(initialNode);
            while (!q.isEmpty()) {
                int current = q.poll();
                for (int nextIndex : graph[current]) {
                    if (groups[current] == groups[nextIndex]) {
                        return false;
                    }
                    if (groups[nextIndex] == 0) {
                        groups[nextIndex] = groups[current] * (-1);
                        q.add(nextIndex);
                    }
                }
            }
        }
        return true;
    }
}