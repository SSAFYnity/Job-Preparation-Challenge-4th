import java.util.*;

class Solution {
    static Stack<Integer> stack = new Stack<>();
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        int size = n;
        for(int i=0;i<cmd.length;i++) {
            String line = cmd[i];
            switch(line.charAt(0)) {
                case 'U':
                    k -= Integer.parseInt(line.substring(2));
                    break;
                case 'D':
                    k += Integer.parseInt(line.substring(2));
                    break;
                case 'C':
                    stack.push(k);
                    size--;
                    if(k == size) {
                        k--;
                    }
                    break;
                case 'Z':
                    if(k >= stack.pop()) {
                        k++;
                    }
                    size++;
                    break;
            }
        }
        for(int i=0;i<size;i++) {
            sb.append("O");
        }
        //
        while(!stack.isEmpty()) {
            sb.insert(stack.pop(), "X");
        }
        return sb.toString();
    }
}