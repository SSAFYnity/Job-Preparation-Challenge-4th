import java.util.*; 
import java.io.*; 

class Solution {
    public static class Node {
        int prev; 
        int next; 
        int curr; 
        
        public Node(int prev, int next, int curr) {
            this.prev = prev; 
            this.next = next; 
            this.curr = curr; 
        }
    }
    
    static int[] prev; 
    static int[] next; 
    static Stack<Node> stack; 
    static StringBuilder sb; 
    
    public String solution(int n, int curr, String[] cmdList) {        
        stack = new Stack<>(); 
        sb = new StringBuilder();   
        prev = new int[n];
        next = new int[n]; 
        
        for (int i=0;i<n;i++){
            prev[i] = i-1; 
            next[i] = i+1; 
        }
        next[n-1] = -1; 
        
        StringTokenizer st; 
        for (String cmd : cmdList) {
            st = new StringTokenizer(cmd); 
            char ch = st.nextToken().charAt(0);
            if (ch == 'U') {
                int x = Integer.parseInt(st.nextToken());
                curr = up(x, curr);
            } else if (ch == 'D') {
                int x = Integer.parseInt(st.nextToken());
                curr = down(x, curr);
            } else if (ch == 'C') {
                curr = remove(curr);
            } else if (ch == 'Z') {
                restore(); 
            }
        }

        for (int i=0;i<n;i++) {
            sb.append("O");
        }
        for (Node node : stack) {
            sb.replace(node.curr, node.curr+1, "X");
        }
        return sb.toString(); 
    }
    
    private static int up (int x, int curr) {
        while (x-- > 0) {
            curr = prev[curr];
        }
        return curr; 
    }
    
    private static int down (int x, int curr) {
        while (x-- > 0) {
            curr = next[curr]; 
        }
        return curr; 
    }
    
    private static int remove (int curr) {
        stack.push(new Node(prev[curr], next[curr], curr));

        // 양쪽 노드 연결 
        if(prev[curr] != -1) next[prev[curr]] = next[curr];
        if(next[curr] != -1) prev[next[curr]] = prev[curr];
        
        if (next[curr] != -1) 
            return next[curr];
        else 
            return prev[curr];
    }
    
    private static void restore () {
        Node node = stack.pop(); 

        if (node.prev != -1) next[node.prev] = node.curr; 
        if (node.next != -1) prev[node.next] = node.curr; 
    }
}
