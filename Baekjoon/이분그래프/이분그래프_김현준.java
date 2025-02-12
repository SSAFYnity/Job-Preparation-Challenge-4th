
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이분그래프 {
    static int N, V, E;
    static List<Integer>[] graph;
    static int[] color;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 정점
            E = Integer.parseInt(st.nextToken()); // 간선

            graph = new ArrayList[V+1];
            for(int j=1;j<=V;j++) {
                graph[j] = new ArrayList<>();
            }
            color = new int[V+1];
            for(int j=0;j<E;j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);
                graph[e].add(s);
            }

            boolean flag = false;
            // 모든 정점에 대해서 dfs 탐색
            for(int j=1;j<=V;j++) {
                if(color[j] == 0) {
                    color[j] = 1;
                    if(!dfs(j)) { // 안되면 flag 처리
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean dfs(int node) {

        for(int i : graph[node]) {
            // 방문하지 않은 정점
            if(color[i] == 0) {
                color[i] = color[node] * -1;
                if(!dfs(i)) return false;
            }
            // 이미 방문한 곳인데, 현재의 color 와 같으면 조건 성립 X
            else if(color[i] == color[node]) {
                return false;
            }
        }
        return true;
    }
}

