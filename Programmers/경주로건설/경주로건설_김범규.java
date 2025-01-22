import java.util.*;

class Solution {
    int n;
    //상, 하, 좌, 우
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0, 0, -1 ,1};
    boolean[][][] visited;
    int min = Integer.MAX_VALUE;
    
    public int solution(int[][] board) {
        //N * N 배열이므로
        n = board.length;
        visited = new boolean[n][n][4];
        
        return bfs(board);
    }
    
    public int bfs(int[][] board){
        int r = 0;
        int c = 0;
        int dir = -1;
        int cost = 0;
        
        Deque<Road> queue = new ArrayDeque<>();
        queue.offer(new Road(r, c, dir, cost));
        
        while(!queue.isEmpty()){
            Road road = queue.poll();
            
            if(road.r == n - 1 && road.c == n - 1){
                min = Math.min(min, road.cost);
            }
            
            for(int d = 0 ; d < 4; d++){
                int nr = road.r + dr[d];
                int nc = road.c + dc[d];
                
                //벽이거나 넘어간 경우
                if(nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] == 1){
                    continue;
                }
                
                    int newCost = road.cost;
                    //방향이 같거나 초기인경우
                    if(road.dir == -1 || road.dir == d){
                        newCost += 100;
                    }
                    else {
                        newCost += 600;
                    }
                    
                    //방문하지 않았거나, 이미 board배열에 저장된 값이 더 큰 경우에만 접근
                    if(!visited[nr][nc][d] || board[nr][nc] >= newCost){
                        queue.offer(new Road(nr, nc, d, newCost));
                        visited[nr][nc][d] = true;
                        board[nr][nc] = newCost;
                    }
                
            }
        }
        
        return min;
    }
    
    public class Road{
        int r;
        int c;
        int dir;
        int cost;
        
        public Road(int r, int c, int dir, int cost){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }

}