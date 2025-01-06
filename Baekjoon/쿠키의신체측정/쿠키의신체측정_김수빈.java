package Baekjoon;
import java.util.*;
import java.io.*;

public class 쿠키의신체측정_김수빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 판 크기
        char[][] board = new char[N][N];  // 쿠키판
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 심장찾기
        int[] heart = new int[2];
        loop:
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 참고로 String은 .equals("*")를 써야함
                if (board[r][c] == '*') {
                    heart[0] = r + 1;
                    heart[1] = c;
                    break loop;
                }
            }
        }
        int heartR = heart[0];
        int heartC = heart[1];
        heart[0]++;
        heart[1]++;

        // 왼쪽 팔
        int leftArmC = heartC -1, leftArmLength = 0;
        while(leftArmC >= 0 && board[heartR][leftArmC] == '*') {
            leftArmLength++;
            leftArmC--;
        }
        // 오른쪽 팔
        int rightArmC = heartC + 1, rightArmLength = 0;
        while(rightArmC < N && board[heartR][rightArmC] == '*') {
            rightArmLength++;
            rightArmC++;
        }
        // 허리
        int waistR = heartR + 1, waistLength = 0;
        while(waistR < N && board[waistR][heartC] == '*') {
            waistLength++;
            waistR++;
        }
        // 왼쪽 다리
        int leftLegC = heartC - 1, leftLegR = waistR, leftLegLength = 0;
        while(leftLegR < N && board[leftLegR][leftLegC] == '*') {
            leftLegLength++;
            leftLegR++;
        }
        // 오른쪽 다리
        int rightLegC = heartC + 1, rightLegR = waistR, rightLegLength = 0;
        while(rightLegR < N && board[rightLegR][rightLegC] == '*') {
            rightLegLength++;
            rightLegR++;
        }
        int[] body = {leftArmLength, rightArmLength, waistLength, leftLegLength, rightLegLength};
        for (int h : heart) {
            System.out.print(h);
            System.out.print(" ");
        };
        System.out.println();
        for (int b : body) {
            System.out.print(b);
            System.out.print(" ");
        }

    }
}
