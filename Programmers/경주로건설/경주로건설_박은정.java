import java.util.*;
class Solution {
    static int[][] way = {{-1,0},{0,-1},{0,1},{1,0}};
    static int N;
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        // 단순 BFS에서는 어떤 좌표에 도착하면 방문 여부 만으로 해당 좌표에 다시 방문하지 않는다. 
        // 하지만, 이 문제에서는 같은 좌표라도 비용이 더 낮은 경로로 도달했을 경우 다시 방문해야 한다. 
        N = board.length;
        int[][][] cost = new int[N][N][4]; 
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0,0,-1,0));
        while(!queue.isEmpty()){
            Node now = queue.poll();
            if(now.x == N-1 && now.y == N-1){
                answer = Math.min(answer, now.c);
                continue;
            }
            for(int w = 0; w < 4 ; w++) {
                int nx = now.x + way[w][0];
                int ny = now.y + way[w][1];
                if(!inRange(nx,ny))continue;
                if(board[nx][ny] == 1)continue;
                int nextCost = now.c + (now.w == -1 || now.w == w || now.w  == 3-w ? 100: 600);
                if(cost[nx][ny][w] > nextCost) {
                    cost[nx][ny][w] = nextCost;
                    queue.add(new Node(nx, ny, w, nextCost ));
                }
            }
        }
        return answer;
    }
    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    static class Node {
        int x, y, w, c;
        Node(int x, int y, int w, int c){
            this.x = x;
            this.y = y;
            this.w = w;
            this.c = c;
        }
    }
}