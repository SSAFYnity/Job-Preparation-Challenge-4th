package Baekjoon.주사위굴리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김범규_주사위굴리기 {
    // 가로, 세로 주사위 저장하기.
    static int[] command;
    static int[][] field, dice;
    static int N, M, x, y, K, high;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 후 맞는 크기의 배열 생성
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        dice = new int[4][3];
        command = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            command[i] = Integer.parseInt(st.nextToken());
            moveDice(command[i]);
        }

    }

    public static void moveDice(int command) {
        // 동1 서2 북3 남4
        switch (command) {
            case 1:
                // 동쪽
                if (y + 1 < M) {
                    y += 1;
                    int floor = dice[3][1];
                    int temp = dice[1][2];
                    for(int i = 2; i >= 1; i--){
                        dice[1][i] = dice[1][i - 1];
                    }
                    dice[3][1] = temp;
                    dice[1][0] = floor;

                    changeNumber();
                }
                else {
                    return;
                }
                break;
            // 서쪽
            case 2:
                if (y - 1 >= 0) {
                    y -= 1;
                    int floor = dice[3][1];
                    int temp = dice[1][0];
                    for (int i = 1; i < 3; i++) {
                        dice[1][i - 1] = dice[1][i];
                    }
                    dice[3][1] = temp;
                    dice[1][2] = floor;

                    changeNumber();
                }
                else {
                    return;
                }
                break;
            // 북쪽
            case 3:
                if (x - 1 >= 0) {
                    x -= 1;
                    int temp = dice[0][1];
                    for (int i = 1; i < 4; i++) {
                        dice[i - 1][1] = dice[i][1];
                    }
                    dice[3][1] = temp;

                    changeNumber();
                }
                else {
                    return;
                }
                break;
            // 남쪽
            case 4:
                if (x + 1 < N) {
                    x += 1;
                    int temp = dice[3][1];
                    for (int i = 3; i >= 1; i--) {
                        dice[i][1] = dice[i - 1][1];
                    }
                    dice[0][1] = temp;

                    changeNumber();
                }
                else {
                    return;
                }
            default:
                break;
        }
    }

    public static void changeNumber() {
        // 바닥면이 0인경우 값을 복사하고
        if (field[x][y] == 0) {
            field[x][y] = dice[3][1];
        }
        // 아닌경우 주사위의 바닥면으로 복사한다...
        else {
            dice[3][1] = field[x][y];
            field[x][y] = 0;
        }
        System.out.println(dice[1][1]);
    }

}
