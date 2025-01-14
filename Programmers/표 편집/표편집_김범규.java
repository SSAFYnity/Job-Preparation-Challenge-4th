import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        // 이중 연결 리스트
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] removed = new boolean[n]; // 삭제 여부 확인

        // 초기화
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1; // 마지막 행은 next가 없음

        // 삭제된 행 저장
        Stack<Integer> stack = new Stack<>();

        // 명령어 처리
        for (String command : cmd) {
            char op = command.charAt(0);
            
            if (op == 'U') { // 위로 이동
                int x = Integer.parseInt(command.substring(2));
                for (int i = 0; i < x; i++) {
                    k = prev[k];
                }
            } else if (op == 'D') { // 아래로 이동
                int x = Integer.parseInt(command.substring(2));
                for (int i = 0; i < x; i++) {
                    k = next[k];
                }
            } else if (op == 'C') { // 현재 행 삭제
                stack.push(k); // 삭제된 행 저장
                removed[k] = true; // 삭제 상태 기록
                if (prev[k] != -1) {
                    next[prev[k]] = next[k];
                }
                if (next[k] != -1) {
                    prev[next[k]] = prev[k];
                }
                // 삭제 후 다음 행으로 이동
                k = (next[k] != -1) ? next[k] : prev[k];
            } else if (op == 'Z') { // 삭제된 행 복구
                int restored = stack.pop(); // 복구할 행
                removed[restored] = false; // 삭제 상태 해제
                if (prev[restored] != -1) {
                    next[prev[restored]] = restored;
                }
                if (next[restored] != -1) {
                    prev[next[restored]] = restored;
                }
            }
        }

        // 결과 문자열 생성
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            answer.append(removed[i] ? 'X' : 'O');
        }

        return answer.toString();
    }
}
