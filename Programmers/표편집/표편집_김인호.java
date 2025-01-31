import java.io.*;
import java.util.*;

class Solution {
    static int currentPosition, totalSize;
    static Stack<Integer> delPositions;

    public String solution(int n, int k, String[] cmd) {
        totalSize = n; currentPosition = k;
        delPositions = new Stack<>();
        for (String s : cmd) {
            String[] commandParts = s.split(" ");
            char operation = commandParts[0].charAt(0);
            if (operation == 'D') {
                int steps = Integer.parseInt(commandParts[1]);
                moveDown(steps);
            } else if (operation == 'U') {
                int steps = Integer.parseInt(commandParts[1]);
                moveUp(steps);
            } else if (operation == 'C') {
                totalSize--;
                deleteCurrentNode();
            } else {
                totalSize++;
                restoreNode();
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < totalSize ; i++) {
            sb.append('O');
        }
        while(!delPositions.isEmpty()) {
            sb.insert(delPositions.pop().intValue(), 'X');
        }
        return sb.toString();
    }

    static void deleteCurrentNode() {
        delPositions.push(currentPosition);
        if(currentPosition == totalSize) currentPosition--;
    }

    static void restoreNode() {
        int positionToRestore = delPositions.pop();
        if(currentPosition >= positionToRestore) currentPosition++;
    }
    static void moveDown(int steps) {
        currentPosition += steps;
    }

    static void moveUp(int steps) {
        currentPosition -= steps;
    }

}
