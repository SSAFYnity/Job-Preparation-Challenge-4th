import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class Solution {

	static List<Map<Integer, Integer>> graph;
	static Set<Integer> gateSet, summitSet;
	static int[] dp, minIntensity;

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

		// 메모리 초과를 해결하기 위해 2차원 배열을 이중 맵으로 교체
		// -> 더 메모리 효율적인 인접리스트 + 맵으로 교체
		graph = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(i, new HashMap<>());
		}

		// 양방향 그래프 정점과 간선 입력
		for (int p = 0; p < paths.length; p++) {
			int i = paths[p][0];
			int j = paths[p][1];
			int w = paths[p][2];

			graph.get(i).put(j, w);
			graph.get(j).put(i, w);
		}

		// 출입구와 산 봉우리 정점 기록
		gateSet = new HashSet<>();
		for (int gate : gates)
			gateSet.add(gate); // 출입구

		summitSet = new HashSet<>();
		for (int summit : summits)
			summitSet.add(summit); // 산봉우리

		// 입력
		// 메서드

		minIntensity = new int[] { n + 1, 10000001 }; // [산봉우리의 번호, intensity의 최솟값]

		// dp[i]: 출입구들로부터 해당 정점 i에 도달하기까지 누적 최소 간선 비용
		dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);

		// 우선순위 큐로 간선 비용이 더 적은 정점을 먼저 탐색
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
			return Integer.compare(a[1], b[1]); // 2번째 원소 오름차순
		});

		// 출발점에서 가능한 모든 봉우리 한꺼번에 탐색 (시간초과 해결)
		for (int gate : gates) {
			dp[gate] = 0;
			pq.add(new int[] { gate, 0 });
			bfs(pq);
		}

		return minIntensity;
	}// solution

	private void bfs(Queue<int[]> pq) {
		while (!pq.isEmpty()) {
			int[] arr = pq.poll();
			int node = arr[0];
			int maxW = arr[1];

			// 큐에 이미 들어간 경로(maxW)를 꺼냈는데, 이 경로가 이젠 비효율적인 경로가 됐을 경우
			if (maxW > dp[node]) {
				continue;
			}

			// 간선으로 연결된 다음 노드들 탐색
			Map<Integer, Integer> nodes = graph.get(node);
			for (Integer nextNode : nodes.keySet()) {
				// 출입구 방문 금지
				if (gateSet.contains(nextNode))
					continue;

				// 간선 가중치가 기록된 역대 최소 가중치보다 크다면,
				if (nodes.get(nextNode) > minIntensity[1])
					continue;

				// 새로운 가중치
				int newW = Math.max(nodes.get(nextNode), maxW);

				// 큐에 넣기 전, 해당 간선이 비효율적인 경로라면 제외하기
				if (dp[nextNode] <= newW)
					continue;

				dp[nextNode] = newW;

				// 산봉우리에 도착했을 시,
				if (summitSet.contains(nextNode)) {
					if (newW < minIntensity[1] || (newW == minIntensity[1] && nextNode < minIntensity[0])) {
						minIntensity[0] = nextNode;
						minIntensity[1] = newW;
					}
					continue;
				} else {
					// 일반 노드라면, 큐에 삽입
					pq.add(new int[] { nextNode, newW });
				}
			} // 다음 노드 탐색
		}
	}// bfs

}// class