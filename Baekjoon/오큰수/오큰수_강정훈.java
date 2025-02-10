import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] answer = new int[N];
        for (int index = 0; index < N; index++) {
            arr[index] = Integer.parseInt(st.nextToken());
            answer[index] = -1;
        }
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(arr[N-1]);
        for (int index = N - 2; index >= 0; index--) {
            int current = arr[index];
            while(!dq.isEmpty()) {
                int prev = dq.peekLast();
                if (current >= prev) {
                    dq.pollLast();
                } else {
                    answer[index] = prev;
                    break;
                }
            }
            dq.addLast(current);
        }
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < N; index++) {
            sb.append(answer[index]+" ");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }
}