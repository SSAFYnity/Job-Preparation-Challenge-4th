import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int V; // 트리의 정점의 개수
	static List<Node>[] adj;

	static boolean[] visited;
	static int max;
	static int end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		V = Integer.parseInt(br.readLine());

		adj = new ArrayList[V + 1];
		for (int v = 0; v <= V; v++) {
			adj[v] = new ArrayList<>();
		}

		for (int v = 1; v <= V; v++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			while (true) {
				int to = Integer.parseInt(st.nextToken());
				if (to == -1) {
					break;
				}
				int dist = Integer.parseInt(st.nextToken());
				adj[from].add(new Node(to, dist));
			}
		}
		end = 0;
		max = 0;
		visited = new boolean[V + 1];
		dfs(1, 0); // 트리의 끝을 찾기 위한 dfs

		max = 0;
		visited = new boolean[V + 1];
		dfs(end, 0);

		System.out.println(max);
	}

	static void dfs(int x, int cost) {
		visited[x] = true;
		if (cost > max) {
			max = cost;
			end = x;
		}
		for (Node next : adj[x]) {
			if (!visited[next.to]) {
				dfs(next.to, cost + next.cost);
			}
		}
	}

	static class Node {
		int to, cost;

		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

}