import java.io.*;

public class 쿠키의신체측정_김인호 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] map;
    static int mapSize;
    static int[] bodyInfoList = new int[5];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        mapSize = Integer.parseInt(br.readLine());
        map = new boolean[mapSize][mapSize];
        // 맵 저장. 쿠기가 있는 경우 true로 저장
        for (int i = 0; i < mapSize; i++) {
            char[] tmpList = br.readLine().toCharArray();
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = tmpList[j] == '*';
            }
        }
        //탐색하다 머리가 걸리면 나머지 부분도 찾고 종료
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j]) {
                    findHeart(i, j);
                    printAnswer();//정답 출력
                    return;
                }
            }
        }
    }

    //심장 찾기
    static void findHeart(int y, int x) {
        sb.append(y + 2).append(" ").append(x + 1).append("\n");
        findLeftArm(y + 1, x - 1);
        findRightArm(y + 1, x + 1);
        findSpine(y + 2, x);
    }

    //왼팔 찾기
    static void findLeftArm(int y, int x) {
        if (isValidPosition(y, x)) {
            updateBodyInfoList(0);
            findLeftArm(y, x - 1);
        }
    }

    // 오른팔 찾기
    static void findRightArm(int y, int x) {
        if (isValidPosition(y, x)) {
            updateBodyInfoList(1);
            findRightArm(y, x + 1);
        }
    }

    // 척추 찾고 다 찾으면 왼쪽 오른쪽 다리 찾기
    static void findSpine(int y, int x) {
        if (isValidPosition(y, x)) {
            updateBodyInfoList(2); // Spine
            findSpine(y + 1, x);
        } else {
            findLeftLeg(y, x - 1);
            findRightLeg(y, x + 1);
        }
    }

    //왼쪽 다리 찾기
    static void findLeftLeg(int y, int x) {
        if (isValidPosition(y, x)) {
            updateBodyInfoList(3);
            findLeftLeg(y + 1, x);
        }
    }

    //오른쪽 다리 찾기
    static void findRightLeg(int y, int x) {
        if (isValidPosition(y, x)) {
            updateBodyInfoList(4); // 증감
            findRightLeg(y + 1, x);
        }
    }

    // 포지션 벗어나는지 확인
    static boolean isValidPosition(int y, int x) {
        return x >= 0 && y >= 0 && x < mapSize && y < mapSize && map[y][x];
    }
    // 정답 출력
    static void printAnswer() {
        for (int token : bodyInfoList) {
            sb.append(token).append(" ");
        }
        System.out.print(sb);
    }
    //바디 값 증감
    static void updateBodyInfoList(int num) {
        bodyInfoList[num]++;
    }

}
