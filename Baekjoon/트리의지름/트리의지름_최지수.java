import java.util.*;
import java.io.*;

public class Main {
    static int n, inf = Integer.MAX_VALUE;
    static int maxiNode, maxiDis;
    static List<Way>[] tree;
    static boolean[] visit;

    static class Way {
        int arr, dis;
        public Way(int arr, int dis) {
            this.arr = arr;
            this.dis = dis;
        }
    }

    static void dfs(int node, int dis) {
        if (dis > maxiDis) {
            maxiNode = node;
            maxiDis = dis;
        }

        for (Way way : tree[node]) {
            if (visit[way.arr]) continue;
            visit[way.arr] = true;
            dfs(way.arr, dis+way.dis);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n+1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int dep = Integer.parseInt(st.nextToken());
            int arr = Integer.parseInt(st.nextToken());
            tree[dep] = new ArrayList<>();
            while (arr != -1) {
                tree[dep].add(new Way(arr, Integer.parseInt(st.nextToken())));
                arr = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n+1];
        visit[1] = true;
        dfs(1, 0);

        Arrays.fill(visit, false);
        visit[maxiNode] = true;
        dfs(maxiNode, 0);

        System.out.println(maxiDis);
    }
}

