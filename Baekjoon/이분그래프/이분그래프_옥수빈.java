import java.util.*;

public class Main {

    static List<Integer>[] connect;
    static int[] group;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        for (int i = 0; i < K; i++) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            connect = new List[V + 1];
            group = new int[V + 1];
            for (int k = 0; k <= V; k++) {
                connect[k] = new ArrayList<>();
            }
            for (int j = 0; j < E; j++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                connect[start].add(end);
                connect[end].add(start);
            }
            boolean answer = true;
            for (int j = 1; j <= V; j++) {
                if (group[j] == 0) {
                    if (!BFS(j)) {
                        answer = false;
                        break;
                    }
                }
            }
            if (answer)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    static boolean BFS(int n) {
        boolean[] visited = new boolean[connect.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        group[n] = 1;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            visited[x] = true;
            int g = group[x];
            for (int c : connect[x]) {
                if (group[c] != 0 && group[c] == g)
                    return false;
                group[c] = -g;
                if (!visited[c])
                    queue.add(c);
            }
        }
        return true;
    }
}