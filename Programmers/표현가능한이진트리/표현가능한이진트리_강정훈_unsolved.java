class Solution {
    public int[] solution(long[] numbers) {
        int size = numbers.length;
        int[] answer = new int[size];
        for (int index = 0; index < size; index++) {
            long number = numbers[index];
            String binary = Long.toBinaryString(number);
            String fullBinaryTree = makeFullBinaryTree(binary);
            answer[index] = divideAndConquer(fullBinaryTree) ? 1 : 0;
        }
        return answer;
    }

    private boolean divideAndConquer(String binary) {
        int size = binary.length();
        if (size == 1) {
            return true;
        }
        int root = size / 2;
        String left = binary.substring(0, root);
        String right = binary.substring(root + 1);
        if (binary.charAt(root) == '0') {
            return false;
        }
        return divideAndConquer(left) && divideAndConquer(right);
    }
    private String makeFullBinaryTree(String binary) {
        int size = binary.length();
        int levelNodeCount = 1;
        int totalNodeCount = 1;
        while(size > totalNodeCount) {
            levelNodeCount *= 2;
            totalNodeCount += levelNodeCount;
        }
        StringBuilder newBinary = new StringBuilder();
        for (int count = 0; count < totalNodeCount - size; count++) {
            newBinary.append("0");
        }
        return newBinary.append(binary).toString();
    }
}