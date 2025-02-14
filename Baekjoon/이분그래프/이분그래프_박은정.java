import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int V, E;
	static List<Integer>[] adj;
	static int[] color;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		for (int t = 0; t < K; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());// 1- v번 정점
			E = Integer.parseInt(st.nextToken());
			adj = new ArrayList[V + 1];
			for (int v = 0; v < V + 1; v++) {
				adj[v] = new ArrayList<>();
			}
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()); // 인접한 두 정점의 번호 u, v
				int v = Integer.parseInt(st.nextToken());
				adj[u].add(v);
				adj[v].add(u);
			}
			color = new int[V + 1];
			String ans = "YES";
			for (int v = 1; v <= V; v++) {
				if (color[v] == 0) {
					if (!dfs(v, 1)) {
						ans = "NO";
						break;
					}
				}
			}
			System.out.println(ans);
		}

	}

	static boolean dfs(int v, int c) {
		color[v] = c;

		for (int next : adj[v]) {
			if (color[next] == 0) { // 방문하지 않은 정점
				if (!dfs(next, -c)) {
					return false;
				}
			} else if (color[next] == c) {// 인접한 정점이 같은 색이면 이분 그래프 아님
				return false;
			}

		}
		return true;
	}

}
