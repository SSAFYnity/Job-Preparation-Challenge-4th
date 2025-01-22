import java.util.*;

class Solution {
      // 방향 상수 (상, 우, 하, 좌)
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] cost = new int[n][n][4]; // 각 방향별 비용 저장
        for (int[][] c : cost) {
            for (int[] row : c) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        for (int d = 0; d < 4; d++) {
            cost[0][0][d] = 0;
        }
        pq.offer(new Node(0, 0, -1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || board[ny][nx] == 1) continue;

                int nextCost = cur.cost + (cur.dir == -1 || cur.dir == d ? 100 : 600);
                if (nextCost < cost[ny][nx][d]) {
                    cost[ny][nx][d] = nextCost;
                    pq.offer(new Node(nx, ny, d, nextCost));
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            minCost = Math.min(minCost, cost[n - 1][n - 1][d]);
        }
        return minCost;
    }

    class Node {
        int x, y, dir, cost;

        Node(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
}