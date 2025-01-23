import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static Map<Integer, List<Point>> costMap;
    static boolean[] visited;
    static int maxDistance = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        costMap = new HashMap<>();

        for (int i = 1; i <= V; i++) {
            costMap.put(i, new ArrayList<>());
        }

        for (int v = 0; v < V; v++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            while (true) {
                int e = Integer.parseInt(st.nextToken());
                if (e == -1) break;
                int c = Integer.parseInt(st.nextToken());
                costMap.get(s).add(new Point(e, c));
            }
        }

        visited = new boolean[V + 1];
        dfs(1, 0);

        visited = new boolean[V + 1];
        maxDistance = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
    }

    public static void dfs(int now, int cost) {
        if (cost > maxDistance) {
            maxDistance = cost;
            farthestNode = now;
        }

        visited[now] = true;
        for (Point p : costMap.get(now)) {
            if (!visited[p.x]) {
                dfs(p.x, cost + p.y);
            }
        }
    }
}