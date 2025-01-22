import java.util.*;
import java.io.*;

public class Main_15685 {
    static int N;
    // 우 상 좌 하 (반시계)
    static int[] dx = {0,-1,0,1};
    static int[] dy = {1,0,-1,0};
    static boolean[][] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        graph = new boolean[101][101];

        for (int i=0;i<N;i++) {
            st = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int generation = Integer.parseInt(st.nextToken());

            // 1. 방향 구하기
            List<Integer> directionList = getDirection(dir, generation);
            // 2. 그리기
            draw(x,y,directionList);
        }
        // 3. 정사각형 개수 구하기
        int result = getBox();

        System.out.println(result);
    }
    private static int getBox() {
        int count = 0;
        for (int i=0;i<100;i++) {
            for (int j=0;j<100;j++) {
                if (graph[i][j] && graph[i][j+1] && graph[i+1][j] && graph[i+1][j+1]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static List<Integer> getDirection (int dir, int generation) {
        List<Integer> directionList = new ArrayList<>();
        directionList.add(dir);

        for (int i=0;i<generation;i++) {
            for (int j=directionList.size()-1; j>=0; j--) {
                directionList.add((directionList.get(j)+1)%4);
            }
        }

        return directionList;
    }

    private static void draw (int x, int y, List<Integer> directionList) {
        graph[x][y] = true;
        for (Integer dir : directionList) {
            x += dx[dir];
            y += dy[dir];
            graph[x][y] = true;
        }
    }
}

