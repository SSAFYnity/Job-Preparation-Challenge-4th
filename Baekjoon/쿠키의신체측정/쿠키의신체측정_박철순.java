import java.util.*;
import java.io.*;
 
public class Main_20125 {

	static int N; 
	static int[][] map; 
	static int headX;
	static int headY;
	static int heartX;
	static int heartY;
	
	static int leftArm; 
	static int rightArm; 
	static int leftLeg; 
	static int rightLeg; 
	static int body; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 

		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		headX = -1; 
		headY = -1; 
		
		for (int i=0;i<N;i++) {
			String temp = in.readLine();
			for (int j=0;j<N;j++) {
				char ch = temp.charAt(j);
				if (ch == '*') {
					if (headX == -1) {
						headX = i;
						headY = j;
					}
					map[i][j] = 1;
				}
			}
		}
		
		heartX = headX+1; 
		heartY = headY; 
		
		// 1. 왼쪽 팔 
		for (int i=0;i<heartY;i++) {
			if (map[heartX][i] == 1) leftArm++;
		}
		// 2. 오른쪽 팔 
		for (int i=heartY+1;i<N;i++) {
			if (map[heartX][i] == 1) rightArm++;
		}
		// 3. 허리 
		for (int i=heartX+1;i<N;i++) {
			if (map[i][heartY] == 1) body++; 
		}
		// 4. 왼쪽 다리 
		for (int i=heartX+body+1;i<N;i++) {
			if (map[i][heartY-1] == 1) leftLeg++;
		}
		// 5. 오른쪽 다리 
		for (int i=heartX+body+1;i<N;i++) {
			if (map[i][heartY+1] == 1) rightLeg++;
		}
		
		sb.append(heartX+1).append(" ").append(heartY+1+"\n");
		sb.append(leftArm+" ").append(rightArm+" ").append(body+" ").append(leftLeg+" ").append(rightLeg);
		System.out.println(sb.toString());
	}
	
}
