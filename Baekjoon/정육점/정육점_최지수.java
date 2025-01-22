import java.util.*;
import java.io.*;

public class Main {
    static int n, k;

    static class Meat implements Comparable<Meat> {
        int weight, cost;
        public Meat(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
        public int compareTo(Meat o) {
            if (o.cost == this.cost) return o.weight - this.weight;
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        PriorityQueue<Meat> que = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            que.add(new Meat(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int answer = -1;

        int accCost = 0;
        int preCost = -1;
        int accWeight = 0;
        while (!que.isEmpty()) {
            Meat now = que.poll();
            accWeight += now.weight;
            if (preCost == now.cost) accCost += now.cost;
            else accCost = now.cost;
            preCost = now.cost;
            if (k <= accWeight) {
                if (answer == -1) answer = accCost;
                else answer = Math.min(answer, accCost);
            }
        }

        System.out.println(answer);
    }
}

