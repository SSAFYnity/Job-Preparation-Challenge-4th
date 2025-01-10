package SSAFYnity.no7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

	static Map<Integer, Integer> gatesMap, summitsMap;
	static Map<Integer, Map<Integer, Integer>> graph;
	static int[] max;

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

		// 메모리 초과를 해결하기 위해 2차원 배열을 이중 맵으로 교체
		graph = new HashMap<>();
		for (int r = 0; r < paths.length; r++) {
			int i = paths[r][0];
			int j = paths[r][1];
			int w = paths[r][2];

			if (!graph.containsKey(i)) {
				Map<Integer, Integer> nodes = new HashMap<>();
				graph.put(i, nodes);
			}
			if (!graph.containsKey(j)) {
				Map<Integer, Integer> nodes = new HashMap<>();
				graph.put(j, nodes);
			}
			graph.get(i).put(j, w);
			graph.get(j).put(i, w);
		}

		gatesMap = new HashMap<>();
		summitsMap = new HashMap<>();
		for (int g = 0; g < gates.length; g++) {
			gatesMap.put(gates[g], 0); // 출입구
		}
		for (int s = 0; s < summits.length; s++) {
			summitsMap.put(summits[s], 1); // 산봉우리
		}

		max = new int[] { n + 1, 10000001 };
		boolean[] visited;
		for (int g = 0; g < gates.length; g++) {
			// 출발점에서 가능한 모든 봉우리 한꺼번에 탐색 (시간초과 해결)
//			for (int s = 0; s < summits.length; s++) {
			visited = new boolean[n + 1];
			visited[gates[g]] = true;
			PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
				if (a[1] != b[1]) {
					return Integer.compare(a[1], b[1]); // 2번째 원소 오름차순
				}
				return Integer.compare(a[2], b[2]); // 3번째 원소 오름차순
			});
			queue.add(new int[] { gates[g], 0, -1 });
			bfs(queue, visited);
//			}
		}

		return max;
	}// solution

	private void bfs(Queue<int[]> queue, boolean[] visited) {
		// rc
		while (!queue.isEmpty()) {
			int[] arr = queue.poll();
			int node = arr[0];
			int w = arr[1];
			int maxW = arr[2];
			
			if (maxW > max[1]) {
				continue;
			}

			// bc
			if (summitsMap.containsKey(node)) {
				if (maxW == max[1] && node < max[0]) {
					max[0] = node;
					continue;
				}
				if (maxW < max[1]) {
					max[0] = node;
					max[1] = maxW;
					continue;
				}
				continue;
			}

			Map<Integer, Integer> map = graph.get(node);
			for (Integer key : map.keySet()) {
				// 다른 출입구 방문 금지
				if (gatesMap.containsKey(key)) {
					continue;
				}
				if (!visited[key]) {
					if (map.get(key) > max[1]) {
						continue;
					}
					// 먼저 도착한 노드가 봉우리를 방문 체크하면, 다른 경로 노드에서 봉우리에 도달할 수 없음
					if (!summitsMap.containsKey(key)) {
						visited[key] = true;
					}
					int newW = Math.max(map.get(key), maxW);
					if (newW > max[1]) {
						continue;
					}
					queue.add(new int[] { key, map.get(key), newW });
				}
			}
		}
	}// bfs
}// class
