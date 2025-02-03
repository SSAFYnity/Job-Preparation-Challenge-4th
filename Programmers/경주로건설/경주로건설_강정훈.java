import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int R = board.length;
        int C = board[0].length;
        int[][][] costBoard = getCostBoard(R, C);
        RaceErector raceErector = new RaceErector(board, costBoard);
        return raceErector.getMinCost();
    }
    private int[][][] getCostBoard(int R, int C) {
        int[][][] costBoard = new int[R][C][4];
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                Arrays.fill(costBoard[row][col], 123456789);
            }
        }
        return costBoard;
    }
}
class RaceErector {
    int answer = Integer.MAX_VALUE;
    int[][] board;
    int[][][] costBoard;
    final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public RaceErector(int[][] board, int[][][] costBoard) {
        this.board = board;
        this.costBoard = costBoard;
        costBoard[0][0][1] = 0;
        costBoard[0][0][3] = 0;
    }

    public int getMinCost() {
        erectRace();
        return answer;
    }

    private void erectRace() {
        Road start1 = new Road(0, 0, 0, 1);
        Road start2 = new Road(0, 0, 0, 3);
        Queue<Road> q = new ArrayDeque<>();
        q.add(start1);
        q.add(start2);
        while(!q.isEmpty()) {
            Road current = q.poll();
            if (isDestination(current)) {
                answer = Math.min(answer, current.cost);
                continue;
            }
            for (int direction = 0; direction < 4; direction++) {
                int nx = current.x + directions[direction][0];
                int ny = current.y + directions[direction][1];
                if (isValid(nx, ny)) {
                    int erectionCost = getErectionCost(current.direction, direction);
                    int newCost = current.cost + erectionCost;
                    if (newCost < costBoard[nx][ny][direction]) {
                        costBoard[nx][ny][direction] = newCost;
                        q.add(new Road(nx, ny, newCost, direction));
                    }
                }
            }
        }
    }
    private boolean isDestination(Road current) {
        return current.x == board.length-1 && current.y == board[0].length-1;
    }
    private int getErectionCost(int currentDirection, int newDirection) {
        if (currentDirection == newDirection) {
            return 100;
        }
        return 600;
    }
    private boolean isValid(int x, int y) {
        int R = board.length;
        int C = board[0].length;
        return 0 <= x && x < R && 0 <= y && y < C && board[x][y] == 0;
    }
}
class Road {
    int x;
    int y;
    int cost;
    int direction;
    public Road(int x, int y, int cost, int direction) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.direction = direction;
    }
}