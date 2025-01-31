import java.util.*;
import java.io.*;
public class Main {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int rowSize,colSize, T;
    static int [][] map, cloneMap;
    static int topMachinePos;
    static int btmMachinePos;
    static int [] dx = {1,0,-1,0};//확산을 위한 방향
    static int [] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        inputInform(); //입력 받기
        testSimulation(T); //시뮬레이션 돌리기
        outputAnswer(findAnswer());// 정답 찾고 출력
    }
    static void outputAnswer(int answer){
        System.out.print(answer);
    }
    static void spreadDust(){
        cloneMap = new int[rowSize][colSize];
        for (int r = 0; r < rowSize; r++) {// 1. 미세먼지 확산
            for (int c = 0; c < colSize; c++) {
                if (map[r][c]>0){
                    int tmpValue = map[r][c]/5;
                    int count = 0;
                    for (int i = 0; i < 4; i++) {
                        int ny = dy[i]+r;
                        int nx = dx[i]+c;
                        if (nx>=0&&ny>=0&&nx<colSize&&ny<rowSize&&map[ny][nx]!=-1){
                            cloneMap[ny][nx] += tmpValue;
                            count++;
                        }
                    }
                    cloneMap[r][c] += map[r][c] - tmpValue*count;
                }
            }
        }
        resetAirMachine();
    }
    static void circulateBottomDust(int [] btmDx, int btmDy[],int turnNumber){
        //2-2 공기 청정기 bottom (그림에선 위)
        Queue<int []> q = new ArrayDeque<>();
        q.add(new int[]{btmMachinePos,0,0});//마지막은 다음 값
        while (!q.isEmpty()){
            int []pos = q.poll();
            int nextY = pos[0] + btmDy[turnNumber];
            int nextX = pos[1] + btmDx[turnNumber];
            if (nextY==topMachinePos&&nextX==0) break;
            if (nextY>=0 && nextX>=0 && nextY<rowSize && nextX<colSize){
                int nextValue = cloneMap[nextY][nextX];
                cloneMap[nextY][nextX] =  pos[2]; //다음 값에 다 다음위치로 준비한 현재위치를 대입
                q.add(new int[]{nextY,nextX,nextValue});
            }else{//범위를 넘으면 방향을 바꾼다.
                turnNumber++;
                q.add(new int[]{pos[0],pos[1],pos[2]});
            }
        }
    }
    static void circulateTopDust(int [] topDx, int topDy[],int turnNumber){
        Queue<int []> q = new ArrayDeque<>();
        q.add(new int[]{topMachinePos,0,0});//마지막은 다음 값
        while (!q.isEmpty()){
            int []pos = q.poll();
            int nextY = pos[0] + topDy[turnNumber];
            int nextX = pos[1] + topDx[turnNumber];
            if (nextY==topMachinePos&&nextX==0) break;
            if (nextY>=0 && nextX>=0 && nextY<rowSize && nextX<colSize){
                int nextValue = cloneMap[nextY][nextX];
                cloneMap[nextY][nextX] =  pos[2]; //다음 값에 다 다음위치로 준비한 현재위치를 대입
                q.add(new int[]{nextY,nextX,nextValue});
            }else{//범위를 넘으면 방향을 바꾼다.
                turnNumber++;
                q.add(new int[]{pos[0],pos[1],pos[2]});
            }
        }
    }
    static void resetAirMachine(){
        map[btmMachinePos][0] = 0;
        map[topMachinePos][0] = 0;
    }
    static void testSimulation(int time){
        for (int t = 0; t < time; t++) {
            spreadDust();// 1. 먼지 뿌려지기
            circulateTopDust(new int[]{1,0,-1,0},new int[]{0,1,0,-1},0);// 2. 공기청정기에서 바람 나옴, 순환
            circulateBottomDust(new int[]{1,0,-1,0}, new int[]{0,-1,0,1},0);
            map = cloneMap.clone();
            resetAirMachine();
        }
    }
    static int findAnswer(){
        int answer = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                answer += map[i][j];
            }
        }
        return answer;
    }
    static void inputInform() throws IOException { // 입력 받기
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[rowSize][colSize];
        //map 정보 입력 받기
        boolean tmpFlag = false;
        for (int r = 0; r < rowSize; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < colSize; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (!tmpFlag&&map[r][c]==-1){
                    topMachinePos = r+1;//행번호 저장
                    btmMachinePos = r;
                    tmpFlag = true;
                }
            }
        }
    }
}
