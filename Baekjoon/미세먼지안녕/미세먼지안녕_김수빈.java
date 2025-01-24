import java.io.*;
import java.util.*;

public class 미세먼지안녕_김수빈 {
    static int R;
    static int C;
    static int[][] house;
    static int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] dts = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    static int[][] dbs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static void spreadingFineDust() {
        ArrayList<int[]> fineDustPos = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 미세먼지 없거나, 공기청정기인 경우 넘기기
                if (house[i][j] == 0 || house[i][j] == -1) {
                    continue;
                }
                fineDustPos.add(new int[] {i, j, house[i][j]/5});
            }
        }
        for (int[] fineDust : fineDustPos) {
            int num = 0;  // 방향 수
            int cr = fineDust[0], cc = fineDust[1], amount = fineDust[2];
            for (int[] d : ds) {
                int nr = cr + d[0], nc = cc + d[1];
                // 칸이 있고 공기청정기가 아니라면
                if (nr >= 0 && nr < R && nc >= 0  && nc < C && house[nr][nc] != -1) {
                    house[nr][nc] += amount;
                    num++;
                }
            }
            house[cr][cc] -= amount * num;
        }
    }

    private static void workingAirPurifier(int cr, int cc, int[][] d, int di, int currFineDust, boolean isTop) {
        while (true) {
            int nr = cr + d[di][0], nc = cc + d[di][1];
            if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                int temp = house[nr][nc];
                // 공기청정기인 경우 멈추기
                if (temp == -1) {
                    break;
                }
                house[nr][nc] = currFineDust;
                currFineDust = temp;
                cr = nr;
                cc = nc;
            } else {
                di++;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bfr.readLine().split(" ");
        R = Integer.parseInt(input[0]);  // 행
        C = Integer.parseInt(input[1]);  // 열
        int T = Integer.parseInt(input[2]);  // 시간
        house = new int[R][C];
        int[][] airPurifier = new int[2][2];
        int idx = 0;
        for (int i = 0; i < R; i++) {
            String[] line = bfr.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                house[i][j] = Integer.parseInt(line[j]);
                if (house[i][j] == -1) {
                    airPurifier[idx++] = new int[] {i, j};
                }
            }
        }

        int time = 0;
        while (time < T) {
            // 1. 미세먼지 확산
            spreadingFineDust();
            // 2. 공기 청정기 작동
            // 상단
            int atr = airPurifier[0][0], atc = airPurifier[0][1];
            workingAirPurifier(atr, atc + 1, dts,0, house[atr][atc + 1], true);
            house[atr][atc + 1] = 0;
            // 하단
            int abr = airPurifier[1][0], abc = airPurifier[1][1];
            workingAirPurifier(abr, abc + 1, dbs,0, house[abr][abc + 1], false);
            house[abr][abc + 1] = 0;
            time++;
        }
        // 남은 미세먼지 양 확인
        int restFineDust = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (house[i][j] > 0) {
                    restFineDust += house[i][j];
                }
            }
        }

        System.out.println(restFineDust);
    }
}
