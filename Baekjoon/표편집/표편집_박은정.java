import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        TreeSet<Integer> activeRows = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            activeRows.add(i);
        }
        
        Stack<Integer> deleted = new Stack<>();
        int current = k;

        for (String command : cmd) {
            char type = command.charAt(0);
            if (type == 'U') {
                int x = Integer.parseInt(command.split(" ")[1]);
                while (x-- > 0) {
                    current = activeRows.lower(current);
                }
            } else if (type == 'D') {
                int x = Integer.parseInt(command.split(" ")[1]);
                while (x-- > 0) {
                    current = activeRows.higher(current);
                }
            } else if (type == 'C') {
                deleted.push(current);
                activeRows.remove(current);
                if (activeRows.higher(current) != null) {
                    current = activeRows.higher(current);
                } else {
                    current = activeRows.lower(current);
                }
            } else if (type == 'Z') {
                activeRows.add(deleted.pop());
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(activeRows.contains(i) ? "O" : "X");
        }
        return result.toString();
    }
}