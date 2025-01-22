import java.util.*;

class Solution {
    static int N;
    static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};  // 하 우 상 좌
    static final int INF = Integer.MAX_VALUE;
    static final int STRAIGHT_COST = 100;
    static final int CORNER_COST = 500;

    public int solution(int[][] board) {
        N = board.length;  // 부지 크기
        int answer = bfs(board);
        return answer;
    }

    private static int bfs(int[][] board) {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0, 0, 0, -1});  // r, c, cost, direction
        int[][][] cost = new int[N][N][4];  // 4방향 각각 비용
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(cost[i][j], INF);
            }
        }
        cost[0][0][0] = cost[0][0][1] = cost[0][0][2] = cost[0][0][3] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cr = curr[0];  // current row
            int cc = curr[1];  // current col
            int ccost = curr[2];  // current cost
            int cd = curr[3];  // current direction

            for (int i = 0; i < 4; i++) {
                int nr = cr + directions[i][0];
                int nc = cc + directions[i][1];
                // 범위 안이고 벽이 아닌 경우
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] == 0) {
                    int newCost = ccost + STRAIGHT_COST;
                    // 직선이 아니라면 코너 추가 비용
                    if (cd != -1 && cd != i) {
                        newCost += CORNER_COST;
                    }
                    // 4방향으로 가는 것 중 가장 적게 드는 값일 경우 교체
                    if (newCost < cost[nr][nc][i]) {
                        pq.offer(new int[]{nr, nc, newCost, i});
                        cost[nr][nc][i] = newCost;
                    }
                }
            }
        }
        return Math.min(Math.min(cost[N-1][N-1][0], cost[N-1][N-1][1]), Math.min(cost[N-1][N-1][2], cost[N-1][N-1][3]));
    }
}
