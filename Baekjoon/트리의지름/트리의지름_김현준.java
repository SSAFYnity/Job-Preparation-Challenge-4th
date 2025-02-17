
import java.util.*;
import java.io.*;

public class 트리의지름_김현준 {
    static int N;

    static class Point {
        int node, val;

        Point(int node, int val) {
            this.node = node;
            this.val = val;
        }
    }

    static int answer = 0;
    static StringTokenizer st;
    static boolean[] v;
    static List<Point>[] list;
    static int maxNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int tree = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int child = Integer.parseInt(st.nextToken());
                if (child == -1) break;
                int val = Integer.parseInt(st.nextToken());
                list[tree].add(new Point(child, val));
            }
        }

        v = new boolean[N + 1]; // 트리 방문 체크
        v[1] = true;
        dfs(1,0);

        v = new boolean[N + 1];
        answer = 0;
        v[maxNode] = true;
//        System.out.println(maxNode);
        dfs(maxNode, 0);

//        for(int i=1;i<list.length;i++) {
//            for(Point p : list[i]) {
//                System.out.print(p.node + " " + p.val + " ");
//            }
//            System.out.println();
//        }
        System.out.println(answer);
    }

    public static void dfs(int node, int sum) {
//        answer = Math.max(answer, sum);
        if(sum > answer) {
            answer = sum;
            maxNode = node;
        }

        // 현재 들어있는 값들 중에 가장 큰 값 순서대로 탐색
        for(Point p : list[node]) {
            if(!v[p.node]) {
                v[p.node] = true;
                dfs(p.node, sum + p.val);
                v[p.node] = false;
            }
        }

    }
}
