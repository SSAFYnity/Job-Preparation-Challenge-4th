import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트로미노_김범규 {
    static int N, M;
    static int[][] field;

    // 총 19가지의 방식
    static int[][][] tetrominoes = {
            // I 블록
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } },
            { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } },

            // 정사각형
            { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } },

            // L 블럭
            { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },
            { { 0, 0 }, { 0, -1 }, { 0, -2 }, { 1, -2 } },
            { { 0, 0 }, { -1, 0 }, { -2, 0 }, { -2, -1 } },
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { -1, 2 } },

            // J 블럭
            { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, -1 } },
            { { 0, 0 }, { 0, -1 }, { 0, -2 }, { -1, -2 } },
            { { 0, 0 }, { -1, 0 }, { -2, 0 }, { -2, 1 } },
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } },

            // S 블럭
            { { 0, 0 }, { 0, 1 }, { -1, 1 }, { -1, 2 } },
            { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } },

            // Z 블럭
            { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } },
            { { 0, 0 }, { 1, 0 }, { 1, -1 }, { 2, -1 } },

            // T 블럭
            { { 0, 0 }, { 0, -1 }, { -1, 0 }, { 1, 0 } },
            { { 0, 0 }, { 0, 1 }, { -1, 0 }, { 1, 0 } },
            { { 0, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } },
            { { 0, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } },
    };

    public static void main(String[] args) throws IOException {
        // 풀이 방법
        // 1. 테트로미노 경우의 수 19가지를 모두 대입해본다.
        // 작대기 2, 정사각형 1, L자(J자) 8, ㄹ자 4, 볼록 블럭 4개
        // 2. 이 때, 범위를 넘어서면 바로 out

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N * M 행렬
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][M];
        int max = 0;

        // 값 생성
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[][] shape : tetrominoes) {
                    // 범위를 벗어나지 않으면 검사 진행
                    if (isValid(i, j, shape)) {
                        max = Math.max(carculate(i, j, shape), max);
                    }
                }
            }
        }

        //결과 출력
        System.out.println(max);
    }

    // 범위 벗어나는지 확인하는 함수
    public static boolean isValid(int x, int y, int[][] shape) {
        for (int[] delta : shape) {
            int nx = x + delta[0];
            int ny = y + delta[1];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                return false;
        }

        return true;
    }

    // 값을 계산하는 함수
    public static int carculate(int x, int y, int[][] shape) {
        int sum = 0;
        for (int[] delta : shape) {
            int nx = x + delta[0];
            int ny = y + delta[1];
            sum += field[nx][ny];
        }
        return sum;
    }
}