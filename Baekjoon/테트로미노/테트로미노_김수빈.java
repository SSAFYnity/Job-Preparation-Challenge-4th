import java.io.*;
import java.util.*;

public class 테트로미노_김수빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테트로미노 5가지 종류 존재
        // N×M인 종이 위에 테트로미노 하나를 놓음
        // 테트로미노 합의 최댓값 출력
        String[] paperSize = br.readLine().split(" ");
        int N = Integer.parseInt(paperSize[0]);  // 세로 크기
        int M = Integer.parseInt(paperSize[1]);  // 가로 크기
        int[][] paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(line[j]);
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // 상하좌우 & 우하 좌하 좌상 우상
        int[][] shapes1 =
                {{3, 3, 3}, {1, 1, 1},
                {3, 1, 2},
                {1, 1, 3}, {0, 2, 2}, {3, 0, 0}, {3, 1, 1}, {3, 3, 0}, {1, 3, 3}, {2, 1, 1}, {3, 3, 1},
                {1, 3, 1}, {3, 0, 3}, {1, 2, 1}, {3, 1, 3}};
        int[][] shapes2 = {{2, 0, 3}, {0, 3, 1}, {2, 1, 3}, {0, 2, 1}};

        int maxNum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[] shape : shapes1) {
                    int num = paper[i][j];
                    int cr = i, cc = j;
                    for (int s : shape) {
                        cr += directions[s][0];
                        cc += directions[s][1];
                        // 범위 안이 아니면 도형을 만들 수 없어서 break
                        if (cr >= 0 && cr < N && cc >= 0 && cc < M) {
                            num += paper[cr][cc];
                        } else {
                            break;
                        }
                    }
                    if (num > maxNum) {
                        maxNum = num;
                    }
                }
                for (int[] shape : shapes2) {
                    int num = paper[i][j];
                    int cr = i, cc = j;
                    for (int s : shape) {
                        int nr = cr + directions[s][0];
                        int nc = cc + directions[s][1];
                        // 범위 안이 아니면 도형을 만들 수 없어서 break
                        if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                            num += paper[nr][nc];
                        } else {
                            break;
                        }
                    }
                    if (num > maxNum) {
                        maxNum = num;
                    }
                }
            }
        }
        System.out.println(maxNum);

    }
}
