import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}; // 상 좌 하 우 순서
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 주사위 면의 상태 - top, up, down, left, right, bottom 순서
        Dice curDice = new Dice(0, 1, 2, 3, 4, 5);
        int[] dice = new int[6]; // 주사위 각 면의 값을 저장할 배열

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            int k = Integer.parseInt(st.nextToken());

            // 굴리기 전 현재 주사위 면의 상태
            int t = curDice.top;
            int u = curDice.up;
            int d = curDice.down;
            int l = curDice.left;
            int r = curDice.right;
            int b = curDice.bottom;

            Dice nextDice;

            // 방향을 상좌하우 0 1 2 3 순서로 만들고, nextDice에 굴린 후의 주사위 면의 상태 저장
            if(k == 1) { // 오른쪽 방향으로 굴릴 경우
                k = 3;
                nextDice = new Dice(l, u, d, b, t, r);
            } else if(k == 2) { // 왼쪽 방향으로 굴릴 경우
                k = 1;
                nextDice = new Dice(r, u, d, t, b, l);
            } else if(k == 3) { // 위쪽 방향으로 굴릴 경우
                k = 0;
                nextDice = new Dice(u, b, t, l, r, d);
            } else { // 아래쪽 방향으로 굴릴 경우
                k = 2;
                nextDice = new Dice(d, t, b, l, r, u);
            }

            // 굴러갈 위치
            int nx = x + dx[k];
            int ny = y + dy[k];

            // 범위를 벗어나는 경우
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            // 굴릴 수 있는 경우, 현재 주사위를 굴린 후의 주사위 상태로 변경
            curDice = nextDice;
            x = nx; // 주사위 위치도 변경
            y = ny;

            // 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
            if(map[nx][ny] == 0) {
                map[nx][ny] = dice[nextDice.bottom];
                System.out.println(dice[nextDice.top]);
            } else { // 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며,
                dice[nextDice.bottom] = map[nx][ny];
                map[nx][ny] = 0; // 칸에 쓰여 있는 수는 0
                System.out.println(dice[nextDice.top]);
            }
        }
    }
}
class Dice {
    int top, up, down, left, right, bottom;
    Dice(int top, int up, int down, int left, int right, int bottom) {
        this.top = top;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }
}
