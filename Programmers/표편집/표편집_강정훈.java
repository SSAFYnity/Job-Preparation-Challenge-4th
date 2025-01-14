import java.util.*;
class Solution {
    private List<Node> graph;
    private char[] answer;
    private int cursor;
    private Deque<Node> deletedData;
    public String solution(int n, int k, String[] cmd) {
        deletedData = new ArrayDeque<>();
        graph = new ArrayList<>(n);
        cursor = k;
        answer = new char[n];
        Arrays.fill(answer, 'O');
        for (int index = 0; index < n; index++) {
            int prevIndex = (n + index - 1) % n;
            int nextIndex = (n + index + 1) % n;
            graph.add(new Node(prevIndex, index, nextIndex));
        }
        for (String command: cmd) {
            String[] commandInfo = command.split(" ");
            executeCommand(commandInfo);
        }
        return new String(answer);
    }

    private void executeCommand(String[] commandInfo) {
        String command = commandInfo[0];
        if(command.equals("U")) {
            executeUpCommand(Integer.parseInt(commandInfo[1]));
        } else if (command.equals("D")) {
            executeDownCommand(Integer.parseInt(commandInfo[1]));
        } else if (command.equals("C")) {
            executeCutCommand();
        } else {
            executeUndoCommand();
        }
    }

    private void executeUpCommand(int movingDistance) {
        Node node = graph.get(cursor);
        for (int moveCount = 0; moveCount < movingDistance; moveCount++) {
            cursor = node.prevIndex;
            node = graph.get(cursor);
        }
    }

    private void executeDownCommand(int movingDistance) {
        for (int moveCount = 0; moveCount < movingDistance; moveCount++) {
            Node node = graph.get(cursor);
            cursor = node.nextIndex;
        }
    }

    private void executeCutCommand() {
        answer[cursor] = 'X';
        Node node = graph.get(cursor);
        deletedData.addLast(node);
        Node prevNode = graph.get(node.prevIndex);
        Node nextNode = graph.get(node.nextIndex);
        prevNode.nextIndex = nextNode.currentIndex;
        nextNode.prevIndex = prevNode.currentIndex;
        if(node.currentIndex > node.nextIndex) {
            cursor = node.prevIndex;
            return;
        }
        cursor = node.nextIndex;
    }

    private void executeUndoCommand() {
        Node node = deletedData.pollLast();
        answer[node.currentIndex] = 'O';
        Node prevNode = graph.get(node.prevIndex);
        Node nextNode = graph.get(node.nextIndex);
        prevNode.nextIndex = node.currentIndex;
        nextNode.prevIndex = node.currentIndex;
    }
}
class Node {
    int prevIndex;
    int currentIndex;
    int nextIndex;

    public Node(int prevIndex, int currentIndex, int nextIndex) {
        this.prevIndex = prevIndex;
        this.currentIndex = currentIndex;
        this.nextIndex = nextIndex;
    }
}