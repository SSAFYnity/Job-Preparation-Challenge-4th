import java.util.*;

class Solution {
    static class Node {
        boolean present = true;
        Node prev = null, next = null;
        
        public Node up(int x) {
            Node node = this;
            for (int i = 0; i < x; i++) {
                node = node.prev;
            }
            return node;
        }
        public Node down(int x) {
            Node node = this;
            for (int i = 0; i < x; i++) {
                node = node.next;
            }
            return node;
        }
        public Node delete() {
            this.present = false;
            Node prev = this.prev;
            Node next = this.next;
            
            if (prev != null) prev.next = next;
            if (next != null) {
                next.prev = prev;
                return next;
            }
            
            return prev;
        }
        public void ctrlZ() {
            this.present = true;
            Node prev = this.prev;
            Node next = this.next;
            
            if (prev != null) prev.next = this;
            if (next != null) next.prev = this;
        }
    }
    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        
        Node[] linkedList = new Node[n];
        linkedList[0] = new Node();
        for (int i = 1; i < n; i++) {
            linkedList[i] = new Node();
            linkedList[i].prev = linkedList[i-1];
            linkedList[i-1].next = linkedList[i];
        }
        
        Node now = linkedList[k];
        
        ArrayDeque<Node> stack = new ArrayDeque<>();
        for (String inst : cmd) {
            char i = inst.charAt(0);
            switch (i) {
                case 'U': 
                    now = now.up(Integer.parseInt(inst.split(" ")[1]));
                    break;
                case 'D':
                    now = now.down(Integer.parseInt(inst.split(" ")[1]));
                    break;
                case 'C':
                    stack.push(now);
                    now = now.delete();
                    break;
                case 'Z':
                    stack.pop().ctrlZ();
            }
        }
        
        for (Node node : linkedList) {
            if (node.present) answer.append("O");
            else answer.append("X");
        }
        
        return answer.toString();
    }
}