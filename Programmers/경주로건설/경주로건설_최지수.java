import java.util.*;

class Solution {
    static int n, inf = Integer.MAX_VALUE;
    static int[] dy = {0,0,1,-1}, dx = {1,-1,0,0};
    static int[][][] dij;
    static boolean[][][] visit;
    static class Car implements Comparable<Car> {
        int y, x, c;
        boolean ver;
        public Car(int y, int x, int c, boolean ver) {
            this.y = y;
            this.x = x;
            this.c = c;
            this.ver = ver;
        }
        @Override
        public int compareTo(Car o) {
            return this.c - o.c;
        }
        public String toString() {return "["+y+", "+x+"] "+c+" "+(ver?"세로":"가로");}
    }
    static boolean isOut(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= n;
    }
    public int solution(int[][] board) {
        int answer = inf;
        
        n = board.length;
        dij = new int[2][n][n];
        for (int i = 0; i < n; i++) {Arrays.fill(dij[0][i], inf);}
        for (int i = 0; i < n; i++) {Arrays.fill(dij[1][i], inf);}
        dij[0][0][0] = 0;
        dij[1][0][0] = 0;
        
        visit = new boolean[2][n][n];
        visit[0][0][0] = true;
        visit[1][0][0] = true;
        
        PriorityQueue<Car> que = new PriorityQueue<>();
        if (board[1][0] == 0) {
            que.add(new Car(1,0,100,true));
            dij[0][1][0] = 100;
        }
        if (board[0][1] == 0) {
            que.add(new Car(0,1,100,false));
            dij[1][0][1] = 100;
        }
        
        while (!que.isEmpty()) {
            Car now = que.poll();
            answer = Math.min(dij[0][n-1][n-1], dij[1][n-1][n-1]);
            if (answer <= now.c) continue;
            if (visit[now.ver?0:1][now.y][now.x] ) continue;
            visit[now.ver?0:1][now.y][now.x] = true;
            // System.out.println(now);
            for (int d = 0; d < 4; d++) {
                int y = now.y + dy[d];
                int x = now.x + dx[d];
                if (isOut(y,x) || board[y][x] == 1) continue;
                boolean cheap = (now.ver && d >= 2) || (!now.ver && d < 2);
                int cost = now.c + (cheap ? 100 : 600);
                boolean nextver = !now.ver^cheap;
                if (dij[nextver?0:1][y][x] > cost) { // cheap == true면 그대로, false면 반대로
                    // System.out.println(y +" "+x+" " + cost);
                    que.add(new Car(y,x,cost,nextver));
                    dij[nextver?0:1][y][x] = cost;
                }
            }
        }
        
        return Math.min(dij[0][n-1][n-1], dij[1][n-1][n-1]);
    }
}