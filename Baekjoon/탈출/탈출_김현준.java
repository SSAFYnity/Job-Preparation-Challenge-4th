
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 탈출 {
    static class Point {
        int r, c, move;
        Point(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }
    }
    static char[][] map;
    static int N, M;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Queue<Point> water = new ArrayDeque<>();
    static Queue<Point> moves = new ArrayDeque<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int r = 0, c = 0;
        map = new char[N][M];
        for(int i=0;i<N;i++) {
            String line = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == '*') water.offer(new Point(i, j, 0)); // 물
                else if(map[i][j] == 'S') moves.offer(new Point(i, j, 0)); // 고슴도치 위치
                else if(map[i][j] == 'D') {
                    r = i; c = j;
                }
            }
        }

        // 불필요한 게산할 필요 없이, 물 먼저 채우고 고슴도치 이동
        int cnt = 0;
        while(!moves.isEmpty()) {
            int size = water.size();
            for(int i=0;i<size;i++) {
                Point w = water.poll();
                for(int k=0;k<4;k++) {
                    int wr = w.r + dr[k];
                    int wc = w.c + dc[k];
                    if(inRange(wr, wc) && map[wr][wc] == '.') {
                        map[wr][wc] = '*';
                        water.offer(new Point(wr, wc, 0));
                    }
                }
            }

            int moveSize = moves.size();
            for(int i=0;i<moveSize;i++) {
                Point p = moves.poll();
                for(int k=0;k<4;k++) {
                    int nr = p.r + dr[k];
                    int nc = p.c + dc[k];
                    if(inRange(nr, nc) && map[nr][nc] == '.') {
                        map[nr][nc] = 'S';
                        moves.offer(new Point(nr, nc, p.move + 1));
                    } else if(inRange(nr, nc) && map[nr][nc] == 'D') {
                        map[nr][nc] = 'S';
                        cnt = p.move + 1;
                        break;
                    }
                }
            }
        }

        if(map[r][c] == 'D') {
            System.out.println("KAKTUS");
        } else {
            System.out.println(cnt);
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
