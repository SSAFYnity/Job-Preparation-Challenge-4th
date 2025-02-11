import java.util.*;
class Solution {
    private int leftMinValue = Integer.MAX_VALUE;
    private int leftSecondMinValue = Integer.MAX_VALUE;
    private Set<Integer> visited;
    private Queue<Integer> rightQueue;
    public int solution(int[] a) {
        if(a.length <= 2) {
            return a.length;
        }
        int answer = 0;
        visited = new HashSet<>();
        rightQueue = new PriorityQueue<>();
        for (int baloon : a) {
            rightQueue.add(baloon);
        }
        rightQueue.add(Integer.MAX_VALUE);
        rightQueue.add(Integer.MAX_VALUE);
        int leftMinValue = Integer.MAX_VALUE;
        int leftSecondMinValue = Integer.MAX_VALUE;
        for (int baloon : a) {
            visited.add(baloon);
            int[] rightMinValues = getRightMinValues();
            int rightMinValue = rightMinValues[0];
            int rightSecondMinValue = rightMinValues[1];
            if (canPop(baloon, leftMinValue, leftSecondMinValue, rightMinValue, rightSecondMinValue)) {
                answer++;
            }
            if (leftMinValue > baloon) {
                leftSecondMinValue = leftMinValue;
                leftMinValue = baloon;
            } else if (leftMinValue < baloon && baloon < leftSecondMinValue) {
                leftSecondMinValue = baloon;
            }
        }
        return answer;
    }
    private int[] getRightMinValues() {
        int[] rightMinValues = new int[2];
        while(true) {
            int candidate = rightQueue.peek();
            if (visited.contains(candidate)) {
                rightQueue.poll();
            } else {
                if (rightMinValues[0] != 0) {
                    rightMinValues[1] = candidate;
                    rightQueue.add(rightMinValues[0]);
                    break;
                }
                rightQueue.poll();
                rightMinValues[0] = candidate;
            }
        }
        return rightMinValues;
    }
    private boolean canPop(int baloon, int leftMinValue, int leftSecondMinValue, int rightMinValue, int rightSecondMinValue) {
        if (leftMinValue > baloon && rightMinValue > baloon) {
            return true;
        } else if (leftMinValue > baloon && rightMinValue < baloon) {
            return true;
        } else if (leftMinValue < baloon && rightMinValue > baloon) {
            return true;
        }
        return false;
    }
}