import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*1. 배열 크기는 최대 50X50
2. 빈칸은 . , 물은 *, 돌은 X
3. 비버는 D, 고슴도치는 S
4. 1턴에 물은 사방으로 1칸씩 확장 (돌, 비버소굴 X)
5. 1턴에 도치는 네방향중 한곳으로 1칸 이동 (돌, 물 X)
6. 고슴도치가 비버굴로 이동할 수 있는 가장 빠른 시간 구하기. 이동 못하면 KAKTUS 출력*/

/*잘못 생각했던 부분
- 고슴도치를 한칸만 이동시킨다고 S 위치를 바꿔준 후 이전 위치를 빈칸으로 메꿔줌..
- 근데 이렇게 하지말고 고슴도치 계속 4방탐색으로 계속 증식해야함. 그리고 모든 물, 도치에서 bfs 해줘야함 돌때마다 cnt++
- 도치큐가 비어있지 않을때까지 도는데
	- 도치가 비버집에 도착하면 끝. cnt 출력
	- 큐에 있는거 다 쓸때까지 비버집에 못가면 KAKTUS 출력*/

class Water{
	int waterX, waterY;
	
	Water(int x, int y){
		this.waterX=x;
		this.waterY=y;
	}
}

class Dochi{
	int dochiX, dochiY;
	
	Dochi(int x, int y){
		this.dochiX=x;
		this.dochiY=y;
	}
}

public class Main {
	static int R,C;
	static int cnt;
	static int beaverX, beaverY;
	static boolean flag;
	static Queue<Water>waterQ;
	static Queue<Dochi>dochiQ;
	
	static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		char[][] arr=new char[R][C];
		
		waterQ=new ArrayDeque<>();
		dochiQ=new ArrayDeque<>();
		
		// 입력
		for(int i=0; i<R; i++) {
			String str=br.readLine();
			for(int j=0; j<C; j++) {
				char ch=str.charAt(j);
				if(ch=='D') {
					beaverX=i;
					beaverY=j;
				}
				else if(ch=='S') {
					dochiQ.add(new Dochi(i,j));
				}
				else if(ch=='*') {
					waterQ.add(new Water(i,j));
				}
				arr[i][j]=ch;
			}
		}
		
		Water wt;
		Dochi dochi;
		
		// 1턴		
		while(!dochiQ.isEmpty()) {
			
			cnt++;
			int waterLen=waterQ.size();
			
			for(int i=0; i<waterLen; i++) {
				wt=waterQ.poll();
				int wtX=wt.waterX;
				int wtY=wt.waterY;
				
				// 물이동
				for(int j=0; j<4; j++) {
					int nx=dx[j]+wtX;
					int ny=dy[j]+wtY;
					
					// 범위를 넘지 않고 빈칸이면
					
					if(isRange(nx, ny) && arr[nx][ny]!='X' && !(nx==beaverX && ny==beaverY)) {
						
						arr[nx][ny]='*';
						waterQ.add(new Water(nx,ny));
					}
				}
			}
				
			// 도치 이동 (고슴도치가 비버집에 가면 끝내기)
			// BFS로 탐색 후 빈칸이면 증식. cnt++
			
			int dochiLen=dochiQ.size();
			for(int i=0; i<dochiLen; i++) {
				dochi=dochiQ.poll();
				int dochiX=dochi.dochiX;
				int dochiY=dochi.dochiY;
				
				
				for(int j=0; j<4; j++) {
					int nx=dx[j]+dochiX;
					int ny=dy[j]+dochiY;
					
					// 범위를 넘지 않고 현재 위치에 도치가 있으면
					if(isRange(nx, ny)) {
						// 비버 집이면 flag를 true로 바꿔주고 종료
						if(nx==beaverX && ny==beaverY) {
							flag=true;
							break;
						}
						// 빈칸이면 증식
						if(arr[nx][ny]=='.') {
							arr[nx][ny]='S';
							dochiQ.add(new Dochi(nx,ny));
						}
						
					}
				}
			}
			
//			for(int i=0; i<R; i++) System.out.println(arr[i]);
//			System.out.println("========");
			if(flag==true) break;
			
		}
		System.out.println(flag==true ? cnt : "KAKTUS");
		
	}

	private static boolean isRange(int nx, int ny) {
		if(nx>=0 && nx<R && ny>=0 && ny<C) return true;
		return false;
	}
}