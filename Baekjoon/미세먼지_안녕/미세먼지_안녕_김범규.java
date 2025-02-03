package Baekjoon.미세먼지_안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 미세먼지_안녕_김범규 {
    static int R, C, T;
    static int[][] room;
    static int[] airPurifier = new int[2];
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배열 크기와 시간 입력
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];

        int airIdx = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                // 공기청정기 위치
                if (room[i][j] == -1) {
                    airPurifier[airIdx++] = i;
                }
            }
        }

        // 주기동안 순환 반복하기
        for (int i = 0; i < T; i++) {
            // 1. 미세먼지 확산
            spreadDust();
            // 2. 공기청정기 작동
            airPurifierOperation();
        }

        int sum = 0;
        // 전체 미세먼지 합 구하기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 공기청정기 제외
                if (room[i][j] > 0) {
                    sum += room[i][j];
                }
            }
        }

        System.out.println(sum);

    }

    // 복사 배열 생성 후 계산하고 복사배열에 옮기기
    public static void spreadDust() {
        int[][] newRoom = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 0인경우는 확산 안하므로
                int spreadValue = room[i][j] / 5;
                int spreadCount = 0;

                if (room[i][j] > 0) {
                    for (int d = 0; d < 4; d++) {
                        // 4방 탐색 수행
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && room[nr][nc] != -1) {
                            newRoom[nr][nc] += spreadValue;
                            spreadCount++;
                        }
                    }

                    // 가운데는 확산 후 남은 먼지를 저장한다.
                    newRoom[i][j] += room[i][j] - (spreadValue * spreadCount);
                }
            }
        }
        // 과정이 끝나면 배열에 다시 값 옮김
        room = newRoom;
    }

    public static void airPurifierOperation() {
        int top = airPurifier[0];
        int bottom = airPurifier[1];

        // 반시계 회전
        for (int i = top - 1; i > 0; i--) {
            room[i][0] = room[i - 1][0];
        }

        for (int i = 0; i < C - 1; i++) {
            room[0][i] = room[0][i + 1];
        }

        for (int i = 0; i < top; i++) {
            room[i][C - 1] = room[i + 1][C - 1];
        }

        for (int i = C - 1; i > 1; i--) {
            room[top][i] = room[top][i - 1];
        }

        // 시계 회전
        for (int i = bottom + 1; i < R - 1; i++) {
            room[i][0] = room[i + 1][0];
        }

        for (int i = 0; i < C - 1; i++) {
            room[R - 1][i] = room[R - 1][i + 1];
        }

        for (int i = R - 1; i > bottom; i--) {
            room[i][C - 1] = room[i - 1][C - 1];
        }

        for (int i = C - 1; i > 1; i--) {
            room[bottom][i] = room[bottom][i - 1];
        }

        room[top][1] = 0;
        room[bottom][1] = 0;

        room[top][0] = -1;
        room[bottom][0] = -1;

    }

}
