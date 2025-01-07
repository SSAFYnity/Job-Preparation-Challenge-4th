import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Meat> meatList = new ArrayList<>();
        for (int index = 0; index < N; index++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            meatList.add(new Meat(weight, cost));
        }
        Collections.sort(meatList);
        System.out.println(calculateMinimumCost(meatList, M));
    }
    private static int calculateMinimumCost(List<Meat> meatList, int requiredWeight) {
        int beforeCost = 0;
        int accumulatedWeight = 0;
        int answerCandidate = 0;
        int answer = Integer.MAX_VALUE;

        for (Meat meat : meatList) {
            accumulatedWeight += meat.weight;

            if (beforeCost != meat.cost) {
                answerCandidate = meat.cost;
                beforeCost = meat.cost;
            } else {
                answerCandidate += meat.cost;
            }

            if (accumulatedWeight >= requiredWeight) {
                answer = Math.min(answer, answerCandidate);
            }
        }
        if (accumulatedWeight < requiredWeight) {
            return -1;
        }
        return answer;
    }
}
class Meat implements Comparable<Meat> {
    int weight;
    int cost;
    public Meat(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }
    @Override
    public int compareTo(Meat meat) {
        if (this.cost != meat.cost) {
            return this.cost - meat.cost;
        }
        return meat.weight - this.weight;
    }
}