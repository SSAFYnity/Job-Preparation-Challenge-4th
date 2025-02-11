package Baekjoon.내리막길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 내리막길_김범규 {
    static int M, N;
    static int[][] map;
    static int[][] dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];

        for(int i = 0; i < M; i++){
            Arrays.fill(dp[i], -1);
        }

        //값 저장
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp와 dfs를 활용하여 문제를 풀이한다.
        int cnt = dfs(0, 0);

        System.out.println(cnt);
    }

    public static int dfs(int r, int c){
        //끝점
        if(r == M - 1 && c == N - 1){
            return 1;
        }

        if(dp[r][c] != -1){
            return dp[r][c];
        }

        for(int d = 0; d < 4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr >= 0 && nr < M && nc >= 0 && nc < N){
                if(map[r][c] > map[nr][nc]){
                    dp[r][c] += dfs(nr, nc);
                }
            }
        }

        return dp[r][c];
    }
}
