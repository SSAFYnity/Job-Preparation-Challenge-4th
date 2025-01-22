import java.io.IOException;
import java.io.*;
import java.util.*;

public class 빙산 {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int rowSize, colSize;
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};
    static int [][] map;
    static boolean [][] isVisitedMap;
    public static void main(String[] args) throws IOException {
        settingForInput();
        int answerYear = simulateIceBergForYear();
        System.out.println(answerYear);
        /*
        1. 이동하면서 탐색한다.
        2. 인접 물이 있으면 그 인접 물의 갯수만큼 제거하여 현실 맵에 반영한다.
        3. 현실 맵을 기준으로 섬이 몇개 인지 BFS로 계산한다.
        * */
    }
    static void settingForInput() throws IOException{ // 세팅함수
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new int[rowSize][colSize];// 값할당.
        for (int i = 0; i < rowSize; i++) {//맵 저장
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    static int simulateIceBergForYear(){//이동하면서 탐색 1 _ 1년 흘려보내기
        int answerYear = 0;
        while (true) {
            meltIcebergs(); // 빙산 녹이기 작업
            answerYear++;
            int bergCount = findBergCount(); // 빙산 개수 확인 작업
            if (bergCount >= 2) {
                return answerYear; // 빙산이 둘로 분리된 경우
            } else if (bergCount == 0) {
                return 0; // 빙산이 모두 녹은 경우
            }
        }
    }
    // 빙산 녹이기
    static void meltIcebergs() {
        int[][] tempMap = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (map[i][j] > 0) {
                    tempMap[i][j] = Math.max(map[i][j] - countAdjacentWater(i, j), 0);
                }
            }
        }
        map = tempMap; // 변경된 맵 반영
    }
    static int countAdjacentWater(int y, int x){ //인접 물의 갯수를 구하고 해당 값만큼 리턴하여 본 함수에서 리턴 값만큼 빼준다
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny >= 0 && nx >= 0 && ny < rowSize && nx < colSize && map[ny][nx] == 0) {
                count++;
            }
        }
        return count;
    }
    static int findBergCount(){ //이동하면서 탐색 2 _ 섬갯수 확인
        isVisitedMap = new boolean[rowSize][colSize];
        int bergCount = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (map[i][j] > 0 && !isVisitedMap[i][j]){
                    bergCount++;
                    checkIceBergCount(i,j);
                }
            }
        }
        return bergCount;
    }
    static void checkIceBergCount(int y, int x){//섬 갯수 확인 3
        Queue<int [] > q = new ArrayDeque<>();
        q.add(new int[]{y,x});
        isVisitedMap[y][x] = true;
        while (!q.isEmpty()){
            int [] curPos = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + curPos[0];
                int nx = dx[i] + curPos[1];
                if (ny>=0&&nx>=0&&ny<rowSize&&nx<colSize){
                    if (isVisitedMap[ny][nx]) continue;
                    if (map[ny][nx]>0){//주위가 섬일 경우
                        q.add(new int[]{ny,nx});
                        isVisitedMap[ny][nx] = true;
                    }
                }
            }
        }
    }
}
