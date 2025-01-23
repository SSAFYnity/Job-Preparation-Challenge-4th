import java.io.*;
import java.util.*;

public class Main {
    static int furthestNode  = 0;
    static int maxDistance = 0;
    static boolean[] visited;
    static WeightedGraph graph;

    static class Edge {
        int target;  // 목표 정점
        int weight;  // 엣지 가중치

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }

    }

    public static class WeightedGraph {
        private int numVertices;  // 정점의 개수
        private List<List<Edge>> adjList;  // 인접리스트

        public WeightedGraph(int numVertices) {
            this.numVertices = numVertices + 1;
            adjList = new ArrayList<>(this.numVertices);
            for (int i = 0; i < this.numVertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int from, int to, int weight) {
            adjList.get(from).add(new Edge(to, weight));
        }

        public List<Edge> getEdges(int vertex) {
            return adjList.get(vertex);
        }

    }

    public static void dfs(int node, int distance) {
        visited[node] = true;
        if (distance > maxDistance) {
            maxDistance = distance;
            furthestNode = node;
        }

        for (Edge edge : graph.getEdges(node)) {
            if (!visited[edge.target]) {
                dfs(edge.target, distance + edge.weight);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        graph = new WeightedGraph(V);

        for (int i = 0; i < V; i++) {
            String[] edgeInfo = br.readLine().split(" ");
            int from = Integer.parseInt(edgeInfo[0]);
            for (int j = 1; j < edgeInfo.length - 1; j += 2) {
                int e = Integer.parseInt(edgeInfo[j]);
                int w = Integer.parseInt(edgeInfo[j + 1]);
                graph.addEdge(from, e,  w);
            }
        }

        // 끝 점 찾기
        visited = new boolean[V + 1];
        dfs(1, 0);

        // 끝 점에서 가장 먼 곳 찾기
        visited = new boolean[V + 1];
        maxDistance = 0;
        dfs(furthestNode, 0);

        System.out.println(maxDistance);
    }
}
