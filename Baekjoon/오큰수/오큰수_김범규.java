package Baekjoon.오큰수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오큰수_김범규 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N]; // 입력 배열
        int[] result = new int[N]; // 결과 저장 배열 (오큰수)
        Stack<Integer> stack = new Stack<>(); // 인덱스를 저장하는 스택

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 오른쪽에서 왼쪽으로 탐색 (반대 방향)
        for (int i = 0; i < N; i++) {
            // 스택이 비어있지 않고, 현재 값이 스택의 top보다 크면 pop
            while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                result[stack.pop()] = A[i];
            }
            // 현재 인덱스를 스택에 push
            stack.push(i);
        }

        // 아직 남아있는 인덱스는 오큰수가 없으므로 -1
        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
