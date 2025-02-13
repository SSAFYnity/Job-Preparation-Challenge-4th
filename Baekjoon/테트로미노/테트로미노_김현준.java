
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][][] tetromino = {{{0,0}, {0,1}, {0,2}, {0,3}}, // 1자 모양 가로
            {{0,0},{1,0}, {2,0}, {3,0}}, // 세로
            {{0,0}, {0,1}, {1,0}, {1,1}}, // ㅁ 자 모양
            {{0,0}, {1,0}, {2,0}, {2,-1}}, // ㄴ자 모양
            {{0,0}, {1,0}, {2,0}, {2,1}},
            {{0,0}, {0,-1}, {0,-2}, {1,0}},
            {{0,0}, {0,1}, {0,2}, {1,0}},
            {{0,0}, {0,1}, {1,1}, {2,1}},
            {{0,0}, {0,-1}, {1,-1}, {2,-1}},
            {{0,0}, {0,-1}, {0,-2}, {-1,0}},
            {{0,0}, {0,1}, {0,2}, {-1,0}},
            {{0,0}, {1,0}, {1,1}, {2,1}}, // 네번째 모양
            {{0,0}, {0,1}, {-1,1}, {-1,2}},
            {{0,0}, {1,0}, {1,-1}, {2,-1}},
            {{0,0}, {0,1}, {1,1}, {1,2}},
            {{0,0}, {0,1}, {0,2}, {1,1}},  // ㅗ
            {{0,0}, {1,0}, {2,0}, {1,1}},  // ㅏ
            {{0,0}, {0,1}, {0,2}, {-1,1}}, // ㅜ
            {{0,0}, {1,0}, {2,0}, {1,-1}}  // ㅓ
    };
    static int[][] map;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                // 1~5번까지 가능한 경우의 수를 모두 계산한다.
                for(int k=0;k<tetromino.length;k++) {
                    int sum = 0;
                    boolean isValid = true;
                    for(int t=0;t<4;t++) {
                        int r = i + tetromino[k][t][0];
                        int c = j + tetromino[k][t][1];
                        if(inRange(r, c)) {
                            sum += map[r][c];
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid)
                        max = Math.max(max, sum);
                }
            }
        }
        System.out.println(max);

    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
