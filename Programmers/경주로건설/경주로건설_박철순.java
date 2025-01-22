import java.util.*; 
import java.io.*; 

class Solution {
    int N; 
    // 상좌하우 
    int dx[] = {-1,0,1,0};
    int dy[] = {0,-1,0,1};
    public int solution(int[][] board) {
        N = board.length; 
        int[][][] score = new int[N][N][4];
        
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                Arrays.fill(score[i][j], Integer.MAX_VALUE);                
            }
        }
        Arrays.fill(score[0][0], 0); 
        
        Deque<int[]> deque = new ArrayDeque<>(); 

        deque.add(new int[]{0,0,-1,0});        
        while (!deque.isEmpty()) {
            int[] temp = deque.poll();
            
            int x = temp[0];
            int y = temp[1];
            int beforeDir = temp[2]; 
            int currScore = temp[3]; 
            
            for (int dir=0;dir<4;dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir]; 
                if (nx<0||nx>=N||ny<0||ny>=N || board[nx][ny] == 1) continue;
                
                int nextScore = currScore; 
                if (beforeDir == -1 || beforeDir == dir) {
                    nextScore+=100;
                } else {
                    nextScore+=600; 
                }
                
                if (score[nx][ny][dir] > nextScore) {
                    deque.add(new int[]{nx,ny,dir,nextScore});  
                    score[nx][ny][dir] = nextScore; 
                }
            }
        }
        
        int result = Integer.MAX_VALUE; 
        for (int dir=0;dir<4;dir++) {
            result = Math.min(result, score[N-1][N-1][dir]);
        }
        
        return result;
    }
}
