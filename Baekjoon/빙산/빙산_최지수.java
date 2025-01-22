import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] dr = {{0, 1, 0, -1}, {1, 0, -1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        /*
         * 주변의 0 개수만큼 빙산 높이 줄어듬
         */
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        
        int[][] bing = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                bing[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        
        
        /*
         * 한칸씩 순회하면서 깎아나갈텐데 
         * 같은 시간에 녹은(0된) 것도 옆에서 카운트가 되는 현상을 어떻게 방지할까
         * 매번 복사하는 건........
         * 0이 되는 칸만 저장을 해볼까
         * 
         * 깎는 함수, 갈라졌는지 확인하는 함수
         * 가 한 while문(반복할 때마다 time++) 안에 들어가있자
         * 
         * 갈라졌는지 확인하는 건 일단 돌면서 0 아닐 때 bfs로 한 영역 visit 찍어
         * 방문 안한 0 아닌 곳 있는지 확인
         */
        
        
        int time = 0;
        boolean[][] visit;
        boolean isFinish = false;
        int cnt = 0;
        
        while (!isFinish) {
        	// 갈라?
            visit = new boolean[n][m];
            Queue<int[]> que = new LinkedList<int[]>();
            boolean isSecond = false;
            
            for (int i = 1; i < n-1; i++) {
				for (int j = 1; j < m-1; j++) {
					if (bing[i][j] <= 0 || visit[i][j]) {
						if (bing[i][j] == -1) bing[i][j] = 0;
						continue;
					}
					else if (isSecond) {
						System.out.println(time);
						isFinish = true;
						break;
					}
					visit[i][j] = true;
					que.add(new int[] {i, j});
					
					while (!que.isEmpty()) {
						int[] now = que.poll();
						
						for (int k = 0; k < 4; k++) {
							int y = now[0] + dr[0][k];
							int x = now[1] + dr[1][k];
							if (bing[y][x] <= 0 || visit[y][x]) continue;
							que.add(new int[] {y,x});
							visit[y][x] = true;
						}
					}
					isSecond = true;
				}
				if (isFinish) break;
			}
            
        	// 깎아
        	cnt = 0;
            for (int i = 1; i < n-1; i++) {
                for (int j = 1; j < m-1; j++) {
                	if (bing[i][j] == -1) bing[i][j] = 0;
                	else if (bing[i][j] != 0) {
                		cnt++;
                        int minus = 0;
                        for (int k = 0; k < 4; k++) {
                            if (bing[i+dr[0][k]][j+dr[1][k]] == 0) {
                                minus++;
                            }
                        }
                        if (bing[i][j] - minus <= 0) bing[i][j] = -1; // 이번 타임엔 영향 안 주게
                        else bing[i][j] -= minus;
                    }
                }
            }
            if (cnt==0) {
            	System.out.println(0);
            	isFinish = true;
            	break;
            }
            time++;
            
        }
        
    }
}
