import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[][] pan;
	static boolean[][] visit;
	static Set<String> set = new HashSet<>();
	static int result = Integer.MIN_VALUE;
	static int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};

	static boolean isOut(int y, int x) {
		return y < 0 || y >= n || x < 0 || x >= m;
	}
	
	public static void tracking(int y, int x, int score, int cnt) {
		if (cnt == 4) {
			if (result < score) result = score;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int w = y + dr[0][i];
			int v = x + dr[1][i];
			
			if (isOut(w,v) || set.contains(w+" "+v)) continue;
			
			set.add(w+" "+v);
			
			tracking(w,v,score+pan[w][v],cnt+1);
			
			if (cnt == 2) {
				for (int j = i; j < 4; j++) {
					int q = y + dr[0][j];
					int p = x + dr[1][j];
					
					if (isOut(q,p) || set.contains(q+" "+p)) continue;
					
					set.add(q+" "+p);
					tracking(w,v,score+pan[w][v]+pan[q][p], cnt+2);
					set.remove(q+" "+p);
				}
			}
			
			set.remove(w+" "+v);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		pan = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				set.add(i+" "+j);
				tracking(i,j,pan[i][j],1);
				set.remove(i+" "+j);
			}
		}
		
		System.out.println(result);
	}
}
