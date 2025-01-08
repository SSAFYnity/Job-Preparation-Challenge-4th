import java.util.*;
import java.io.*;
 
public class Main {
	static int N; 
	static int M; 
	static int[][] graph; 
	static boolean[][] visited;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for (int j=0;j<M;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int year = 0; 
		int islandCount; 
		while (true) {
			// 1. 두덩어리인지 확인 
			islandCount = getIslandCount();
			if (islandCount == 0) {
				year = 0; // 섬이 없는 경우 
				break; 
			} else if (islandCount >= 2) {
				break; 
			}
			// 2. 1년 증가 
			year++; 
			// 3. 빙하 녹기 
			meltdown(); 
		}
		System.out.println(year);
	}

	private static void meltdown() {
		int[][] tempGraph = new int[N][M];
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (graph[i][j] == 0) continue;
				int nearWaterCount = getWaterCount(i,j);
				tempGraph[i][j] = graph[i][j] - nearWaterCount; 
				if (tempGraph[i][j] < 0) tempGraph[i][j] = 0;
			}
		}
		for (int i=0;i<N;i++) {
			graph[i] = Arrays.copyOf(tempGraph[i], tempGraph[i].length);
		}
	}

	private static int getWaterCount(int x, int y) {
		int waterCount = 0; 
		for (int dir=0;dir<4;dir++) {
			int nx = x+dx[dir];
			int ny = y+dy[dir]; 
			if (nx<0||nx>=N||ny<0||ny>=M) continue; 
			
			if (graph[nx][ny] == 0) waterCount++;
		}
		
		return waterCount;
	}

	private static int getIslandCount() {
		int islandCount = 0; 
		visited = new boolean[N][M]; 
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (graph[i][j] > 0 && !visited[i][j]) {
					bfs(i,j);
					islandCount++;
				}
			}
		}
		
		return islandCount;
	}

	private static void bfs(int x, int y) {
		Deque<int[]> deque = new ArrayDeque<>(); 
		deque.add(new int[] {x,y});
		visited[x][y] = true; 
		
		while(!deque.isEmpty()) {
			int[] pos = deque.poll(); 
			for (int dir=0;dir<4;dir++) {
				int nx = pos[0] + dx[dir];
				int ny = pos[1] + dy[dir];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; 
				if (graph[nx][ny] == 0) continue; 
				if (visited[nx][ny]) continue; 
				
				deque.add(new int[] {nx,ny});
				visited[nx][ny] = true; 
			}
		}
	}
}

