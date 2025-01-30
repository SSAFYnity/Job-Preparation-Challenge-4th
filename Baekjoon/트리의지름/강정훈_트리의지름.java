import java.io.*;
import java.util.*;

class Main {
    static List<Node>[] tree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            while (true) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) {
                    break;
                }
                int distance = Integer.parseInt(st.nextToken());
                tree[start].add(new Node(end, distance));
            }
        }

        Node firstFarNode = findFarNode(1);
        Node secondFarNode = findFarNode(firstFarNode.end);

        System.out.println(secondFarNode.distance);
    }

    private static Node findFarNode(int startIndex) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(new Node(startIndex, 0));
        visited[startIndex] = true;

        int maxDistance = 0;
        int farIndex = startIndex;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node next : tree[current.end]) {
                if (!visited[next.end]) {
                    int newDistance = current.distance + next.distance;
                    queue.add(new Node(next.end, newDistance));
                    visited[next.end] = true;

                    if (newDistance > maxDistance) {
                        maxDistance = newDistance;
                        farIndex = next.end;
                    }
                }
            }
        }

        return new Node(farIndex, maxDistance);
    }
}

class Node {
    int end;
    int distance;

    public Node(int end, int distance) {
        this.end = end;
        this.distance = distance;
    }
}