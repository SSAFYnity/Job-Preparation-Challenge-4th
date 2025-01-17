import java.io.*;
import java.util.*;

public class 미세먼지안녕_김수빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bfr.readLine().split(" ");
        int R = Integer.parseInt(input[0]);  // 행
        int C = Integer.parseInt(input[1]);  // 열
        int T = Integer.parseInt(input[2]);  // 시간
        int[][] house = new int[R][C];
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
        // 미세먼지 확산 및 공기 청정기 작동
        int[] topAir= airPurifier[0], bottomAir = airPurifier[1];
        int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int time = 0;
        while (time < T) {
            // 1. 미세먼지 확산
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
            // 2. 공기 청정기 작동
            // 윗칸
            int[] nt = {topAir[0], topAir[1] + 1};
            int[][] dt = {{0, 1} ,{-1, 0}, {0, -1}, {1, 0}};
            int dti = 0;
            int areaR = house[nt[0]][nt[1]];
            while (nt.equals(topAir)) {
                int ntr = nt[0] + dt[dti][0], ntc = nt[1] + dt[dti][1];
                if (ntr < 0 && ntr >= R && ntc < 0 && ntc >= C) {
                    dti++;
                    ntr = nt[0] + dt[dti][0];
                    ntc = nt[1] + dt[dti][1];
                }
                nt = new int[] {ntr, ntc};
                if (nt.equals(topAir)) {
                    break;
                }
                int temp = house[nt[0]][nt[1]];
                house[nt[0]][nt[1]] = areaR;
                areaR = temp;
            }
            // 아랫칸
            int[] nb = {bottomAir[0], bottomAir[1] + 1};
            int[][] db = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int dbi = 0;
            int areaC = house[nb[0]][nb[1]];
            while (nb.equals(bottomAir)) {
                int nbr = nt[0] + db[dti][0], nbc = nt[1] + db[dti][1];
                if (nbr < 0 && nbr >= R && nbc < 0 && nbc >= C) {
                    dti++;
                    nbr = nb[0] + dt[dbi][0];
                    nbc = nb[1] + dt[dbi][1];
                }
                nb = new int[] {nbr, nbc};
                if (nb.equals(bottomAir)) {
                    break;
                }
                int temp = house[nb[0]][nb[1]];
                house[nb[0]][nb[1]] = areaC;
                areaC = temp;
            }
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
