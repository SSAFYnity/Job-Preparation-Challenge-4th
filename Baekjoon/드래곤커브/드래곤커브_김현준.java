import java.util.*;
import java.io.*;

public class Main {
    static boolean[][] map = new boolean[101][101]; // 지도는 100x100
    static int N, X, Y, D, G;
    static int[] dr = {0, -1, 0, 1}; // 우 상 좌 하
    static int[] dc = {1, 0, -1, 0};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int answer = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken()); // x축
            Y = Integer.parseInt(st.nextToken()); // y축
            D = Integer.parseInt(st.nextToken()); // 시작 방향
            G = Integer.parseInt(st.nextToken()); // 세대

            drawDragonCurve();
        }
        answer = checkSquare();
        System.out.println(answer);
    }

    public static void drawDragonCurve() {
        List<Integer> directionList = new ArrayList<>();
        directionList.add(D); // 시작 방향

        for(int i=0;i<G;i++) {
            for(int j=directionList.size()-1;j>=0;j--) {
                directionList.add((directionList.get(j) + 1) % 4); // 시계방향 90도 회전
            }
        }

        map[Y][X] = true;

        int r = Y;
        int c = X;
        for(int dir : directionList) {
            r += dr[dir];
            c += dc[dir];

            // 현재 좌표가 지도 안에 있으면 true 처리
            if(r >= 0 && r < 101 && c >= 0 && c < 101) {
                map[r][c] = true;
            }
        }
    }

    public static int checkSquare() {
        int cnt = 0;
        for(int i=0;i<100;i++) {
            for(int j=0;j<100;j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
