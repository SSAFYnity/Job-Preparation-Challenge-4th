import java.io.*;
import java.util.*;
public class Main {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int rowSize, colSize;
    static int [][] map;
    static int [][] dpMap;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};
    static StringTokenizer st ;
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new int[rowSize][colSize];
        dpMap = new int[rowSize][colSize];

        for (int r = 0; r < rowSize; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < colSize; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                dpMap[r][c] = -1;
            }
        }
        System.out.print(DFS(0,0));
    }
    static int DFS(int curY, int curX){
        if (curY==rowSize-1&&curX==colSize-1){
            return 1;
        }
        if (dpMap[curY][curX]!=-1){// 미리 간 경로에 대해 다시 가지 않는다.
            return dpMap[curY][curX];
        }
        dpMap[curY][curX] = 0; //이동 체크
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + curY;
            int nx = dx[i] + curX;
            if (ny>=0 && nx>=0 && ny<rowSize && nx<colSize ){
                if (map[curY][curX]>map[ny][nx]){
                    dpMap[curY][curX] += DFS(ny,nx);
                }
            }
        }
        return dpMap[curY][curX];
    }
}
